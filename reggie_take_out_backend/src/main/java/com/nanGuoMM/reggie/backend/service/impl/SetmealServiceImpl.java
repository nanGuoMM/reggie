package com.nanGuoMM.reggie.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nanGuoMM.reggie.backend.domain.category.PO.CategoryPO;
import com.nanGuoMM.reggie.backend.service.ISetmealDishService;
import com.nanGuoMM.reggie.backend.service.ISetmealService;
import com.nanGuoMM.reggie.backend.domain.setmeal.DTO.SetmealDTO;
import com.nanGuoMM.reggie.backend.domain.setmeal.PO.SetmealPO;
import com.nanGuoMM.reggie.backend.domain.setmeal_dish.DTO.SetmealDishDTO;
import com.nanGuoMM.reggie.backend.domain.setmeal_dish.PO.SetmealDishPO;
import com.nanGuoMM.reggie.backend.mapper.SetmealMapper;
import com.nanGuoMM.reggie.backend.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, SetmealPO> implements ISetmealService {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISetmealDishService setmealDishService;

    @Override
    public IPage<SetmealDTO> pageSetmeal(int page, int pageSize, String name) {
        //分页查询
        IPage<SetmealPO> setmealPOIPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<SetmealPO> queryWrapper = new LambdaQueryWrapper<SetmealPO>()
                .like(StringUtils.hasLength(name),SetmealPO::getName,name)
                .orderByDesc(SetmealPO::getUpdateTime);
        super.page(setmealPOIPage,queryWrapper);

        //封装dto
        List<SetmealDTO> setmealDTOS = setmealPOIPage.getRecords().stream().map(setmealPO -> {
            SetmealDTO setmealDTO = new SetmealDTO();
            BeanUtils.copyProperties(setmealPO,setmealDTO);
            //查询套餐分类名，并赋值
            CategoryPO categoryPO = categoryService.getById(setmealPO.getCategoryId());
            setmealDTO.setCategoryName(categoryPO.getName());
            return setmealDTO;
        }).collect(Collectors.toList());

        //转换
        IPage<SetmealDTO> setmealDTOIPage = new Page<>(page,pageSize,setmealPOIPage.getTotal());
        setmealDTOIPage.setRecords(setmealDTOS);
        return setmealDTOIPage;
    }

    @Override
    public void saveSetmeal(SetmealDTO setmealDTO) {
        //转换，并保存套餐
        SetmealPO setmealPO = new SetmealPO();
        BeanUtils.copyProperties(setmealDTO,setmealPO);
        super.save(setmealPO);
        //转换
        List<SetmealDishPO> setmealDishPOS = setmealDTO.getSetmealDishes().stream().map(setmealDishDTO -> {
            SetmealDishPO setmealDishPO = new SetmealDishPO();
            BeanUtils.copyProperties(setmealDishDTO, setmealDishPO);
            //手动设置setmealID
            setmealDishPO.setSetmealId(setmealPO.getId().toString());
            return setmealDishPO;
        }).collect(Collectors.toList());
        //保存套餐菜品关系表
        setmealDishService.saveBatch(setmealDishPOS);
    }

    @Override
    public SetmealDTO getSetmealById(Long id) {
        //查询套餐
        SetmealPO setmealPO = super.getById(id);
        //查询套餐菜品关系
        LambdaQueryWrapper<SetmealDishPO> queryWrapper = new LambdaQueryWrapper<SetmealDishPO>()
                .eq(SetmealDishPO::getSetmealId,id);
        List<SetmealDishPO> setmealDishPOS = setmealDishService.list(queryWrapper);
        //封装dto
        SetmealDTO setmealDTO = new SetmealDTO();
        BeanUtils.copyProperties(setmealPO,setmealDTO);
        setmealDishPOS.forEach(setmealDishPO -> {
            SetmealDishDTO setmealDishDTO = new SetmealDishDTO();
            BeanUtils.copyProperties(setmealDishPO,setmealDishDTO);
            setmealDTO.getSetmealDishes().add(setmealDishDTO);
        });
        return setmealDTO;
    }

    @Override
    public void updateSetmeal(SetmealDTO setmealDTO) {
        //更新套餐信息
        SetmealPO setmealPO = new SetmealPO();
        BeanUtils.copyProperties(setmealDTO,setmealPO);
        super.updateById(setmealPO);

        //更新菜品和套餐关系表
        setmealDTO.getSetmealDishes().forEach(setmealDishDTO -> {
            SetmealDishPO setmealDishPO = new SetmealDishPO();
            BeanUtils.copyProperties(setmealDishDTO,setmealDishPO);
            setmealDishService.updateById(setmealDishPO);
        });
    }

    @Override
    public void deleteSetmeal(List<Long> ids) {
        //删除菜品和套餐关系
        LambdaQueryWrapper<SetmealDishPO> queryWrapper = new LambdaQueryWrapper<SetmealDishPO>()
                .in(SetmealDishPO::getSetmealId,ids);
        setmealDishService.remove(queryWrapper);
        //删除套餐
        super.removeByIds(ids);
    }
}

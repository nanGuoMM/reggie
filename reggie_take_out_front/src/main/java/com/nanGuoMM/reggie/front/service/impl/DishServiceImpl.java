package com.nanGuoMM.reggie.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanGuoMM.reggie.front.domain.dish.DTO.DishDTO;
import com.nanGuoMM.reggie.front.domain.dish.PO.DishPO;
import com.nanGuoMM.reggie.front.domain.dish_flavor.DTO.DishFlavorDTO;
import com.nanGuoMM.reggie.front.domain.dish_flavor.PO.DishFlavorPO;
import com.nanGuoMM.reggie.front.mapper.DishMapper;
import com.nanGuoMM.reggie.front.service.IDishFlavorService;
import com.nanGuoMM.reggie.front.service.IDishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-23
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, DishPO> implements IDishService {

    @Autowired
    IDishFlavorService dishFlavorService;

    @Cacheable(cacheNames = "dishCache",key = "#categoryId + '_' + #status")
    @Override
    public List<DishDTO> listDish(long categoryId, Integer status) {
        //查询
        LambdaQueryWrapper<DishPO> queryWrapper = new LambdaQueryWrapper<DishPO>()
                .eq(DishPO::getCategoryId,categoryId).eq(DishPO::getStatus,status);
        List<DishPO> dishPOS = super.list(queryWrapper);
        //转换
        List<DishDTO> dishDTOS = new ArrayList<>();
        dishPOS.forEach(dishPO -> {
            DishDTO dishDTO = new DishDTO();
            BeanUtils.copyProperties(dishPO,dishDTO);
            dishDTOS.add(dishDTO);
        });
        dishDTOS.forEach(dishDTO -> {
            LambdaQueryWrapper<DishFlavorPO> queryWrapper1 = new LambdaQueryWrapper<DishFlavorPO>()
                    .eq(DishFlavorPO::getDishId,dishDTO.getId());
            List<DishFlavorPO> flavorPOS = dishFlavorService.list(queryWrapper1);

            flavorPOS.forEach(dishFlavorPO -> {
                DishFlavorDTO dishFlavorDTO = new DishFlavorDTO();
                BeanUtils.copyProperties(dishFlavorPO,dishFlavorDTO);
                dishDTO.getFlavors().add(dishFlavorDTO);
            });
        });
        return dishDTOS;
    }
}

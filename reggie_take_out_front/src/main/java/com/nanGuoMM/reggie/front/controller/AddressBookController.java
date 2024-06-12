package com.nanGuoMM.reggie.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nanGuoMM.reggie.front.domain.Result;
import com.nanGuoMM.reggie.front.domain.address_book.DTO.AddressBookDTO;
import com.nanGuoMM.reggie.front.domain.address_book.PO.AddressBookPO;
import com.nanGuoMM.reggie.front.service.IAddressBookService;
import com.nanGuoMM.reggie.front.utils.BaseContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 地址管理 前端控制器
 * </p>
 *
 * @author nanGuoMM
 * @since 2024-05-29
 */
@RestController
@RequestMapping("/addressBook")
@Api(tags = "地址")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    @ApiOperation("展示")
    @GetMapping("/list")
    public Result<List<AddressBookDTO>> listAddress() {
        //调用service展示所有地址
        LambdaQueryWrapper<AddressBookPO> queryWrapper = new LambdaQueryWrapper<AddressBookPO>()
                .eq(AddressBookPO::getUserId,BaseContext.getCurrentId());
        List<AddressBookPO> list = addressBookService.list(queryWrapper);

        List<AddressBookDTO> addressBookDTOS = list.stream().map(addressBookPO -> {
            AddressBookDTO addressBookDTO = new AddressBookDTO();
            BeanUtils.copyProperties(addressBookPO, addressBookDTO);
            //手动设置isDefault
            addressBookDTO.setDefault(addressBookPO.getIsDefault());
            return addressBookDTO;
        }).collect(Collectors.toList());

        return Result.success(addressBookDTOS);
    }

    @ApiOperation("保存")
    @PostMapping
    public Result<Object> saveAddress(@ApiParam("新地址") @RequestBody AddressBookDTO addressBookDTO) {
        //转换
        AddressBookPO addressBookPO = new AddressBookPO();
        BeanUtils.copyProperties(addressBookDTO,addressBookPO);
        //调用service保存
        addressBookService.save(addressBookPO);

        return Result.success();
    }

    @ApiOperation("回显")
    @GetMapping("/{id}")
    public Result<AddressBookDTO> getAddressById(@ApiParam("地址id") @PathVariable Long id) {
        //查询
        AddressBookPO addressBookPO = addressBookService.getById(id);
        //封装dto
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        BeanUtils.copyProperties(addressBookPO,addressBookDTO);
        return Result.success(addressBookDTO);
    }

    @ApiOperation("修改")
    @PutMapping
    public Result<Object> updateAddress(@ApiParam("新地址") @RequestBody AddressBookDTO addressBookDTO) {
        //封装PO
        AddressBookPO addressBookPO = new AddressBookPO();
        BeanUtils.copyProperties(addressBookDTO,addressBookPO);
        //调用service
        addressBookService.updateById(addressBookPO);
        return Result.success();
    }

    @ApiOperation("删除")
    @DeleteMapping
    public Result<Object> deleteAddress(@ApiParam("地址id") @RequestParam Long ids) {
        //调用service
        addressBookService.removeById(ids);

        return Result.success();
    }

    @ApiOperation("默认Put")
    @PutMapping("/default")
    public Result<Object> defaultAddress(@ApiParam("地址id") @RequestBody Long id) {
        //构建修改条件
        LambdaUpdateWrapper<AddressBookPO> updateWrapper = new LambdaUpdateWrapper<>();
        //设为默认地址
        updateWrapper.eq(AddressBookPO::getId,id)
                .set(AddressBookPO::getIsDefault,true);
        addressBookService.update(updateWrapper);
        //将其他的设为0
        LambdaUpdateWrapper<AddressBookPO> updateWrapper1 = new LambdaUpdateWrapper<AddressBookPO>()
                .ne(AddressBookPO::getId,id)
                .set(AddressBookPO::getIsDefault,false);
        addressBookService.update(updateWrapper1);

        return Result.success();
    }

    @ApiOperation("默认Get")
    @GetMapping("/default")
    public Result<AddressBookDTO> getDefault() {
        //构建修改条件
        LambdaQueryWrapper<AddressBookPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBookPO::getUserId,BaseContext.getCurrentId())
                .eq(AddressBookPO::getIsDefault,true);
        AddressBookPO addressBookPO = addressBookService.getOne(queryWrapper);

        AddressBookDTO addressBookDTO = new AddressBookDTO();
        BeanUtils.copyProperties(addressBookPO,addressBookDTO);
        return Result.success(addressBookDTO);
    }
}

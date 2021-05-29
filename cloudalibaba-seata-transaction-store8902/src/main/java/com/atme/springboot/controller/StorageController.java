package com.atme.springboot.controller;

import com.atme.springboot.domain.CommonResult;
import com.atme.springboot.service.StorageService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wsk
 * @date 2020/3/25 21:55
 */
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

//    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200, "扣减库存成功");
    }
}
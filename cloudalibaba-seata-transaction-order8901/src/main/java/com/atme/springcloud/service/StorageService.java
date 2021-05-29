package com.atme.springcloud.service;

import com.atme.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wsk
 * @date 2020/3/25 21:00
 */
@FeignClient(value = "seata-store-service")  // 存储服务的服务名称不要写错了
public interface StorageService {

    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}

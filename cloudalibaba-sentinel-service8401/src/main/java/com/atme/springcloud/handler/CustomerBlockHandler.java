package com.atme.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entity.CommonResult;

/**
 * 兜底类
 *
 * @author wsk
 * @date 2020/3/24 22:04
 */
public class CustomerBlockHandler {

    /**
     * 兜底方法
     */
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444, "按客户自定义，global handlerException-----1");
    }

    /**
     * 兜底方法
     */
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "按客户自定义，global handlerException-----2");
    }
}

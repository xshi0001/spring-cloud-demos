package cloud.atguigu.springgcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * 服務端- 设置服务降级策略（针对服务异常与服务超时场景）
 *
 * @author JClearLove
 * @date 2021/04/28 06:43
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfoOk(Integer id) {
        return null;
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",
            commandProperties = {
                    // 设置该方法执行时间不要超过3s，否则执行执行备用方法
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            })
    @Override
    public String paymentInfoTimeOut(Integer id) {
        // 模拟超时，超过3s就回调HystrixCommmond
        int timeNum = 3;

        // 异常模拟
//        int age = 10 / 0;

        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池:  " + Thread.currentThread().getName() + "paymentInfoTimeout  id:" + id + "  O(∩_∩)O 耗时 " + timeNum;
    }

    /**
     * 用于在超时场景中的兜底方法
     */
    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + "系统繁忙或运行报错，请稍后再试 id:" + id + "  /(ㄒoㄒ)/~~ ";
    }


    // ======================================服务熔断 ===================================


    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {

            //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //时间范围
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    @Override
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能为负数，请稍后再试 /(ㄒoㄒ)/~~  id" + id;
    }


}


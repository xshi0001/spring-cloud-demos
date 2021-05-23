package cloud.atguigu.springcloud.service;

import org.springframework.stereotype.Component;


/**
 *
 *  解决代码膨胀与代码混乱的问题
 *
 * 请求服务端服务接口的fallback方法，与之前直接写到在OrderHystrixController中的fallback的方法不同
 *
 * 这样直接解耦，避免对主要业务代码的污染。
 * 如果服务端宕机了。客户端那么直接调用这些fallback方法
 *
 * FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
 * @author JClearLove
 * @date 2021/05/23 17:33
 */
@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "客户端fallback----paymentFallbackService fall back-paymentInfo_OK  /(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "客户端fallback---PaymentFallbackService fallback-paymentInfo_TimeOut /(ㄒoㄒ)/~~";
    }
}

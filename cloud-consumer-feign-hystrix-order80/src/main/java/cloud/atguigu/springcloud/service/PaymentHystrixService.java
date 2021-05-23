package cloud.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackServiceImpl.class)
public interface PaymentHystrixService {
    /**
     *正常访问
     */
    @RequestMapping(value = "/payment/hystrix/ok/{id}",method = RequestMethod.GET)
    String paymentInfoOk(@PathVariable("id") Integer id);

    /**
     * 超时
     */
    @RequestMapping(value = "/payment/hystrix/timeout/{id}",method = RequestMethod.GET)
    String paymentInfoTimeOut(@PathVariable("id") Integer id);
}

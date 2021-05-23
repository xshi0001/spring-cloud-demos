package cloud.atguigu.springgcloud.service;

/**
 * 服務端接口
 *
 * @author JClearLove
 * @date 2021/04/28 06:42
 */
public interface PaymentService {
    String paymentInfoOk(Integer id);

    String paymentInfoTimeOut(Integer id);

    String paymentCircuitBreaker(Integer id);
}

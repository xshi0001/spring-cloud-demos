package cloud.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author JClearLove
 * @date 2021/05/23 17:33
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class feignHystrixOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(feignHystrixOrder80.class, args);
    }
}



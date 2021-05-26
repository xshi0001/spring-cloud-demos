# cloudlearn


b站资料：
[尚硅谷2020最新版SpringCloud(H版&alibaba)框架开发教程全套完整版](https://www.bilibili.com/video/BV18E411x7eT?p=2&spm_id_from=pageDriver)

CSDN对应笔记：
- [尚硅谷2020周阳老师SpringCloud学习整理笔记第一部分(H版&alibaba)](https://blog.csdn.net/qq_42107430/article/details/104683947)
- [尚硅谷2020周阳老师SpringCloud学习整理笔记第二部分](https://blog.csdn.net/qq_42107430/article/details/104788858)
- [尚硅谷2020最新版SpringCloud(H版&alibaba)-脑图](https://www.processon.com/view/link/5e96e37e1e085369d0c8a39b#map)


springboot核springcloud的版本选择要兼顾 ---> start.spring.io/actuator/info


springcloud Hoxton.SR1 springboot 2.2.2.RELEASE cloud alibaba
2.1.0.RELEASE Java 8 Maven 3.5+ Mysql 5.7+


springcloud 的“惨案”
- 服务注册中心-Eureka(x) Zookeeper Consul Nacos(重点掌握)
- 服务调用- Ribbon LoadBalancer
- 服务调用2- Feign(x) OpenFeign
- 服务降级 Hystrix(x) resilience4j sentienl
- 服务网关 Zuul(x) Zuul2 (!) gateway
- 服务配置 Config(x) Nacos
- 服务总线 Bus(x) Nacos

约定>配置>编码


## cloud-provide-payment8001

- 返回体可以用泛型对象表示
- 数据库连接池 -druid等连接池的使用
- devtools 热部署
- RestTemplate for a client to server like httpclient


- 无法显示dashboard打开workspace.xml文件之后，找到component为RunDashboard的节点处，然后在component标签里添加

```XML
<option name="configurationTypes">
  <set>
    <option value="SpringBootApplicationConfigurationType" />
  </set>
 </option>
```

## Registry Center

### Eureka

- 服务注册中心-Eureka(x) Zookeeper Consul Nacos(重点掌握)

动嘴和动手同样重要！ ”一个医生对应多个病人，病人通过挂号中心注册信息“

Eureka- “三角形架构” 消费者服务（多个，学）+ 注册中心（多个，学校70001）+
服务提供者（多个，教） 对比dubbe

注册中心server-物业公司要做的
1. @EnableEurekaServer 启动类表明是server
2. application.yml配置
3. 集群版--互相注册，相互守望


服务提供者
1. 改pom+@EnableEurekaClient
   2。服务发现功能-暴露对方自身服务信息，在主类引入@EnableDiscoveryClient，并在controller中使用DiscoveryClient-bean

服务访问者
1. 访问者访问服务只要指定访问服务名称就好，`http://CLOUD-PAYMENT-SERVICE`就可以
2. 要在`RestTemplate`配置中添加`@LoadBalanced`，指定负载均衡策略，默认是轮询

其它配置： 1.主机名称修改 2.ip信息提示 3.Eureka自我保护-
服务不能用，但是不会对服务的信息进行清楚，属于CAP的AP分支-enable-self-preservation
和 eviction-interval-timer-in-ms

总结：
eureka三角形框架，有种代理模式的意思，高可用，不知道性能怎样，后面有机会在翻翻，目前已经停止更新，但停更不停用。

### zookeeper

临时结点，持久结点 那么服务在zk上注册的话是哪一种结点？ -**临时结点** 比较绝情！

注意和服务器上的zk安装版本一致

### Consul

- [官方中文文档](https://www.springcloud.cc/spring-cloud-consul.html)
- 是一套的开源的分布式服务发现和配置管理系统，由go语言 功能： Sevice
  Discovery、监控检测、kv存储等
- consul.exe运行

- 验证consul --version
- `consul agent -dev` -http://localhost:8500/ui/dc1/services

cap- （consistency强一致性、Availability可用性、Partition
tolerance分区容错性) cap理论关注的是数据，


## loadbalance

### Ribbon

集中式LB （医院大门）
Nginx是服务端负载均衡，客户端所有请求都会交给nginx,然后由nginx实现转发请求，即负载均衡是由服务端实现的
-- 安装在服务端

进程中LB（多个医生）
Ribbon是本地负载均衡，在调用微服务接口时候，会在注册中上获取注册信息服务列表(Eureka)之后缓存到JVM本地(客户端的本地)，从而在本地实现RPC远程服务调用技术--安装在客户端

                       Eureka(注册中心)
                  /

客户端（Ribbon) 微服务1


                                                微服务2


策略：轮询、随机、响应时间加权 （IRule核心接口-算法实现）


Ribbon 自定义规则
1. 首先基于IRule接口，创建策略bean
   [MySelfRule.java](cloud-consumer-order80/src/main/java/com/atguigu/myrule/MySelfRule.java)
2. 在主启动类[OrderMain80.java](cloud-consumer-order80/src/main/java/com/atguigu/springcloud/OrderMain80.java),加上注解`@RibbonClient(name = "CLOUD-PAYMENT_SERVICE", configuration = MySelfRule.class)`
3. 去掉注解 @LoadBalanced//负载均衡

★ RoundRobin源码分析- CAS 自旋锁


## OpenFeign


是什么: Rest Client, 编写一个服务接口和Feign注解 （Dao层上面的@Mapper）
为什么有Ribbon了还有Feign?(简化开发，一个服务接口比如，PaymentService，被多个微服务调用，)

面向接口编程: Ribbon + Restemplate => 微服务调用接口PaymentService+
@FeignClient == controller-->service

超时控制

## Hystrix

[服务容错保护——Spring Cloud Hystrix](https://www.jianshu.com/p/6c574abe50c1)
[Hystrix学习](https://blog.csdn.net/f641385712/category_9921991.html)

服务熔断、降级、接近实时的监控

出现场景：分布式系统的延迟和容错开源库
服务颗粒度越来越小，链路越来越长，如果某一个服务挂掉，就会导致其它服务无法使用，如何解决某一个服务挂掉不会导致整体服务失败，避免级联故障，提高分布式系统的弹性？

Hystrix-“断路器” 是什么
当某个服务异常，向调用方返回一个符合预期的、可处理的备选响应（Fallback）、而不是长时间的等待或者抛出调用方无法处理的异常，保证服务调用方的线程不会长时间、不必要的占用，避免故障在分布式系统中的蔓延、乃至雪崩。


> tomcat的默认工作线程数被打满了，没有多余的线程来分解压力和处理

既可以放到服务端也可以放到服务端

- 服务降级

- 服务熔断

 牛逼的地方在于：监测A服务的是否正常，不正常立马给服务调用方返回，并且将**尝试恢复**服务A



- 服务限流



## Service Gateway

zuul网关
[Gateway新一代网关](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)
理念架构的思想 谈谈你对网关的理解？zuul与gateway区别，技术选型是怎么来的（看xmind）
gateway:
- 基于Spring Framework5、Project Reactor（WebFlux
  使用了高性能的Reactor模式通信框架Netty）、Spring Boot 2.0进行构件;
- 动态路由: 能够匹配任何请求属性;
- 可以对路由指定Predicate断言和Filter过滤器，易于编写的Predicate断言和Filter过滤器;
- 集成Hystrix的断路器功能，请求限流功能;
- 支持路由重写;
- 继承Spring Cloud服务发现功能;

zuul 1.x 是基于servlet2.0的阻塞式的网络IO网络框架（每一个servlet请求对应一个线程）

网关的三大组件（核心逻辑：路由转发和执行过滤器）

- Route-路由（由ID、目标URI，一系列的断言和过滤器组成，断言为true则匹配该路由）
- Predicate-断言（匹配请求中的所有内容-请求头或者请求参数，如果请求与断言相匹配则进行路由）
- Filter-过滤（spring中GatewayFilter实例，使用过滤器，请求被路由前或者之后对Http请求和返回的Http响应进行修改），分为"pre"和"post"阶段，种类分为GatewayFilter(单一的)和
  Global Filter(全局的)

##  服务配置

Config
Bus

## stream

解决各种中间件使用问题，屏蔽技术细节

## nacos

## sentinel

docker部署sentinel无法监控的问题

流控效果：

- 直接失败—》
- 预热 warm up (由低水位到高水位，冷加载因子=3)
1 QpS和线程数限流
2 A接口（订单接口）访问达到阈值的时候，限制B接口 （下单接口）









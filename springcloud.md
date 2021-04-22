# cloudlearn

b站资料：
[尚硅谷2020最新版SpringCloud(H版&alibaba)框架开发教程全套完整版](https://www.bilibili.com/video/BV18E411x7eT?p=2&spm_id_from=pageDriver)

CSDN对应笔记：
- [尚硅谷2020周阳老师SpringCloud学习整理笔记第一部分(H版&alibaba)](https://blog.csdn.net/qq_42107430/article/details/104683947)
- [尚硅谷2020周阳老师SpringCloud学习整理笔记第二部分](https://blog.csdn.net/qq_42107430/article/details/104788858)
- [尚硅谷2020最新版SpringCloud(H版&alibaba)-脑图](https://www.processon.com/view/link/5e96e37e1e085369d0c8a39b#map)


springboot核springcloud的版本选择要兼顾
 ---> start.spring.io/actuator/info
 
 
 springcloud Hoxton.SR1
 springboot 2.2.2.RELEASE
 cloud alibaba 2.1.0.RELEASE
 Java 8
 Maven 3.5+
 Mysql 5.7+
 
 
 springcloud 的“惨案”
- 服务注册中心-Eureka(x) Zookeeper  Consul  Nacos(重点掌握)
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
## 
- restTemplate for client to server like httpclient


- 无法显示dashboard打开workspace.xml文件之后，找到component为RunDashboard的节点处，然后在component标签里添加

```XML
<option name="configurationTypes">
  <set>
    <option value="SpringBootApplicationConfigurationType" />
  </set>
 </option>
```

## Eureka 
- 服务注册中心-Eureka(x) Zookeeper  Consul  Nacos(重点掌握)

动嘴和动手同样重要！
”一个医生对应多个病人，病人通过挂号中心注册信息“

 Eureka- “三角形架构” 消费者服务（多个，学）+ 注册中心（多个，学校70001）+ 服务提供者（多个，教）
对比dubbe

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

其它配置：
1.主机名称修改
2.ip信息提示
3.Eureka自我保护- 服务不能用，但是不会对服务的信息进行清楚，属于CAP的AP分支-enable-self-preservation 和 eviction-interval-timer-in-ms

总结：
eureka三角形框架，有种代理模式的意思，高可用，不知道性能怎样，后面有机会在翻翻，目前已经停止更新，但停更不停用。

# CORN  
Spring Cloud Basic Framework / 小企业级微服务基础框架

### Preparation for the Project / 项目准备  
##### 1、Develop Environment Necessities  / 环境要求  
> |  environment / 环境   | version / 版本  |
> | --------- | ----------- |
> | Redis   | above 3.2  / 3.2 以上  |
> | JDK | 1.8 |	
  
##### 2、Start the Project / 项目启动  
> IDE must install lombok plugin / IDE安装lombok插件  
> starting sequence / 项目启动顺序：  
>  1、redis-server  
>  2、corn-eureka  
>  3、corn-gateway  
>  4、corn其他服务  
  
### Project Structure / 项目结构  
> corn  
>   |-- corn-common  --  common serivce jar / 公共服务jar包  
>   |-- corn-gateway  --  gateway / 网关路由  
>   |-- corn-eureka  --  microservices  govern / 服务治理  
>   |-- corn-uas--  user authority system / 用户权限系统  
>       |-- corn-uas-api  --  uas api / uas 对外服务  
>       |-- corn-uas-biz  --  uas bussiness / uas 核心业务  

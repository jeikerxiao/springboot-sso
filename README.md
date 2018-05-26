# springboot-sso

spring boot sso 单点登录。

项目依赖：

```java
compile('org.springframework.security.oauth:spring-security-oauth2')
compile('org.springframework.boot:spring-boot-starter-security')
compile('org.springframework.boot:spring-boot-starter-web')
runtime('org.springframework.boot:spring-boot-devtools')
compileOnly('org.projectlombok:lombok')
testCompile('org.springframework.boot:spring-boot-starter-test')
testCompile('org.springframework.security:spring-security-test')
```

## sso-server

单点登录的验证服务器。

服务器配置：

```java
server:
  port: 8888
security:
  oauth2:
    client:
      client-id: clientid
      client-secret: clientsecret
      scope: read,write
      auto-approve-scopes: '.*'
  user:
    name: admin
    password: 123456
```

开启认证服务器注解

```java
@SpringBootApplication
// 认证服务器
@EnableAuthorizationServer
public class SsoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoServerApplication.class, args);
	}
}
```

开始资源服务器

```java
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/user")
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
```



## sso-client

client 分为两个项目

1. sso-client1
2. sso-client2

客户端配置基本相同：

```java
server:
  port: 8081
security:
  oauth2:
    client:
      client-id: clientid
      client-secret: clientsecret
      access-token-uri: http://localhost:8888/oauth/token
      user-authorization-uri: http://localhost:8888/oauth/authorize
    resource:
      user-info-uri: http://localhost:8888/user
  user:
    name: user
    password: 123456
```

开启sso注解

```java
@SpringBootApplication
@EnableOAuth2Sso
public class SsoClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(SsoClient1Application.class, args);
	}
}
```

测试接口：

```java
@RestController
public class UserController {

    @GetMapping("/client1")
    public Map<String, String> hello(Principal user) {
        return Collections.singletonMap("user", user.getName());
    }
}
```


两个项目基本相同。

# 运行

## 1.启动项目

1. sso-server
2. sso-client1
3. sso-client2

## 2.浏览器访问

http://localhost:8081/client1

## 3.浏览器自动跳转到sso-server进行登录验证

* 输入账号：admin
* 输入密码：123456

## 4.返回响应结果

```javascript
{
	user: "admin"
}
``` 
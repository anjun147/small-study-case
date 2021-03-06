package com.client.oauth.store;

import com.control.oauth.properties.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 使用Redis管理令牌
 * <p>
 *  默认情况下，令牌是通过 `randomUUID` 产生`32`位随机数的来进行填充的，而产生的令牌默认是存储在内存中。
 * 内存采用`TokenStore` 接口的默认实现类 `InMemoryTokenStore`  , 开发时方便调试，适用单机版。
 * `RedisTokenStore` 将令牌存储到 `Redis` 非关系型数据库中，适用于并发高的服务。
 * `JdbcTokenStore` 基于 `JDBC` 将令牌存储到 关系型数据库中，可以在不同的服务器之间共享令牌。
 * `JwtTokenStore （JSON Web Token）`将用户信息直接编码到令牌中，这样后端可以不用存储它，前端拿到令牌可以直接解析出用户信息。
 * </p>
 * <b>
 *     这里本来是在认证服务器里面已经做好了的，但是一般情况下资源服务器也需要认证token是否有效，
 *     我们一般会调用认证服务器去认证，但是我们将这个认证的处理放在资源服务器，资源服务器就可以直接验证token了，不用再重复调用认证服务器认证token。
 * </b>
 * @author: stars
 * @data: 2020年 10月 05日 18:12
 **/
@Configuration
public class AuthRedisTokenStore {

    @Bean
    public TokenStore tokenStore(RedisConnectionFactory connectionFactory, SecurityProperties securityProperties) {
        return new CustomRedisTokenStore(connectionFactory, securityProperties);
    }
}

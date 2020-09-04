package com.gold.oauthconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @author goldhuang
 * @Description: 认证服务配置
 * @date 2020-08-27
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;
    private final RedisConnectionFactory redisConnectionFactory;
    @Value("${jwt.key:GoLdJwtKey}")
    private String tokenSecretKey;

    public MyAuthorizationServerConfigurer(DataSource dataSource, RedisConnectionFactory redisConnectionFactory) {
        this.dataSource = dataSource;
        this.redisConnectionFactory = redisConnectionFactory;

    }

    /**
     * 配置安全约束相关配置
     * @param security 定义令牌终结点上的安全约束
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 支持client_id、client_secret以form表单的形式登录, 参考可见：微信获取access_token
        security.allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端详细信息
     * @param clients 定义客户端详细信息服务的配置程序。可以初始化客户端详细信息，也可以只引用现有存储。
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 1. 内存存储client信息
//        clients.inMemory()
//            // client_id
//            .withClient("gold")
//            // 授权方式
//            .authorizedGrantTypes("client_credentials")
//            // 授权范围
//            .scopes("write")
//            // client_secret
//            .secret("{noop}123456");

        // 2. 数据库存储
        clients.jdbc(dataSource).passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());

    }

    /**
     *
     * @param endpoints 定义授权和令牌端点以及令牌服务。
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        TokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        TokenStore tokenStore = new JdbcTokenStore(dataSource);

        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(tokenSecretKey);
        jwtAccessTokenConverter.setVerifier(new MacSigner(tokenSecretKey));
        TokenStore tokenStore = new JwtTokenStore(jwtAccessTokenConverter);

        endpoints.accessTokenConverter(jwtAccessTokenConverter);
        endpoints.tokenStore(tokenStore);
    }

//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(tokenSecretKey);
//        return converter;
//    }
}

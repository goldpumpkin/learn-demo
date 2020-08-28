package com.gold.oauthconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author goldhuang
 * @Description: 认证服务配置
 * @date 2020-08-27
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    /**
     * 配置安全约束相关配置
     * @param security 定义令牌终结点上的安全约束
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 支持client_id、client_secret以form表单的形式登录,参考可见：微信获取access token
        security.allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端详细信息
     * @param clients 定义客户端详细信息服务的配置程序。可以初始化客户端详细信息，也可以只引用现有存储。
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            // client_id
            .withClient("gold")
            // 授权方式
            .authorizedGrantTypes("client_credentials")
            // 授权范围
            .scopes("write")
            // client_secret
            .secret("{noop}123456");

    }

    /**
     *
     * @param endpoints 定义授权和令牌端点以及令牌服务。
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
    }
}

package com.config;

import com.service.impl.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

import static com.config.SecurityConstants.CLIENT_ID;
import static com.config.SecurityConstants.EXPIRATION_TIME;
import static com.config.SecurityConstants.GRANT_TYPE;
import static com.config.SecurityConstants.RESOURCE_ID;
import static com.config.SecurityConstants.SCOPE_READ;
import static com.config.SecurityConstants.SCOPE_WRITE;
import static com.config.SecurityConstants.SECRET;

@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenEnhancer tokenEnhancer;

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.inMemory().
                withClient(CLIENT_ID).
                secret(SECRET).
                authorizedGrantTypes(GRANT_TYPE).
                scopes(SCOPE_READ,SCOPE_WRITE).
                resourceIds(RESOURCE_ID).
                accessTokenValiditySeconds(EXPIRATION_TIME);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

        enhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer, accessTokenConverter));
        endpoints.
                tokenStore(tokenStore).
                accessTokenConverter(accessTokenConverter).
                tokenEnhancer(enhancerChain).
                authenticationManager(authenticationManager).
                userDetailsService(userDetailsService);
    }


}

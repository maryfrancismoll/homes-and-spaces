package com.config;

import com.service.impl.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

/**
 * @author Maryfrancis Remo Moll
 *
 */
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

    /**
     * Description copied from interface: AuthorizationServerConfigurer
     * Configure the ClientDetailsService, e.g. declaring individual clients and their properties.
     * Note that password grant is not enabled (even if some clients are allowed it) unless an
     * AuthenticationManager is supplied to the
     * AuthorizationServerConfigurer.configure(AuthorizationServerEndpointsConfigurer).
     * At least one client, or a fully formed custom ClientDetailsService must be declared or the server will not start.
     *
     * @param configurer
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        configurer.inMemory().
                withClient(CLIENT_ID).
                secret(passwordEncoder.encode(SECRET)).
                authorizedGrantTypes(GRANT_TYPE).
                scopes(SCOPE_READ,SCOPE_WRITE).
                resourceIds(RESOURCE_ID).
                accessTokenValiditySeconds(EXPIRATION_TIME);
    }

    /**
     * Description copied from interface: AuthorizationServerConfigurer
     * Configure the non-security features of the Authorization Server endpoints, like token store,
     * token customizations, user approvals and grant types. You shouldn't need to do anything by default,
     * unless you need password grants, in which case you need to provide an AuthenticationManager.
     *
     * @param endpoints
     * @throws Exception
     */
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

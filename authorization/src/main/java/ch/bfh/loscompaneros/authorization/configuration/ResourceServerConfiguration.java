package ch.bfh.loscompaneros.authorization.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * Also configure this microservice as OAuth2 resource server to be able to provide
 * REST endpoints e.g. for user management.
 * Source: https://github.com/viadee/DeicheFuerDieInseln
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /*
         * Note:
         * The resources /oauth/token, /oauth/check_token and /oauth/token_key
         * are protected by OAuth2 authorization server security configuration.
         * Within this security configuration, those resources are not eligible for configuration. 
         */
        // @formatter:off
        http
            .authorizeRequests()
                .antMatchers(GET,"/user").hasAuthority("GET_USER")
                .antMatchers(POST,"/user").hasAuthority("POST_USER")
                .anyRequest().denyAll();
        // @formatter:on
    }

}

package com.guppy.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanghonggu on 2017. 7. 13..
 */

@Configuration
@EnableOAuth2Client
public class Oauth2Config extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/",
                        "/login/**",
                        "/guppy/dist/**",
                        "/api/board/list/**").permitAll().anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
                .and().logout()
                .logoutSuccessUrl("/").permitAll().and().csrf().disable()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
                .exceptionHandling() .authenticationEntryPoint(restAuthenticationEntryPoint);


        http.headers().frameOptions().disable();

        // @formatter:on
    }

    private Filter ssoFilter() {

        List<Filter> filters = new ArrayList<>();

        OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter(
                "/login/facebook");
        OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), oauth2ClientContext);
        facebookFilter.setRestTemplate(facebookTemplate);
        facebookFilter.setTokenServices(
                new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId()));
        facebookFilter.setAuthenticationSuccessHandler((request,response, authentication) -> response.sendRedirect("/facebook/complete"));
        facebookFilter.setAuthenticationFailureHandler((request,response, authentication) -> response.sendRedirect("/"));
        filters.add(facebookFilter);

        OAuth2ClientAuthenticationProcessingFilter kakaoFilter = new OAuth2ClientAuthenticationProcessingFilter(
                "/login/kakao");
        OAuth2RestTemplate kakaoTemplate = new OAuth2RestTemplate(kakao(), oauth2ClientContext);
        kakaoFilter.setRestTemplate(kakaoTemplate);
        kakaoFilter.setTokenServices(
                new UserInfoTokenServices(kakaoResource().getUserInfoUri(), kakao().getClientId()));
        kakaoFilter.setAuthenticationSuccessHandler((request,response, authentication) -> response.sendRedirect("/kakao/complete"));
        kakaoFilter.setAuthenticationFailureHandler((request,response, authentication) -> response.sendRedirect("/"));
        filters.add(kakaoFilter);

        CompositeFilter filter = new CompositeFilter();
        filter.setFilters(filters);

        return filter;
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    @ConfigurationProperties("facebook.client")
    public AuthorizationCodeResourceDetails facebook() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("facebook.resource")
    public ResourceServerProperties facebookResource() {
        return new ResourceServerProperties();
    }

    @Bean
    @ConfigurationProperties("kakao.client")
    public AuthorizationCodeResourceDetails kakao() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("kakao.resource")
    public ResourceServerProperties kakaoResource() {
        return new ResourceServerProperties();
    }


}

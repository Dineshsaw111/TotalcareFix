package com.totalcarefix.config;

import com.totalcarefix.filter.TokenVerificationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TokenVerificationFilter> tokenVerificationFilter(JwtDecoder jwtDecoder) {
        FilterRegistrationBean<TokenVerificationFilter> registrationBean = new FilterRegistrationBean<>();

        TokenVerificationFilter filter = new TokenVerificationFilter();
        filter.setJwtDecoder(jwtDecoder);

        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
//        registrationBean.addUrlPatterns("/cities/getAllUserCities");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return registrationBean;
    }
}

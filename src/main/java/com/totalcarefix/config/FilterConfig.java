package com.totalcarefix.config;//package com.totalcarefix.config;

import com.totalcarefix.filer.TokenVerificationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TokenVerificationFilter> tokenVerificationFilter() {
        FilterRegistrationBean<TokenVerificationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenVerificationFilter());

        // Exclude the endpoint for generating token from token verification
        registrationBean.addUrlPatterns("/*");
        registrationBean.addUrlPatterns("/login/auth"); // Exclude this endpoint
        registrationBean.addUrlPatterns("/cities/getAllUserCities");
        registrationBean.addUrlPatterns("/register");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}
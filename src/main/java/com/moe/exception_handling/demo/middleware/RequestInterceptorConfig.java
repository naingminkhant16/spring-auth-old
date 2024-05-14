package com.moe.exception_handling.demo.middleware;

import com.moe.exception_handling.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestInterceptorConfig implements WebMvcConfigurer {
    private final TokenRepository tokenRepository;

    @Autowired
    public RequestInterceptorConfig(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor(tokenRepository))
                .addPathPatterns("/api/**") // Intercept only requests to paths under /api/
                .excludePathPatterns("/api/auth/**") // Exclude certain paths
        ;
    }
}

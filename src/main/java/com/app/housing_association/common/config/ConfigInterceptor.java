package com.app.housing_association.common.config;

import com.app.housing_association.common.interceptor.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

    private final RequestHandler requestHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestHandler);
    }
}

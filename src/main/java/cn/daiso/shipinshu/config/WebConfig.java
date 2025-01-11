package cn.daiso.shipinshu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域的路径
                .allowedOrigins("http://localhost:5173") // 前端 URL
                .allowedMethods("*")
                .allowedHeaders("*") // 允许的请求头
                .exposedHeaders("Authorization") // 允许暴露的响应头
                .allowCredentials(true); // 允许携带 Cookie
    }
}


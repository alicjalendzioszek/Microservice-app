package com.example.lab4gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.netty.http.client.HttpClient;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("players",r -> r
                        .path("/players/**")
                        .and()
                        .method("GET", "PUT", "DELETE", "POST")
                        .uri("http://player:8081"))
                .route("teams", r -> r
                        .path("/teams/**")
                        .and()
                        .method("GET", "PUT", "DELETE", "POST")
                        .uri("http://team:8082"))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter(){
        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        corsConfig.addAllowedHeader("*");
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);

    }
}

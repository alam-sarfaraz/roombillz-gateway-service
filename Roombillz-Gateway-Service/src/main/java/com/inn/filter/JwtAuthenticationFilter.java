package com.inn.filter;

import com.inn.security.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.*;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RoleAccessConfig roleAccessConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        if(!routeValidator.isSecured.test(path)){
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Missing Authorization header");
        }

        String token = authHeader.substring(7);

        Claims claims = jwtUtil.getClaims(token);

        String username = claims.getSubject();
        List<String> roles = claims.get("roles", List.class);

        if(!roleAccessConfig.hasAccess(path, roles)){
            throw new RuntimeException("Access Denied");
        }

        ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .header("X-User-Name", username)
                .build();

        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
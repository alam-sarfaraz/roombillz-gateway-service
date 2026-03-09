package com.inn.security;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/login",
            "/swagger",
            "/v3/api-docs"
    );

    public Predicate<String> isSecured =
            uri -> openApiEndpoints
                    .stream()
                    .noneMatch(uri::contains);

}
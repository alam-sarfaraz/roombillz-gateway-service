package com.inn.security;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class RoleAccessConfig {

    private static final Map<String,List<String>> ROLE_ACCESS = new HashMap<>();

    static {

        ROLE_ACCESS.put("/api/v1/roomBillz", List.of("ADMIN","USER"));
        ROLE_ACCESS.put("/api/v1/notification", List.of("ADMIN"));
        ROLE_ACCESS.put("/api/v1/scheduler", List.of("ADMIN"));
        ROLE_ACCESS.put("/api/v1/document", List.of("ADMIN","USER"));
    }

    public boolean hasAccess(String path, List<String> roles){

        for (Map.Entry<String,List<String>> entry : ROLE_ACCESS.entrySet()) {

            if(path.startsWith(entry.getKey())){

                return roles.stream()
                        .anyMatch(entry.getValue()::contains);
            }
        }

        return true;
    }
}
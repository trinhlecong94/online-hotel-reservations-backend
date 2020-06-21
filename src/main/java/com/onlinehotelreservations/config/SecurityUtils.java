package com.onlinehotelreservations.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class SecurityUtils {
    public static String getCurrentUserEmail() {

        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal() instanceof Jwt) {

            final Jwt jwt = (Jwt) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            return jwt.getClaims().get("sub") != null ? jwt.getClaims().get("sub").toString() : null;
        }

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private SecurityUtils() {
    }
}

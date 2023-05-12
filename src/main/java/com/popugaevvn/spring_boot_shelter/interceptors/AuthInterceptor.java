package com.popugaevvn.spring_boot_shelter.interceptors;

import com.popugaevvn.spring_boot_shelter.exceptions.AuthorizedErrorException;
import com.popugaevvn.spring_boot_shelter.services.auth.AuthService;
import com.popugaevvn.spring_boot_shelter.utils.servletRequest.ServletRequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LogManager.getLogger(AuthInterceptor.class);

    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            LOGGER.error("Not valid token for request " + request.getRequestURI());
            throw new AuthorizedErrorException("Not authorized");
        }

        String cookieAuthService = ServletRequestUtils.getCookieByKey(request, "_auth_service_key");

        if (!authService.isAuthorize(token, cookieAuthService)) {
            LOGGER.error("User with token + " + token + "is not authorized");
            throw new AuthorizedErrorException("Not authorized");
        }

        return true;
    }
}

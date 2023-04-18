package com.popugaevvn.spring_boot_shelter.interceptors;

import com.popugaevvn.spring_boot_shelter.exceptions.AuthorizedErrorException;
import com.popugaevvn.spring_boot_shelter.services.auth.AuthService;
import com.popugaevvn.spring_boot_shelter.utils.servletRequest.ServletRequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) throw new AuthorizedErrorException("Not authorized");

        String cookieAuthService = ServletRequestUtils.getCookieByKey(request, "_auth_service_key");

        if (!authService.isAuthorize(token, cookieAuthService)) {
            throw new AuthorizedErrorException("Not authorized");
        }

        return true;
    }
}

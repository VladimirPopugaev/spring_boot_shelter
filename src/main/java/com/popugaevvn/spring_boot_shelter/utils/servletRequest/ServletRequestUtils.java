package com.popugaevvn.spring_boot_shelter.utils.servletRequest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class ServletRequestUtils {

    /**
     * Searches `HttpServletRequest` class request for a cookie with the given name.
     * @param request - HttpServletRequest from client with headers and cookies
     * @param cookieKey - string with key of needed cookie
     * @return - string value of needed cookie or empty string ("")
     */
    public static String getCookieByKey(HttpServletRequest request, String cookieKey) {
        Cookie[] cookies = request.getCookies();

        return Arrays.stream(cookies)
                .filter(cookie -> cookieKey.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny()
                .orElse("");

    }

}

package com.ry.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Web服务相关工具
 * 
 * @author cobe
 *
 */
public class WebUtil {
    private WebUtil() {}

    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes atts = RequestContextHolder.getRequestAttributes();
        return atts == null ? null : ((ServletRequestAttributes)atts).getRequest();
    }

    public static void setHttpRequestAttribute(String name, Object value) {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return;
        }
        request.setAttribute(name, value);
    }

    public static Object getHttpRequestAttribute(String name) {
        HttpServletRequest request = getHttpServletRequest();
        return request == null ? null : request.getAttribute(name);
    }

    public static String getHttpRequestParameter(String name) {
        HttpServletRequest request = getHttpServletRequest();
        return request == null ? null : request.getParameter(name);
    }

    public static HttpServletResponse getHttpServletResponse() {
        RequestAttributes atts = RequestContextHolder.getRequestAttributes();
        return atts == null ? null : ((ServletRequestAttributes)atts).getResponse();
    }

    public static HttpSession getHttpSession() {
        HttpServletRequest request = getHttpServletRequest();
        return request == null ? null : request.getSession();
    }

    public static Object getSessionAttribute(String key) {
        HttpSession session = getHttpSession();
        return session == null ? null : session.getAttribute(key);
    }

    public static void setSessionAttribute(String key, Object value) {
        HttpSession session = getHttpSession();
        if (session != null) {
            session.setAttribute(key, value);
        }
    }

    public static void removeSessionAttribute(String key) {
        HttpSession session = getHttpSession();
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    public static String getCookie(String name) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (name.equals(cookie.getName())) {
                    return StringUtils.trim(cookie.getValue());
                }
            }
        }
        return null;
    }

    public static String getHeader(String name) {
        HttpServletRequest request = getHttpServletRequest();
        return request == null ? null : request.getHeader(name);
    }

    public static int getIntHeader(String name) {
        HttpServletRequest request = getHttpServletRequest();
        return request == null ? null : request.getIntHeader(name);
    }

    public static long getDateHeader(String name) {
        HttpServletRequest request = getHttpServletRequest();
        return request == null ? null : request.getDateHeader(name);
    }

    public static void addCookie(String name, String value, String path, String domain, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setPath(path);
        cookie.setDomain(domain);
        cookie.setMaxAge(maxAgeInSeconds);

        addCookie(cookie);
    }

    public static void addCookie(Cookie cookie) {
        HttpServletResponse response = getHttpServletResponse();
        if (response == null) {
            return;
        }
        response.addCookie(cookie);
    }

    public static String getIp() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return null;
        }

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            return ip.split(",")[0];
        }
        if (ip == null) {
            ip = "";
        }
        return ip;
    }

    public static ContentCachingRequestWrapper getContentCachingRequestWrapper() {
        return (ContentCachingRequestWrapper)getHttpServletRequest();
    }
}

package com.github.lh.authentication;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-25
 */
@Slf4j
public class JwtFilter extends AccessControlFilter {

    public static final String DEFAULT_JWT_PARAM = "_jwt";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return null != getSubject(request, response) &&
                getSubject(request, response).isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String jwt = getJwt(request);
        if (StringUtils.hasText(jwt)) {
            AuthenticationToken token = new JwtToken(jwt, request.getRemoteHost());
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                return true;
            } catch (AuthenticationException e) {
                log.error(e.getMessage(), e);
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            }
        }
        return false;
    }


    protected String getJwt(ServletRequest request) {
        String jwt = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            // 先从cookie获取jwt
            jwt = Stream.of(httpServletRequest.getCookies())
                    .filter(cookie1 -> DEFAULT_JWT_PARAM.equalsIgnoreCase(cookie1.getName()))
                    .map(Cookie::getValue)
                    .findAny().orElse(null);
            if (StringUtils.hasText(jwt)) {
                return jwt;
            }
            // 取不到，再取header
            jwt = httpServletRequest.getHeader(DEFAULT_JWT_PARAM);
            if (StringUtils.hasText(jwt)) {
                return jwt;
            }

            // 仍然取不到，再从parameterMap里取
            jwt = httpServletRequest.getParameter(DEFAULT_JWT_PARAM);
            if (StringUtils.hasText(jwt)) {
                return jwt;
            }
        }
        return jwt;
    }
}

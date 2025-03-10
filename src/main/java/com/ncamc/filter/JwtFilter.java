package com.ncamc.filter;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.ncamc.config.WhiteListConfig;
import com.ncamc.internal.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@WebFilter(urlPatterns = "/*", filterName = "JwtFilter")
public class JwtFilter implements Filter {

    @Resource
    private WhiteListConfig urls;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static List<String> NOT_FILTER_ARRAY;

    public JwtFilter() {
        NOT_FILTER_ARRAY = Arrays.asList(Constant.NOT_FILTER_STR.split("\\|"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();
        log.info("Visit servlet path: {}", servletPath);

        if (servletPath.equals(StrUtil.SLASH) || ObjUtil.equals(servletPath, "/login")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 白名单过滤
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String url : urls.getUrls()) {
            if (pathMatcher.match(url, servletPath)) {
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        // swagger 过滤
        for (String str : NOT_FILTER_ARRAY) {
            if (servletPath.startsWith(str)) {
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        // 验证token信息
        String token = request.getHeader(Constant.JWT_HEADER_TOKEN);
        String userInfo = request.getHeader(Constant.JWT_REQUEST_USER);
        log.info("token====={} agentId======={}", token, userInfo);
        if (StrUtil.isNotBlank(token) && ObjUtil.isNotNull(redisTemplate.opsForValue().get(token)) && StrUtil.isNotBlank(userInfo)) {
            request.setAttribute(Constant.JWT_REQUEST_USER, userInfo);
            chain.doFilter(servletRequest, servletResponse);
            log.info("权限认证成功: {}", servletPath);
            return;
        }

        // 认证失败
        log.info("Unauthorized request at {}", servletPath);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(String.format("{code: %s, msg: %s, data: null}", HttpServletResponse.SC_UNAUTHORIZED, "认证失败"));
    }
}

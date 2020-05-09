package com.alfred.system.shiro;

import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.UUID;

/**
 * @program: 0812erp
 * @author: Alfred
 * @create: 2020年4月29日09:22:19
 **/
@Configuration
public class TokenWebSessionManager   extends DefaultWebSessionManager {

    private static final String TOKEN_HEADER = "TOKEN" ;

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        System.out.println(httpServletRequest.getRequestURI());
        //从头里面得到请求TOKEN 如果不存在就生成一个
        String header = WebUtils.toHttp(request).getHeader(TOKEN_HEADER);
        if (StringUtils.hasText(header)) {
            return header;
        }
        return UUID.randomUUID().toString();
    }
}

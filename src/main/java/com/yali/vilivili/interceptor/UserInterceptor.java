package com.yali.vilivili.interceptor;

import com.yali.vilivili.annotation.RequireLogin;
import com.yali.vilivili.model.vo.TokenInfoVO;
import com.yali.vilivili.service.ToolService;
import com.yali.vilivili.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Description 用户拦截器,需要去WebMvcConfig配置才能生效
 * @Date 2023/1/8 1:12
 * @Author pq
 */
@Component
@Slf4j
public class UserInterceptor implements AsyncHandlerInterceptor {

    @Value("${jwt.login.response.header}")
    private String jwtHeader;

    @Resource
    private ToolService toolService;

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
        if (handler instanceof HandlerMethod method) {
            RequireLogin requireLogin = method.getMethodAnnotation(RequireLogin.class);
            if (Objects.nonNull(requireLogin) && requireLogin.required()) {
                String token = request.getHeader(jwtHeader);
                if (StringUtils.isBlank(token)) {
                    toolService.failedResponse(response, "401", "未登录，请登录！");
                    return false;
                }
                TokenInfoVO tokenInfoVO = JwtUtils.decodeJwt(token);
                if (Objects.isNull(tokenInfoVO.getUserId())) {
                    toolService.failedResponse(response, "601", "token中用户id为空，请登录！");
                    return false;
                }
            }
            return true;
        }
        return true;
    }
}

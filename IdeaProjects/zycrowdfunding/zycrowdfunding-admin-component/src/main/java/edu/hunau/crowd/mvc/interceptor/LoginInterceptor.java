package edu.hunau.crowd.mvc.interceptor;

import edu.hunau.crowd.constant.CrowdConstant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 判断一下session中是否有登录的用户，若无，则直接重定向到登录页面
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object attribute = request.getSession().getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        if (attribute==null){
//            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
            response.sendRedirect(request.getContextPath()+"/admin/to/loginPage");
        }
        return true;
    }
}

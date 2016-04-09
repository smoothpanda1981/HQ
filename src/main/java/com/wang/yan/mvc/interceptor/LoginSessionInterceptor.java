package com.wang.yan.mvc.interceptor;

import com.wang.yan.mvc.HelloController;
import com.wang.yan.mvc.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ywang on 09.04.16.
 */
public class LoginSessionInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(LoginSessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.debug("Pre Handle");

//        logger.info("test : " + httpServletRequest.getRequestURI());
//        logger.info("test : " + httpServletRequest.getServletPath());
//        logger.info("test : " + httpServletRequest.getLocalAddr());
//        logger.info("test : " + httpServletRequest.getRequestURL());
//        logger.info("test : " + httpServletRequest.getPathTranslated());
//        logger.info("test : " + httpServletRequest.getPathInfo());
//        logger.info("test : " + httpServletRequest.getContextPath());
//        logger.info("test : " + httpServletRequest.getLocalName());
//        logger.info("test : " + httpServletRequest.getServerPort());
//        logger.info("test : " + httpServletRequest.getLocalPort());

        String servletPath = "";
        if (httpServletRequest.getServletPath().length() > 1) {
            servletPath = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getServletPath(), "");
        } else {
            servletPath = httpServletRequest.getRequestURL().toString();
        }
        httpServletRequest.setAttribute("homeMenuValue", servletPath);
        if (httpServletRequest.getServletPath().equals("/")) {
            return true;
        } else {
            if (httpServletRequest.getSession().getAttribute("login") == null) {
                httpServletResponse.sendRedirect(Utils.computePath(httpServletRequest));
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.debug("Post Handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.debug("After completion");
    }
}

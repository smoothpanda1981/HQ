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
        logger.debug("*********************************************");
        logger.debug("Pre Handle");

//        logger.info("test : " + httpServletRequest.getRequestURI());
        logger.info("Servlet Path : " + httpServletRequest.getServletPath());
//        logger.info("test : " + httpServletRequest.getLocalAddr());
//        logger.info("Request URL : " + httpServletRequest.getRequestURL().toString());
//        logger.info("test : " + httpServletRequest.getPathTranslated());
//        logger.info("test : " + httpServletRequest.getPathInfo());
//        logger.info("test : " + httpServletRequest.getContextPath());
//        logger.info("test : " + httpServletRequest.getLocalName());
//        logger.info("test : " + httpServletRequest.getServerPort());
//        logger.info("test : " + httpServletRequest.getLocalPort());

        String servletPath = "";
        if (httpServletRequest.getServletPath().length() > 1) {
            servletPath = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getServletPath(), "");
            logger.debug("in if : " + servletPath);
        } else {
            servletPath = httpServletRequest.getRequestURL().toString();
            logger.debug("in else : " + servletPath);
        }
        httpServletRequest.setAttribute("homeMenuValue", servletPath);


        if (httpServletRequest.getServletPath().equals("/")) {
            logger.debug("in if servletPath equals /.");
            return true;
        } else {
            logger.debug("in else servletPath not equals /.");
            if (httpServletRequest.getServletPath().equals("/signup")) {
                logger.debug("in else servletPath  in if servletPath equals /signup.");
                return true;
            } else {
                if (httpServletRequest.getSession().getAttribute("login") == null) {
                    logger.debug("in if session attribute login is null.");
                    httpServletResponse.sendRedirect(Utils.computePath(httpServletRequest));
                    return false;
                } else {
                    logger.debug("in else session login not null");
                    return true;
                }
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

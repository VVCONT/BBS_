package com.thorn.springboost.service;

import com.Thorn.model.userWithBLOBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录过滤
 */

public class InterceptorConfig implements HandlerInterceptor {


    private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);


    /**
     * 进入controller层之前拦截请求
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */

    @Override

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {


        log.info("---------------------开始进入请求地址拦截----------------------------");

        HttpSession session = httpServletRequest.getSession();

        if (!StringUtils.isEmpty(session.getAttribute("userName"))) {

            return true;

        } else {

            PrintWriter printWriter = httpServletResponse.getWriter();

            printWriter.write("{code:0,message:\"session is invalid,please login again!\"}");

            return false;

        }


    }


    @Override

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");

    }


    @Override

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        log.info("---------------视图渲染之后的操作-------------------------0");

    }

}

public class UserSessionFilter implements HandlerInterceptor {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 不拦截的url
        String[] notFilter = new String[]{"BBS/", "Login/", "user/user", "user/login",
                "user/reg", "Home"};

        // 请求的url
        String url = request.getRequestURI();

        userWithBLOBs user = (userWithBLOBs) request.getSession().getAttribute("userSession");
        Object obj = null;
        if (user != null && user.getUsername() != null)
            obj = user;
        if (null == obj) {
            boolean doFilter = chek(notFilter, url);
            if (doFilter) {

                // 如果session中不存在登录者实体，则弹出框提示重新登录
                PrintWriter out = response.getWriter();
                //String loginPage = request.getContextPath()+"/index.jsp";
                String loginPage = "/user/user";
                StringBuilder builder = new StringBuilder();
                builder.append("<script type=\"text/javascript\">");
                builder.append("window.top.location.href='");
                builder.append(loginPage);
                builder.append("';");
                builder.append("</script>");
                out.print(builder.toString());
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            //已登录不能返回这个页面
            if (url.indexOf("/user/user") == -1)
                filterChain.doFilter(request, response);
            else {
                //返回BBS主页面
                PrintWriter out = response.getWriter();
                //String loginPage = request.getContextPath()+"/index.jsp";
                String index = "/BBS/page";
                StringBuilder builder = new StringBuilder();
                builder.append("<script type=\"text/javascript\">");
                builder.append("window.top.location.href='");
                builder.append(index);
                builder.append("';");
                builder.append("</script>");
                out.print(builder.toString());
            }
        }

    }

    /**
     * @param notFilter 不拦截的url
     * @param url       ：请求的url
     * @return false：不拦截
     * true：拦截
     */
    public boolean chek(String[] notFilter, String url) {
        //url以css和js结尾的不进行拦截
        if (url.equals("/"))
            return false;
        if (url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".jpg") || url.endsWith("" +
                ".png")) {
            return false;
        }
        //含有notFilter中的任何一个则不进行拦截
        for (String s : notFilter) {
            logger.info(s);
            if (url.indexOf(s) != -1) {
                logger.info(s);
                return false;
            }
        }
        logger.info("拦截:" + url);
        return true;
    }

}


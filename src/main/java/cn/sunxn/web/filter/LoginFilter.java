package cn.sunxn.web.filter;

import cn.sunxn.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
         HttpServletRequest request = (HttpServletRequest) req;
         HttpServletResponse response = (HttpServletResponse) resp;
         String requestURI = request.getRequestURI();
//        System.out.println(requestURI);
         User user = (User) request.getSession().getAttribute("loginUser");
         if(requestURI.contains("findUserByPageServlet") || requestURI.contains("userManagerServlet")){
             if (user != null) {
                 if (user.getVersion() == 1) {
                     chain.doFilter(req, resp);
                 } else {
                     response.sendRedirect(request.getContextPath()+"/error/404.jsp");
                 }
             } else {
                 response.sendRedirect("login.jsp");
             }
         } else if (requestURI.contains("pollVote.jsp")){
             if (user != null) {
                 if (user.getStatus() == 2) {
                     chain.doFilter(req, resp);
                 } else {
                     response.sendRedirect(request.getContextPath()+"/error/404.jsp");
                 }
             } else {
                 response.sendRedirect("login.jsp");
             }
         } else if (requestURI.contains("participateVoteServlet")) {
             if (user != null) {
                 if (user.getStatus() != 0) {
                     chain.doFilter(req, resp);
                 } else {
                     response.sendRedirect(request.getContextPath()+"/error/404.jsp");
                 }
             } else {
                 response.sendRedirect("login.jsp");
             }
         } else {
             chain.doFilter(req, resp);
         }
    }

}

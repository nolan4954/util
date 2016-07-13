package com.best.bestsmart.sms.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CrosFilter extends OncePerRequestFilter {
	//extends OncePerRequestFilter
// extends UsernamePasswordAuthenticationFilter 
	static final String ORIGIN = "Origin";
	
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		
//	}
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {	
//	
//		 HttpServletResponse res = (HttpServletResponse) response;		 
//         HttpServletRequest req= (HttpServletRequest) request;
//         
//     	System.out.println(res.getHeader(ORIGIN));
//        System.out.println(req.getMethod());
//         
//         
//         if (!req.getMethod().equals("option")) {
//         res.setHeader("Access-Control-Allow-Origin", "http://localhost:8888");
//         res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//         res.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
//         res.setHeader("Access-Control-Expose-Headers", "x-requested-with");
//         res.setHeader("Access-Control-Allow-Credentials", "true");
//         chain.doFilter(request, response);		
//         }
//		
//	}
//	@Override
//	public void destroy() {
//		
//	}
	
	
	
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println(request.getHeader(ORIGIN));
        System.out.println(request.getMethod());
            response.setHeader("Access-Control-Allow-Origin", request.getHeader(ORIGIN));//* or origin as u prefer
            response.setHeader("Access-Control-Allow-Credentials", "true");
           response.setHeader("Access-Control-Allow-Headers",
                    request.getHeader("Access-Control-Request-Headers"));
    }	
	

}
	
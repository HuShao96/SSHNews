package MyStrutsFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

public class MyStrutsFilter extends StrutsPrepareAndExecuteFilter {
	 public void doFilter(ServletRequest request, ServletResponse response,
             FilterChain chain) throws IOException, ServletException {
         HttpServletRequest request2 = (HttpServletRequest) request;
         String url = request2.getRequestURI();        
         if (url.contains("/jsp/")) {            
             chain.doFilter(request, response);        
         }else{                    
             super.doFilter(request, response, chain);        
         }
     }

}

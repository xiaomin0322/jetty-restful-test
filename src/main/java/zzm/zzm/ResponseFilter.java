package zzm.zzm;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ResponseFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        WrapperResponse wrapperResponse = new WrapperResponse((HttpServletResponse) response);
        filterChain.doFilter(request, wrapperResponse);
        byte[] content = wrapperResponse.getContent();
            System.out.println("Response Content: {}"+new String(content) +" "+content.length);
            
            
            System.out.println("½âÑ¹ÂÞ>>>>>>>>>>>>>>>>>"+MessageGZIP.uncompressToString(content));
            
            System.out.println("response>>>>"+response);
            
        System.out.println(response.getClass().getName());   
        
        /*org.eclipse.jetty.server.Response response2 = (org.eclipse.jetty.server.Response)response;
        
        
        System.out.println(response2.getHttpOutput().getBufferSize());
        
        */
        
        
            
        ServletOutputStream out = response.getOutputStream();
        out.write(content);
        out.flush();
    }

    @Override
    public void init(FilterConfig paramFilterConfig) throws ServletException {
    }

}
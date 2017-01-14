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

    
    public void doFilter2(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
    	System.out.println("response>>>>"+response);
        //此时 ：Content-Encoding: 为 null 所以这里没问题
    	
        response.getWriter().println(">>>>>>>>>>>>>>>>>>");
        
        
        return ;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
    	 System.out.println("myfilter start");
        WrapperResponse wrapperResponse = new WrapperResponse((HttpServletResponse) response);
        
        
        /**
         * 1.request > filter1 > filter2
         * 2.dofilter
         * 3.response > filter2 > filter1
         * 
         */
        
        
        
        filterChain.doFilter(request, wrapperResponse);
        
        System.out.println("myfilter end");
        
        byte[] content = wrapperResponse.getContent();
            System.out.println("Response Content: {}"+new String(content) +" "+content.length);
            
            
            System.out.println("解压罗>>>>>>>>>>>>>>>>>"+MessageGZIP.uncompressToString(content));
            
            System.out.println("response>>>>"+response);
            
        System.out.println(response.getClass().getName());   
        
        /*org.eclipse.jetty.server.Response response2 = (org.eclipse.jetty.server.Response)response;
        
        
        System.out.println(response2.getHttpOutput().getBufferSize());
        
        */
        
       byte[] outbyte= MessageGZIP.compressToByte(MessageGZIP.uncompressToString(content)+"hahahhahahaha");
            
        ServletOutputStream out = response.getOutputStream();
        //這裡輸出要gzip編碼，應為：Content-Encoding: gzip，如果不編碼會有問題
        out.write(outbyte);
        out.flush();
    }

    @Override
    public void init(FilterConfig paramFilterConfig) throws ServletException {
    }

}
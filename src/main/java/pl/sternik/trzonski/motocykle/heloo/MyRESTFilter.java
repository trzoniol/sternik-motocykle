package pl.sternik.trzonski.motocykle.heloo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "/MyRESTFilter", 
//servletNames = { "HelloServlet" }, 
urlPatterns = { "/hello" })
public class MyRESTFilter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        System.out.println("--------------- test aaaaaaa ---------------");
        
        chain.doFilter(req, res);
        
        System.out.println("--------------- test bbbbbbb ---------------");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
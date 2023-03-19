package top.hanjie.filter;

import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/phone/*", filterName = "firstFilter")
@Order(1)
public class FirstFilter implements Filter {
    @Override
    @SneakyThrows
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        System.out.println("I'm first filter");
        chain.doFilter(req, res);
    }
}

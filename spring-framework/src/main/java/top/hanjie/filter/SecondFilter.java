package top.hanjie.filter;

import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/phone/*", filterName = "secondFilter")
@Order(2)
public class SecondFilter implements Filter {
    @Override
    @SneakyThrows
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        System.out.println("I'm second filter");
        chain.doFilter(req, res);
    }
}

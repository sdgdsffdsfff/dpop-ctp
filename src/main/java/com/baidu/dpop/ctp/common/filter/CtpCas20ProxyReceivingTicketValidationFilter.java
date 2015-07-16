package com.baidu.dpop.ctp.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;

import com.baidu.dpop.frame.core.filter.DpopFilterConfig;

/**
 * Ctp Cas20ProxyReceivingTicketValidationFilter
 * 
 * @author cgd
 * @date 2014年12月25日 上午11:17:53
 */
public class CtpCas20ProxyReceivingTicketValidationFilter implements Filter {

    private Cas20ProxyReceivingTicketValidationFilter cas20Fiter;

    // ------------------- 初始化逻辑 -----------------------
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        DpopFilterConfig dpopFilterConfig = new DpopFilterConfig(filterConfig);

        cas20Fiter = new Cas20ProxyReceivingTicketValidationFilter();
        this.cas20Fiter.init(dpopFilterConfig);
    }

    // ------------------- destory逻辑 ----------------------
    @Override
    public void destroy() {
        if (this.cas20Fiter != null) {
            this.cas20Fiter.destroy();
        }
    }

    /**
     * 过滤逻辑
     * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        // ----------------- 内部用户ticket验证 ----------------
        Object ticketObj = httpRequest.getParameter("ticket");
        if (ticketObj != null) {
            cas20Fiter.doFilter(request, response, chain);
        } else {
            chain.doFilter(request, response);
        }
        
        return;
    }

}

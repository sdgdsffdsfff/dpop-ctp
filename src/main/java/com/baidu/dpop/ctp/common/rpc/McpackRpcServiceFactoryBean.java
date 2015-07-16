package com.baidu.dpop.ctp.common.rpc;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

import com.baidu.rpc.client.McpackRpcProxy;
import com.baidu.rpc.exception.ExceptionHandler;

public class McpackRpcServiceFactoryBean<T> implements FactoryBean<T> {
	
	private final Class<T> rpcInterface;
    private final T rpcService;

    public McpackRpcServiceFactoryBean(Class<T> rpcInterface, String serviceEndpoint, String encoding) {
        this(rpcInterface, serviceEndpoint, encoding, new ExceptionHandler());
    }

    @SuppressWarnings("unchecked")
    public McpackRpcServiceFactoryBean(Class<T> rpcInterface, String serviceEndpoint, String encoding,
                                       ExceptionHandler exceptionHandler) {
        this.rpcInterface = rpcInterface;
        McpackRpcProxy rpcHandler = new McpackRpcProxy(serviceEndpoint, encoding, exceptionHandler);

        Object rpcProxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[] { rpcInterface },
                                                 rpcHandler);
        rpcService = (T) rpcProxy;
    }

    @Override
    public T getObject() throws Exception {
        return rpcService;
    }

    @Override
    public Class<?> getObjectType() {
        return rpcInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

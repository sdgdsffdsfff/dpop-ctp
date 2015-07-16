package com.baidu.dpop.ctp.common.rpc;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

import com.baidu.rpc.client.BasicAuthHandler;
import com.baidu.rpc.client.McpackRpcProxy;
import com.baidu.rpc.exception.ExceptionHandler;

public class McpackAuthRpcServiceFactoryBean<T> implements FactoryBean<T> {

    private final Class<T> rpcInterface;
    private final T rpcService;

    public McpackAuthRpcServiceFactoryBean(Class<T> rpcInterface, String serviceEndpoint, String username,
            String password, String encoding) {
        this(rpcInterface, serviceEndpoint, encoding, username, password, new ExceptionHandler());
    }

    @SuppressWarnings("unchecked")
    public McpackAuthRpcServiceFactoryBean(Class<T> rpcInterface, String serviceEndpoint, String encoding,
            String username, String password, ExceptionHandler exceptionHandler) {
        this.rpcInterface = rpcInterface;

        BasicAuthHandler ba = new BasicAuthHandler();
        ba.setUsername(username);
        ba.setPassword(password);

        McpackRpcProxy rpcHandler = new McpackRpcProxy(serviceEndpoint, encoding, exceptionHandler);
        rpcHandler.setAuthHandle(ba);

        Object rpcProxy =
                Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[] { rpcInterface }, rpcHandler);
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

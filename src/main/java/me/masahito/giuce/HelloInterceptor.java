package me.masahito.giuce;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

class HelloInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before proceed");
        Object result = invocation.proceed();
        System.out.println("after proceed");

        return result;
    }
}
package com.smartdevs.interceptor;

import com.smartdevs.annotation.MaxInputLength;
import com.smartdevs.exception.InputLengthException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by johnnym on 30/05/15.
 */
public class InputSizeInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (invocation.getArguments().length < 1 ||
                !(invocation.getArguments()[0].getClass().isAssignableFrom(String.class))) {

            throw new RuntimeException(
                    "The first argument of the annotated method MUST be a String or MUST be inherited from a String!");
        }

        int limit = invocation.getMethod().getAnnotation(MaxInputLength.class).value();
        int size = ((String) invocation.getArguments()[0]).length();

        if (size > limit) {
            throw new InputLengthException("Input size (" + size + ") exceeds limit (" + limit + ")!");
        }
        return invocation.proceed();
    }
}

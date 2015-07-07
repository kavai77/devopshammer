package com.smartdevs.interceptor;

import com.smartdevs.exception.InputLengthException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

import java.util.logging.Logger;

/**
 * Created by cskavai on 07/07/15.
 */
public class EmptyInputInterceptor implements MethodInterceptor {
    private static Logger LOGGER = Logger.getLogger(EmptyInputInterceptor.class.getName());

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (invocation.getArguments().length < 1 || !(invocation.getArguments()[0] instanceof String)) {
            LOGGER.warning("The first argument of the annotated method MUST be a String at " + invocation.getMethod().toGenericString());
            return invocation.proceed();
        }

        if (StringUtils.isBlank((String) invocation.getArguments()[0])) {
            throw new InputLengthException("Input should not be empty!");
        }
        return invocation.proceed();
    }
}

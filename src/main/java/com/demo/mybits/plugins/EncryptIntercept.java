package com.demo.mybits.plugins;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.demo.mybits.utils.EncryptUtils;

/**
 * @author lichaoshuai
 * Created on 2022-01-26
 */
@Intercepts({@Signature(
        type = ParameterHandler.class,
        method = "setParameters",
        args = {PreparedStatement.class})})
public class EncryptIntercept implements Interceptor {
    /**
     * @author lichaoshuai
     * Created on 2022-01-26
     */
    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        DefaultParameterHandler parameterHandler = (DefaultParameterHandler) invocation.getTarget();
        // 获取参数信息
        Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
        parameterField.setAccessible(true);
        Object parameterObject = parameterHandler.getParameterObject();
        if (Objects.isNull(parameterObject)) {
            return invocation.proceed();
        }
        System.out.println("current parameterObject type : " + parameterObject.getClass());
        //        if (parameterObject instanceof MapperMethod.ParamMap) {
        //            Map paramMap = (Map) parameterObject;
        //            sensitiveObject = paramMap.get("password");
        //        } else {
        //            sensitiveObject = parameterObject;
        //        }
        //        if (Objects.isNull(sensitiveObject)) {
        //            return invocation.proceed();
        //        }
        //如果参数需要加密 执行加密操作
        Field[] fields = parameterObject.getClass().getDeclaredFields();
        for (Field declaredField : fields) {
            if (declaredField.getName().contains("password")) {
                declaredField.setAccessible(true);
                String value = (String) declaredField.get(parameterObject);
                declaredField.set(parameterObject, EncryptUtils.encrypt(value));
            }
        }
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }



}

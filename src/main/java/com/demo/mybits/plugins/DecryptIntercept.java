package com.demo.mybits.plugins;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import com.demo.mybits.utils.EncryptUtils;

/**
 * @author lichaoshuai
 * Created on 2022-01-26
 */
@Intercepts({@Signature(
        type = ResultSetHandler.class,
        method = "handleResultSets",
        args = {Statement.class})})
public class DecryptIntercept implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object returnObject = invocation.proceed();
        try {
            if (returnObject instanceof List) {
                List resultList = (List) returnObject;
                if (CollectionUtils.isNotEmpty(resultList) && needDecrypt(resultList.get(0))) {
                    for (Object item : resultList) {
                        decrypt(item);
                    }
                }
            } else {
                if (Objects.nonNull(returnObject) && needDecrypt(returnObject)) {
                    decrypt(returnObject);
                }
            }
        } catch (Exception e) {
            System.out.println("解密失败");
            e.printStackTrace();
        }
        return returnObject;
    }

    private void decrypt(Object object) throws IllegalAccessException {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().contains("password")) {
                declaredField.setAccessible(true);
                if (Objects.nonNull(declaredField.get(object))) {
                    String value = declaredField.get(object).toString();
                    declaredField.set(object, EncryptUtils.decrypt(value));
                }
            }
        }

    }

    private boolean needDecrypt(Object object) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().contains("password")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}

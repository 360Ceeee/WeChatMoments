package com.example.wechatmoments.utils;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * Object 工具类
 */

public class ObjectClassUtil {

    @SuppressWarnings("unchecked")
    public static <T> T getNewInstance(Object object, int i) {
        if (object != null) {
            try {
                return ((Class<T>) ((ParameterizedType) (Objects.requireNonNull(object.getClass()
                        .getGenericSuperclass()))).getActualTypeArguments()[i])
                        .newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Object object, int i) {
        if (object != null) {
            return (T) ((ParameterizedType) Objects.requireNonNull(object.getClass()
                    .getGenericSuperclass()))
                    .getActualTypeArguments()[i];
        }
        return null;

    }

    public static <T> void checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
    }

}
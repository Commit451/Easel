package com.commit451.easel;

import java.lang.reflect.Field;

/**
 * Reflection is fun
 */
class ReflectionUtil {

    static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }
}

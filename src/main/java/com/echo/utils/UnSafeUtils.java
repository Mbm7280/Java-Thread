package com.echo.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;


public class UnSafeUtils {
    /**
     * UnSafe
     * @return
     */

    public static Unsafe getUnsafe() {
        try {
            // jni
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            return null;
        }
    }

}

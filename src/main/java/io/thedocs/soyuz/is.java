package io.thedocs.soyuz;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * Created by fbelov on 28.03.15.
 */
public class is {

    public static boolean t(String check) {
        return check != null && check.length() > 0;
    }

    public static boolean t(Boolean check) {
        return check != null && check;
    }

    public static boolean t(Collection check) {
        return check != null && check.size() > 0;
    }

    public static boolean t(Object[] check) {
        return check != null && check.length > 0;
    }

    public static boolean t(Map check) {
        return check != null && check.size() > 0;
    }

    public static boolean t(Long check) {
        return check != null && check != 0;
    }

    public static boolean t(Integer check) {
        return check != null && check != 0;
    }

    public static boolean t(Short check) {
        return check != null && check != 0;
    }

    public static boolean t(Byte check) {
        return check != null && check != 0;
    }

    public static boolean t(File file) {
        return file != null && file.exists();
    }

    public static boolean tt(String check) {
        return check != null && check.trim().length() > 0;
    }

}

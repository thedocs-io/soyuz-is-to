package io.thedocs.soyuz;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * Created by fbelov on 28.03.15.
 */
public class is {

    public static boolean t(@Nullable String check) {
        return check != null && check.length() > 0;
    }

    public static boolean t(@Nullable Boolean check) {
        return check != null && check;
    }

    public static boolean t(@Nullable Collection check) {
        return check != null && check.size() > 0;
    }

    public static boolean t(@Nullable Object[] check) {
        return check != null && check.length > 0;
    }

    public static boolean t(@Nullable Map check) {
        return check != null && check.size() > 0;
    }

    public static boolean t(@Nullable Long check) {
        return check != null && check != 0;
    }

    public static boolean t(@Nullable Integer check) {
        return check != null && check != 0;
    }

    public static boolean t(@Nullable Short check) {
        return check != null && check != 0;
    }

    public static boolean t(@Nullable Byte check) {
        return check != null && check != 0;
    }

    public static boolean t(@Nullable File file) {
        return file != null && file.exists();
    }

    public static boolean tt(@Nullable String check) {
        return check != null && check.trim().length() > 0;
    }

}

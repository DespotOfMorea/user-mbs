package org.vnuk.usermbs.util;

import android.text.TextUtils;

import androidx.databinding.InverseMethod;

public class BindingConverter {
    @InverseMethod("stringToInt")
    public static String intToString(int oldValue) {
        return String.valueOf(oldValue);
    }

    public static int stringToInt(String oldValue) {
        if (TextUtils.isEmpty(oldValue) || !TextUtils.isDigitsOnly(oldValue)) {
            return 0;
        }
        return Integer.parseInt(oldValue);
    }

    @InverseMethod("stringToLong")
    public static String longToString(Long oldValue) {
        return (oldValue != null) ? String.valueOf(oldValue) : "";
    }

    public static Long stringToLong(String oldValue) {
        if (TextUtils.isEmpty(oldValue) || !TextUtils.isDigitsOnly(oldValue)) {
            return 0L;
        }
        return Long.parseLong(oldValue);
    }
}

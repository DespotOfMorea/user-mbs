package org.vnuk.usermbs.util;

import android.text.TextUtils;

import androidx.databinding.InverseMethod;

public class BindingConverter {
    @InverseMethod("stringToInt")
    public static String intToString(int intValue) {
        return String.valueOf(intValue);
    }

    public static int stringToInt(String stringValue) {
        if (TextUtils.isEmpty(stringValue) || !TextUtils.isDigitsOnly(stringValue)) {
            return 0;
        }
        return Integer.parseInt(stringValue);
    }

    @InverseMethod("stringToLong")
    public static String longToString(Long longValue) {
        return (longValue != null) ? String.valueOf(longValue) : "";
    }

    public static Long stringToLong(String stringValue) {
        if (TextUtils.isEmpty(stringValue) || !TextUtils.isDigitsOnly(stringValue)) {
            return 0L;
        }
        return Long.parseLong(stringValue);
    }

    @InverseMethod("stringArrayToInt")
    public static String[] intArrayToString(int[] intArray) {
        if (intArray != null && intArray.length > 0) {
            String[] stringArray = new String[intArray.length];
            for (int i = 0; i < intArray.length; i++) {
                stringArray[i] = String.valueOf(intArray[i]);
            }
            return stringArray;
        }

        return new String[]{};
    }

    public static int[] stringArrayToInt(String[] stringArray) {
        if (stringArray != null && stringArray.length > 0) {
            int[] intArray = new int[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                intArray[i] = Integer.parseInt(stringArray[i]);
            }
            return intArray;
        }

        return new int[]{};
    }
}

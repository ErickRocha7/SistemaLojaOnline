package com.store.util;

import java.util.regex.Pattern;

public class StringUtil {
    private static final Pattern SKU_PATTERN = Pattern.compile("^PROD-\\d{4}$");

    public static boolean isValidSKU(String sku) {
        return SKU_PATTERN.matcher(sku).matches();
    }
}
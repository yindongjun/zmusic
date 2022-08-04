package com.example.zmusic.utils;

import com.github.ksuid.KsuidGenerator;

public class KsuidUtils {

    private KsuidUtils() {
    }

    public static String generateKsuid() {
        return KsuidGenerator.generate();
    }
}

package com.example.photoapp.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImgUtil {
    public static File mkFile(StringBuffer filePath, String name) {
        return new File(filePath + "/" + name);
    }

    public static String getUniqueFileName(String fileName) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(now) + fileName;
    }
}

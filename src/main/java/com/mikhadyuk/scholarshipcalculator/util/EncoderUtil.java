package com.mikhadyuk.scholarshipcalculator.util;

import org.apache.commons.codec.digest.DigestUtils;

public class EncoderUtil {
    public String encode(String data) {
        return DigestUtils.sha256Hex(data);
    }
}

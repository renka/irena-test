package com.irena.backend.utils;


import org.slf4j.Logger;

public class ErrorHandlingUtil {
    public static BaseResponse getErrorResponse(Exception e, Logger log) {
        log.error(e.getMessage());
        e.printStackTrace();
        return new BaseResponse<>("ERROR", e.getMessage());
    }
}

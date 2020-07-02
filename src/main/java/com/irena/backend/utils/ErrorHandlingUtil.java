package com.irena.backend.utils;


import org.slf4j.Logger;

import javax.servlet.http.HttpServletResponse;

public class ErrorHandlingUtil {
    public static BaseResponse getErrorResponse(Exception e, Logger log, HttpServletResponse response) {
        log.error(e.getMessage());
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new BaseResponse<>("ERROR", e.getMessage());
    }
}

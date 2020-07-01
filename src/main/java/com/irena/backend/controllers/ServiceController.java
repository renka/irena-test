package com.irena.backend.controllers;

import com.irena.backend.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service")
public class ServiceController {

    @Value("${project.version}")
    private String version;

    @GetMapping("version")
    public BaseResponse<String> version() {
        return new BaseResponse<>(version, "TBD");
    }

    @GetMapping("healthy")
    public BaseResponse<Boolean> health() {
        return new BaseResponse<>(true);
    }
}

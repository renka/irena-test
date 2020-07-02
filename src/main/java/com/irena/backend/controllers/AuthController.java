package com.irena.backend.controllers;

import com.irena.backend.dto.UserDto;
import com.irena.backend.services.UserService;
import com.irena.backend.utils.BaseResponse;
import com.irena.backend.utils.Constants;
import com.irena.backend.utils.ErrorHandlingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = Constants.UI_HOST, maxAge = Constants.CORS_AGE)
@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public BaseResponse<String> register(@RequestBody UserDto userDto, HttpServletResponse response) {
        try {
            userDto = userService.registerUser(userDto);
            return new BaseResponse<>(userDto.getPassword(), "User created successfully. Your password");
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, log, response);
        }
    }
    @PostMapping("login")
    public BaseResponse<UserDto> login(@RequestBody UserDto userDto, HttpServletResponse response) {
        try {
            userDto = userService.login(userDto);
            return new BaseResponse<>(userDto);
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, log, response);
        }
    }

}

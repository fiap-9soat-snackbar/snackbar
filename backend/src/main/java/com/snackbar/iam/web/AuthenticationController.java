package com.snackbar.iam.web;

import com.snackbar.iam.application.AuthenticationService;
import com.snackbar.iam.application.JwtService;
import com.snackbar.iam.application.UserService;
import com.snackbar.iam.domain.UserDetailsEntity;
import com.snackbar.iam.domain.UserEntity;
import com.snackbar.iam.web.dto.LoginResponse;
import com.snackbar.iam.web.dto.LoginUserDto;
import com.snackbar.iam.web.dto.RegisterUserDto;
import com.snackbar.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final OrderService orderService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserService userService, OrderService orderService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterUserDto registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        String jwtToken = null;
        UserDetailsEntity authenticatedUser = null;

        if (loginUserDto.getAnonymous() == true) {
            authenticatedUser = new UserDetailsEntity();
            jwtToken = jwtService.generateToken(authenticatedUser);
        } else {
            authenticatedUser = authenticationService.authenticate(loginUserDto);
            jwtToken = jwtService.generateToken(authenticatedUser);
        }


        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }


}

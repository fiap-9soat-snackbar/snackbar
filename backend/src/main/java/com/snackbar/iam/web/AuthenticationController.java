package com.snackbar.iam.web;

import com.snackbar.iam.application.AuthenticationService;
import com.snackbar.iam.application.JwtService;
import com.snackbar.iam.application.UserService;
import com.snackbar.iam.domain.UserDetailsEntity;
import com.snackbar.iam.domain.UserEntity;
import com.snackbar.iam.web.dto.LoginResponse;
import com.snackbar.iam.web.dto.LoginUserDto;
import com.snackbar.iam.web.dto.RegisterUserDto;
import com.snackbar.order.domain.model.Order;
import com.snackbar.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        UserDetailsEntity authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        Order order = new Order();
        var orderResult = orderService.createOrder(order);
        loginResponse.setOrderId(orderResult.getId());
        return ResponseEntity.ok(loginResponse);
    }


}

package com.ashjang.user.controller;

import com.ashjang.user.domain.SignUpForm;
import com.ashjang.user.service.customer.CustomerSignUpService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final CustomerSignUpService customerSignUpService;
    
    @PostMapping("/customer")
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm signUpForm) {
        return ResponseEntity.ok(customerSignUpService.signUp(signUpForm));
    }
}

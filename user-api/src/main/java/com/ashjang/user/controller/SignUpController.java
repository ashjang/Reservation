package com.ashjang.user.controller;

import com.ashjang.user.domain.SignUpForm;
import com.ashjang.user.service.customer.CustomerSignUpService;
import com.ashjang.user.service.seller.SellerSignUpService;
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
    private final SellerSignUpService sellerSignUpService;

    @ApiOperation(value = "고객용 회원가입", response = String.class)
    @PostMapping("/customer")
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm signUpForm) {
        return ResponseEntity.ok(customerSignUpService.signUp(signUpForm));
    }

    @ApiOperation(value = "셀러용 회원가입", response = String.class)
    @PostMapping("/seller")
    public ResponseEntity<String > sellerSignUp(@RequestBody SignUpForm signUpForm) {
        return ResponseEntity.ok(sellerSignUpService.signUp(signUpForm));
    }
}

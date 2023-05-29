package com.ashjang.user.service.customer;

import com.ashjang.user.domain.SignInForm;
import com.ashjang.user.domain.model.Customer;
import com.ashjang.user.domain.repository.CustomerRepository;
import com.ashjang.user.domain.token.JwtProvider;
import com.ashjang.user.domain.token.UserType;
import com.ashjang.user.exception.CustomException;
import com.ashjang.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerSignInService {
    private final CustomerRepository customerRepository;
    private final JwtProvider jwtProvider;

    // 고객용 로그인
    public String signIn(SignInForm signInForm) {
        // 1. 로그인
        Customer customer = isExistCustomer(signInForm.getPhone(), signInForm.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.SIGNIN_FAILED));
        // 2. 성공시, 토큰 발행 및 response
        return jwtProvider.createToken(customer.getPhone(), customer.getId(), UserType.CUSTOMER);
    }

    // 아이디, 비번 일치 확인
    public Optional<Customer> isExistCustomer(String phone, String password) {
        return customerRepository.findByPhone(phone).stream()
                .filter(customer -> customer.getPassword().equals(password)).findFirst();
    }
}

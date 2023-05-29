package com.ashjang.user.service.customer;

import com.ashjang.user.domain.SignUpForm;
import com.ashjang.user.domain.model.Customer;
import com.ashjang.user.domain.repository.CustomerRepository;
import com.ashjang.user.exception.CustomException;
import com.ashjang.user.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerSignUpService {

    private final CustomerRepository customerRepository;

    // 고객용 회원가입
    public String signUp(SignUpForm signUpForm) {
        if (isPhoneExists(signUpForm.getPhone())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        } else {
            customerRepository.save(Customer.from(signUpForm));
            return "customer - 회원가입 완료";
        }
    }

    // 휴대폰 번호 존재여부 확인
    public boolean isPhoneExists(String phone) {
        return customerRepository.findByPhone(phone).isPresent();
    }


}

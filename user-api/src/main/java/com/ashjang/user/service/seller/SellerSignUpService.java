package com.ashjang.user.service.seller;

import com.ashjang.user.domain.SignUpForm;
import com.ashjang.user.domain.model.Seller;
import com.ashjang.user.domain.repository.SellerRepository;
import com.ashjang.user.exception.CustomException;
import com.ashjang.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerSignUpService {
    private final SellerRepository sellerRepository;

    public String signUp(SignUpForm signUpForm) {
        if (isExistPhone(signUpForm.getPhone())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        } else {
            sellerRepository.save(Seller.from(signUpForm));
            return "seller - 회원가입 완료";
        }
    }

    // 휴대폰 존재 여부
    public boolean isExistPhone(String phone) {
        return sellerRepository.findByPhone(phone).isPresent();
    }
}

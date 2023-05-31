package com.ashjang.user.service.seller;

import com.ashjang.user.domain.SignInForm;
import com.ashjang.user.domain.model.Seller;
import com.ashjang.user.domain.repository.SellerRepository;
import com.ashjang.domain.token.JwtProvider;
import com.ashjang.domain.token.UserType;
import com.ashjang.user.exception.CustomException;
import com.ashjang.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerSignInService {

    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;

    // 셀러용 로그인
    public String signIn(SignInForm signInForm) {
        // 1. 로그인
        Seller seller = isExistSeller(signInForm.getPhone(), signInForm.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.SIGNIN_FAILED));
        // 2. 토큰 생성 및 반환
        return jwtProvider.createToken(seller.getPhone(), seller.getId(), UserType.SELLER);

    }

    // 휴대폰, 비밀번호 일치하는 회원 존재여부
    public Optional<Seller> isExistSeller(String phone, String password) {
        return sellerRepository.findByPhone(phone).stream()
                .filter(seller -> seller.getPassword().equals(password)).findFirst();
    }
}

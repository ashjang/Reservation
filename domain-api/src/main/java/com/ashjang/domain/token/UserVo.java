package com.ashjang.domain.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserVo {
    private Long id;
    private String phone;
    private String userType;
}

package com.ashjang.user.domain.model;

import com.ashjang.user.domain.SignUpForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Seller extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phone;

    private String password;

    public static Seller from(SignUpForm signUpForm) {
        return Seller.builder()
                .name(signUpForm.getName())
                .phone(signUpForm.getPhone())
                .password(signUpForm.getPassword())
                .build();
    }
}

package com.ashjang.user.domain.model;

import com.ashjang.user.domain.SignUpForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Customer extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phone;

    private String password;

    public static Customer from(SignUpForm signUpForm) {
        return Customer.builder()
                .name(signUpForm.getName())
                .phone(signUpForm.getPhone())
                .password(signUpForm.getPassword())
                .build();
    }
}

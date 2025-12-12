package com.capstone1.automatedpayroll.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    
    private Long userId;
    private String userEmail;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userWorkShift;
    private long userNumber;
    private LocalDateTime createdOn;
}

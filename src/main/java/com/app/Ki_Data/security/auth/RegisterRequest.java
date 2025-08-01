package com.app.Ki_Data.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String last_name;
    private String email;
    private String password;
    @Builder.Default
    private String role="USER";
}

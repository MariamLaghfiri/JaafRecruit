package org.example.user.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenDTO {
    private String refreshToken;
}

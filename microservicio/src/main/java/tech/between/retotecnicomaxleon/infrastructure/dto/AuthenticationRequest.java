package tech.between.retotecnicomaxleon.infrastructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationRequest {
    private String username;
    private String password;
}

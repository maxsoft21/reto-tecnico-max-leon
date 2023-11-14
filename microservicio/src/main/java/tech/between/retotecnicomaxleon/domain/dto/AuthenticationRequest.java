package tech.between.retotecnicomaxleon.domain.dto;

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

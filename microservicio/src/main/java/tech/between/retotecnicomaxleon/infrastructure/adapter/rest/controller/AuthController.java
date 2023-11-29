package tech.between.retotecnicomaxleon.infrastructure.adapter.rest.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.between.retotecnicomaxleon.application.service.UserDetailsServiceImpl;
import tech.between.retotecnicomaxleon.infrastructure.dto.AuthenticationRequest;
import tech.between.retotecnicomaxleon.infrastructure.dto.AuthenticationResponse;
import tech.between.retotecnicomaxleon.infrastructure.adapter.Utility.JwtUtils;
import tech.between.retotecnicomaxleon.infrastructure.adapter.exception.ValidateException;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(value = "/authenticate",produces = {"application/json", "application/json;charset=UTF-8", "application/stream+json"})
    public ResponseEntity<?> generarToken(@RequestBody AuthenticationRequest jwtRequest) {
        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (ValidateException exception) {
            log.error("Usuario no encontrado", exception.getStackTrace());
            return new ResponseEntity<>("El Usuario es invalido o no existe", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return ResponseEntity.ok()
                .headers(headers)
                .body(new AuthenticationResponse(token));
    }

    private void autenticar(String username, String password) throws ValidateException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException exception) {
            throw new ValidateException("USUARIO DESHABILITADO " + exception.getMessage());
        } catch (BadCredentialsException e) {
            throw new ValidateException("Credenciales invalidas " + e.getMessage());
        }
    }

}

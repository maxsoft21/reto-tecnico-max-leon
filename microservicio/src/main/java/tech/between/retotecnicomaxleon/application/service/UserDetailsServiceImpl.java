package tech.between.retotecnicomaxleon.application.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.between.retotecnicomaxleon.infrastructure.adapter.repository.UsuarioRepository;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usuarioRepository.findByUsername(username).doOnSuccess(usuario1 -> {
            if(usuario1 == null){
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
        }).block();
    }

}

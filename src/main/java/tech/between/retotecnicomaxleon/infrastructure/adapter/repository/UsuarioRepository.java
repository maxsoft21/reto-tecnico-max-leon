package tech.between.retotecnicomaxleon.infrastructure.adapter.repository;


import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Usuario;

public interface UsuarioRepository extends R2dbcRepository<Usuario,Long> {

    public Mono<Usuario> findByUsername(String username);

}

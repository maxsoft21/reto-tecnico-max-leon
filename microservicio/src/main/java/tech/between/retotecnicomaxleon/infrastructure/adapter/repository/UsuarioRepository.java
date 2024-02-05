package tech.between.retotecnicomaxleon.infrastructure.adapter.repository;


import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import tech.between.retotecnicomaxleon.infrastructure.entity.UsuarioEntity;

public interface UsuarioRepository extends R2dbcRepository<UsuarioEntity,Long> {

    Mono<UsuarioEntity> findByUsername(String username);

}

package tech.between.retotecnicomaxleon.infrastructure.adapter.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Fee;

public interface FeeRepository extends R2dbcRepository<Fee, Long> {
}

package tech.between.retotecnicomaxleon.infrastructure.adapter.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import tech.between.retotecnicomaxleon.infrastructure.entity.FeeEntity;

public interface FeeRepository extends R2dbcRepository<FeeEntity, Long> {
}

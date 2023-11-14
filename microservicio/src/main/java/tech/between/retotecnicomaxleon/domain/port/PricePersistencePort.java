package tech.between.retotecnicomaxleon.domain.port;

import reactor.core.publisher.Mono;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Fee;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Price;

import java.time.LocalDateTime;

public interface PricePersistencePort {

    Mono<Price> getPriceByBrandProductAndDate(Long brandId, Long productId, LocalDateTime applicationDate);
    Mono<Fee> getFeeById(Long priceList);
}

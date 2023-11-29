package tech.between.retotecnicomaxleon.domain.port;

import reactor.core.publisher.Mono;
import tech.between.retotecnicomaxleon.domain.model.Fee;
import tech.between.retotecnicomaxleon.domain.model.Price;

import java.time.LocalDateTime;

public interface PricePersistencePort {

    Mono<Price> getPriceByBrandProductAndDate(Long brandId, Long productId, LocalDateTime applicationDate);
    Mono<Fee> getFeeById(Long priceList);
}

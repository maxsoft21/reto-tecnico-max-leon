package tech.between.retotecnicomaxleon.application.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tech.between.retotecnicomaxleon.application.usecases.PriceService;
import tech.between.retotecnicomaxleon.domain.model.Price;
import tech.between.retotecnicomaxleon.domain.model.PriceResponse;
import tech.between.retotecnicomaxleon.domain.port.PricePersistencePort;
import tech.between.retotecnicomaxleon.infrastructure.adapter.Utility.Util;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceService {

    @Autowired
    PricePersistencePort pricePersistencePort;

    @Override
    @CircuitBreaker(name = "priceCircuitBreaker",fallbackMethod = "fallbackPriceResponseMock")
    public Mono<PriceResponse> getPriceByBrandProductAndDate(Long brandId, Long productId, LocalDateTime applicationDate) {
        Mono<Price> price = pricePersistencePort.getPriceByBrandProductAndDate(brandId, productId, applicationDate);
        return price.map(p ->
                             PriceResponse.builder()
                            .productId(p.getProductId())
                            .brandId(p.getBrandId())
                            .fee(p.getFee())
                            .startDate(Util.fromLocalDateTimeToString(p.getStartDate()))
                            .endDate(Util.fromLocalDateTimeToString(p.getEndDate()))
                            .priceFinal(p.getPrice())
                            .build()
                )
                .onErrorResume(throwable -> {
                    // Manejo de errores con Fallback
                    return fallbackPriceResponseMock(throwable);
                })
                .retry(3); // Intentos de Retry
    }


    private Mono<PriceResponse> fallbackPriceResponseMock( Throwable t) {
        return Mono.just(PriceResponse.builder().productId(0L).build());
    }
}

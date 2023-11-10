package tech.between.retotecnicomaxleon.application.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tech.between.retotecnicomaxleon.application.usecases.PriceService;
import tech.between.retotecnicomaxleon.domain.model.PriceResponse;
import tech.between.retotecnicomaxleon.domain.port.PricePersistencePort;
import tech.between.retotecnicomaxleon.infrastructure.adapter.Utility.Util;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Fee;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Price;

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
        return price.flatMap(p -> {
                    Mono<Fee> fee = pricePersistencePort.getFeeById(p.getPriceList());
                    return Mono.zip(Mono.just(p), fee, (priceResult, feeResult) -> PriceResponse.builder()
                            .productId(p.getProductId())
                            .brandId(p.getBrandId())
                            .fee(feeResult.getFeeValue())
                            .startDate(Util.fromLocalDateTimeToString(p.getStartDate()))
                            .endDate(Util.fromLocalDateTimeToString(p.getEndDate()))
                            .priceFinal(priceResult.getPrice())
                            .build()
                    );
                })
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

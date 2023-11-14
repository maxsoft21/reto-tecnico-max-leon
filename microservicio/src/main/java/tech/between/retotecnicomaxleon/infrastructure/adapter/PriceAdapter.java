package tech.between.retotecnicomaxleon.infrastructure.adapter;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import tech.between.retotecnicomaxleon.domain.port.PricePersistencePort;
import tech.between.retotecnicomaxleon.infrastructure.adapter.Utility.Util;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Fee;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Price;
import tech.between.retotecnicomaxleon.infrastructure.adapter.repository.FeeRepository;
import tech.between.retotecnicomaxleon.infrastructure.adapter.repository.PriceRepository;

import java.time.LocalDateTime;
@Repository
@AllArgsConstructor
public class PriceAdapter implements PricePersistencePort {
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    FeeRepository feeRepository;

    @Override
    public Mono<Price> getPriceByBrandProductAndDate(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepository.getPricesByBrandProductAndDate(brandId, productId, applicationDate);
    }

    @Override
    public Mono<Fee> getFeeById(Long priceList) {
        return feeRepository.findById(priceList);
    }
}

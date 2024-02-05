package tech.between.retotecnicomaxleon.infrastructure.adapter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import tech.between.retotecnicomaxleon.domain.model.Price;
import tech.between.retotecnicomaxleon.domain.port.PricePersistencePort;
import tech.between.retotecnicomaxleon.infrastructure.adapter.mapper.PriceMapper;
import tech.between.retotecnicomaxleon.infrastructure.adapter.repository.PriceRepository;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class PriceAdapter implements PricePersistencePort {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public Mono<Price> getPriceByBrandProductAndDate(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepository.getPricesByBrandProductAndDate(brandId, productId, applicationDate)
                .map(p -> this.priceMapper.toPrice(p));
    }

}

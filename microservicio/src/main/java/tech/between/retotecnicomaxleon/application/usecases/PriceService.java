package tech.between.retotecnicomaxleon.application.usecases;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tech.between.retotecnicomaxleon.domain.model.PriceResponse;

import java.time.LocalDateTime;

@Service
public interface PriceService {
   Mono<PriceResponse> getPriceByBrandProductAndDate(Long brandId, Long productId, LocalDateTime applicationDate);
}

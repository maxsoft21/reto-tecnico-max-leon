package tech.between.retotecnicomaxleon.infrastructure.adapter.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Price;

import java.time.LocalDateTime;

public interface PriceRepository extends R2dbcRepository<Price, Long> {
    @Query(value="SELECT P.* " +
            "FROM PRICES as P INNER JOIN FEES as F ON P.PRICE_LIST = F.PRICE_LIST " +
            "WHERE P.BRAND_ID = :brandId AND P.PRODUCT_ID = :productId AND P.START_DATE <= :applicationDate AND P.END_DATE >= :applicationDate " +
            "ORDER BY P.PRIORITY DESC " +
            "LIMIT 1")
    Mono<Price> getPricesByBrandProductAndDate(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("applicationDate") LocalDateTime applicationDate
    );
}

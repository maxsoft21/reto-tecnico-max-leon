package tech.between.retotecnicomaxleon.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import tech.between.retotecnicomaxleon.domain.model.PriceResponse;
import tech.between.retotecnicomaxleon.domain.port.PricePersistencePort;
import tech.between.retotecnicomaxleon.infrastructure.adapter.Utility.Util;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Fee;
import tech.between.retotecnicomaxleon.infrastructure.adapter.entity.Price;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceServiceImplTest {

    @InjectMocks
    PriceServiceImpl priceService;

    @Mock
    PricePersistencePort pricePersistencePort;

    @Test
    @DisplayName("when you search price by applicationDate, productId and brandId it returns sucsesfull")
    void whenSearchPriceByApplicationDateProductIdAndBrandIdReturnsSucsesfull() throws IOException {
        //GIVEN:
        PriceResponse responseJSON = Util.readFile("price-responseOK", "mock", PriceResponse.class);
        Mono<Price> monoPrice = Mono.just(Price.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1L)
                .startDate(Util.fromStringToLocalDateTime("2020-06-14-00.00.00"))
                .endDate(Util.fromStringToLocalDateTime("2020-12-31-23.59.59"))
                .price(new BigDecimal(35.5))
                .build());
        Mono<Fee> monoFee = Mono.just(Fee.builder()
                .priceList(1L)
                .feeValue(new BigDecimal(9))
                .build());
        //WHEN:
        when(pricePersistencePort.getPriceByBrandProductAndDate(anyLong(), anyLong(), any()))
                .thenReturn(monoPrice);
        when(pricePersistencePort.getFeeById(1L)).thenReturn(monoFee);

        //THEN:
        Mono<PriceResponse> response = priceService.getPriceByBrandProductAndDate(35455L, 1L,
                Util.fromStringToLocalDateTime("2020-06-14-00.00.00"));

        StepVerifier.create(response).assertNext(price -> assertEquals(responseJSON, price))
                .expectComplete()
                .verify();
    }

}
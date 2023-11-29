package tech.between.retotecnicomaxleon.infrastructure.adapter.rest.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;
import tech.between.retotecnicomaxleon.application.usecases.PriceService;
import tech.between.retotecnicomaxleon.domain.model.PriceResponse;
import tech.between.retotecnicomaxleon.infrastructure.adapter.Utility.Util;
import tech.between.retotecnicomaxleon.infrastructure.rest.PricesApi;


@Slf4j
@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    private final PriceService priceService;

    @ApiOperation(
            value = "Get Prices",
            authorizations = {@Authorization(value = "JWT")})
    @Override
    public Mono<ResponseEntity<PriceResponse>> pricesGet(@RequestParam String applicationDate,@RequestParam Long productId,@RequestParam Long brandId,
                                                         @ApiIgnore @RequestParam(required = false) ServerWebExchange exchange) {
        return priceService.getPriceByBrandProductAndDate(brandId,productId, Util.fromStringToLocalDateTime(applicationDate))
                .map(priceResponse -> ResponseEntity.ok(priceResponse))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

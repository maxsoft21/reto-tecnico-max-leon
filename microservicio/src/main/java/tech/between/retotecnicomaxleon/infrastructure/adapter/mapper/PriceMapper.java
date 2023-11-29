package tech.between.retotecnicomaxleon.infrastructure.adapter.mapper;

import org.mapstruct.*;
import tech.between.retotecnicomaxleon.domain.model.Price;
import tech.between.retotecnicomaxleon.infrastructure.entity.PriceEntity;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PriceMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            // @Mapping(source = "code", target = "code"),
            @Mapping(source = "brandId", target = "brandId"),
            @Mapping(source = "startDate", target = "startDate"),
            @Mapping(source = "endDate", target = "endDate"),
            @Mapping(source = "priceList", target = "priceList"),
            @Mapping(source = "productId", target = "productId"),
            @Mapping(source = "priority", target = "priority"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "curr", target = "curr"),
    }
    )
    Price toPrice(PriceEntity priceEntity);
    Iterable<Price> toPrices(Iterable<PriceEntity> priceEntity);

    @InheritInverseConfiguration
    PriceEntity toPriceEnity (Price price);
}

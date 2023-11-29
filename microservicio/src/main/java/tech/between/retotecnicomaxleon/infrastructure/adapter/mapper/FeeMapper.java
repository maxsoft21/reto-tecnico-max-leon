package tech.between.retotecnicomaxleon.infrastructure.adapter.mapper;

import org.mapstruct.*;
import tech.between.retotecnicomaxleon.domain.model.Fee;
import tech.between.retotecnicomaxleon.infrastructure.entity.FeeEntity;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FeeMapper {
    @Mappings({
            @Mapping(source = "priceList", target = "priceList"),
            // @Mapping(source = "code", target = "code"),
            @Mapping(source = "feeValue", target = "feeValue"),
    }
    )
    Fee toFee(FeeEntity feeEntity);
    Iterable<Fee> toFees(Iterable<FeeEntity> feeEntity);

    @InheritInverseConfiguration
    FeeEntity toFeeEnity (Fee fee);
}

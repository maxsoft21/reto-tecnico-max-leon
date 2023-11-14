package tech.between.retotecnicomaxleon.infrastructure.adapter.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "FEES")
public class Fee {
    @Id
    @Column("PRICE_LIST")
    private Long priceList;
    @Column("FEE_VALUE")
    private BigDecimal feeValue;
}

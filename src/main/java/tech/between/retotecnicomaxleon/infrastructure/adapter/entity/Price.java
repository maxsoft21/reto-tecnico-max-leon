package tech.between.retotecnicomaxleon.infrastructure.adapter.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "PRICES")
public class Price {
    @Id
    private Long id;
    @Column( "BRAND_ID")
    private Long brandId;
    @Column("START_DATE")
    private LocalDateTime startDate;
    @Column("END_DATE")
    private LocalDateTime endDate;
    @Column("PRICE_LIST")
    private Long priceList;
    @Column("PRODUCT_ID")
    private Long productId;
    @Column("PRIORITY")
    private Long priority;
    @Column("PRICE")
    private BigDecimal price;
    @Column("CURR")
    private String curr;
}

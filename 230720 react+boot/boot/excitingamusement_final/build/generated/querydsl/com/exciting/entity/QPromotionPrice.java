package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPromotionPrice is a Querydsl query type for PromotionPrice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPromotionPrice extends EntityPathBase<PromotionPrice> {

    private static final long serialVersionUID = 578451991L;

    public static final QPromotionPrice promotionPrice = new QPromotionPrice("promotionPrice");

    public final NumberPath<java.math.BigDecimal> discount = createNumber("discount", java.math.BigDecimal.class);

    public final StringPath promotion_content = createString("promotion_content");

    public final NumberPath<Integer> promotion_id = createNumber("promotion_id", Integer.class);

    public final StringPath promotion_img = createString("promotion_img");

    public final StringPath promotion_name = createString("promotion_name");

    public final NumberPath<Integer> ticket_price = createNumber("ticket_price", Integer.class);

    public QPromotionPrice(String variable) {
        super(PromotionPrice.class, forVariable(variable));
    }

    public QPromotionPrice(Path<? extends PromotionPrice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPromotionPrice(PathMetadata metadata) {
        super(PromotionPrice.class, metadata);
    }

}


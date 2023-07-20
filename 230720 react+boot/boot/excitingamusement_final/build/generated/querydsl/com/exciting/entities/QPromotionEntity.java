package com.exciting.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPromotionEntity is a Querydsl query type for PromotionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPromotionEntity extends EntityPathBase<PromotionEntity> {

    private static final long serialVersionUID = -80814541L;

    public static final QPromotionEntity promotionEntity = new QPromotionEntity("promotionEntity");

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final NumberPath<java.math.BigDecimal> discount = createNumber("discount", java.math.BigDecimal.class);

    public final StringPath promotion_content = createString("promotion_content");

    public final NumberPath<Integer> promotion_id = createNumber("promotion_id", Integer.class);

    public final StringPath promotion_img = createString("promotion_img");

    public final StringPath promotion_name = createString("promotion_name");

    public QPromotionEntity(String variable) {
        super(PromotionEntity.class, forVariable(variable));
    }

    public QPromotionEntity(Path<? extends PromotionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPromotionEntity(PathMetadata metadata) {
        super(PromotionEntity.class, metadata);
    }

}


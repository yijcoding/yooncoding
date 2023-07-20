package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPromotion is a Querydsl query type for Promotion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPromotion extends EntityPathBase<Promotion> {

    private static final long serialVersionUID = -1028527534L;

    public static final QPromotion promotion = new QPromotion("promotion");

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final NumberPath<Double> discount = createNumber("discount", Double.class);

    public final StringPath promotion_content = createString("promotion_content");

    public final NumberPath<Integer> promotion_id = createNumber("promotion_id", Integer.class);

    public final StringPath promotion_img = createString("promotion_img");

    public final StringPath promotion_name = createString("promotion_name");

    public final ListPath<Ticket, QTicket> tickets = this.<Ticket, QTicket>createList("tickets", Ticket.class, QTicket.class, PathInits.DIRECT2);

    public QPromotion(String variable) {
        super(Promotion.class, forVariable(variable));
    }

    public QPromotion(Path<? extends Promotion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPromotion(PathMetadata metadata) {
        super(Promotion.class, metadata);
    }

}


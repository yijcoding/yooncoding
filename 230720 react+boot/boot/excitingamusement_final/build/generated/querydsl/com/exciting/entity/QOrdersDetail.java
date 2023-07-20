package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrdersDetail is a Querydsl query type for OrdersDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrdersDetail extends EntityPathBase<OrdersDetail> {

    private static final long serialVersionUID = 2017945479L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrdersDetail ordersDetail = new QOrdersDetail("ordersDetail");

    public final NumberPath<Integer> order_detail_id = createNumber("order_detail_id", Integer.class);

    public final QOrders orders;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final QTicket ticket_id;

    public QOrdersDetail(String variable) {
        this(OrdersDetail.class, forVariable(variable), INITS);
    }

    public QOrdersDetail(Path<? extends OrdersDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrdersDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrdersDetail(PathMetadata metadata, PathInits inits) {
        this(OrdersDetail.class, metadata, inits);
    }

    public QOrdersDetail(Class<? extends OrdersDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orders = inits.isInitialized("orders") ? new QOrders(forProperty("orders"), inits.get("orders")) : null;
        this.ticket_id = inits.isInitialized("ticket_id") ? new QTicket(forProperty("ticket_id"), inits.get("ticket_id")) : null;
    }

}


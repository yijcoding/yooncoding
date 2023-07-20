package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrders is a Querydsl query type for Orders
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrders extends EntityPathBase<Orders> {

    private static final long serialVersionUID = -856822890L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrders orders = new QOrders("orders");

    public final BooleanPath checkorder = createBoolean("checkorder");

    public final BooleanPath checkrefund = createBoolean("checkrefund");

    public final com.exciting.login.entity.QMember member;

    public final ListPath<Mypoint, QMypoint> mypoints = this.<Mypoint, QMypoint>createList("mypoints", Mypoint.class, QMypoint.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> order_date = createDateTime("order_date", java.time.LocalDateTime.class);

    public final NumberPath<Integer> order_id = createNumber("order_id", Integer.class);

    public final ListPath<OrdersDetail, QOrdersDetail> ordersDetails = this.<OrdersDetail, QOrdersDetail>createList("ordersDetails", OrdersDetail.class, QOrdersDetail.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> use_point = createNumber("use_point", java.math.BigDecimal.class);

    public QOrders(String variable) {
        this(Orders.class, forVariable(variable), INITS);
    }

    public QOrders(Path<? extends Orders> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrders(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrders(PathMetadata metadata, PathInits inits) {
        this(Orders.class, metadata, inits);
    }

    public QOrders(Class<? extends Orders> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.exciting.login.entity.QMember(forProperty("member")) : null;
    }

}


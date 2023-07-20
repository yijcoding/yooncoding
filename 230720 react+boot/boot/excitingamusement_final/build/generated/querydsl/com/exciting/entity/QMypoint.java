package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMypoint is a Querydsl query type for Mypoint
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMypoint extends EntityPathBase<Mypoint> {

    private static final long serialVersionUID = 1940029651L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMypoint mypoint = new QMypoint("mypoint");

    public final NumberPath<java.math.BigDecimal> m_point = createNumber("m_point", java.math.BigDecimal.class);

    public final com.exciting.login.entity.QMember member;

    public final StringPath member_id = createString("member_id");

    public final QOrders orders;

    public final NumberPath<Integer> point_id = createNumber("point_id", Integer.class);

    public final NumberPath<java.math.BigDecimal> sum_point = createNumber("sum_point", java.math.BigDecimal.class);

    public QMypoint(String variable) {
        this(Mypoint.class, forVariable(variable), INITS);
    }

    public QMypoint(Path<? extends Mypoint> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMypoint(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMypoint(PathMetadata metadata, PathInits inits) {
        this(Mypoint.class, metadata, inits);
    }

    public QMypoint(Class<? extends Mypoint> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.exciting.login.entity.QMember(forProperty("member")) : null;
        this.orders = inits.isInitialized("orders") ? new QOrders(forProperty("orders"), inits.get("orders")) : null;
    }

}


package com.exciting.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInquery is a Querydsl query type for Inquery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquery extends EntityPathBase<Inquery> {

    private static final long serialVersionUID = -231410217L;

    public static final QInquery inquery = new QInquery("inquery");

    public final StringPath b_content = createString("b_content");

    public final StringPath b_title = createString("b_title");

    public final StringPath b_type = createString("b_type");

    public final NumberPath<Integer> inquiry_num = createNumber("inquiry_num", Integer.class);

    public final NumberPath<Integer> lev = createNumber("lev", Integer.class);

    public final StringPath member_id = createString("member_id");

    public final DateTimePath<java.util.Date> postdate = createDateTime("postdate", java.util.Date.class);

    public final NumberPath<Integer> ref = createNumber("ref", Integer.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public QInquery(String variable) {
        super(Inquery.class, forVariable(variable));
    }

    public QInquery(Path<? extends Inquery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInquery(PathMetadata metadata) {
        super(Inquery.class, metadata);
    }

}


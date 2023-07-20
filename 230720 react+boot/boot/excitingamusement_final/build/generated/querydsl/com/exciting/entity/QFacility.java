package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacility is a Querydsl query type for Facility
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacility extends EntityPathBase<Facility> {

    private static final long serialVersionUID = 493719316L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFacility facility = new QFacility("facility");

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final QAmusement amusement;

    public final StringPath f_img = createString("f_img");

    public final StringPath f_info = createString("f_info");

    public final StringPath f_location = createString("f_location");

    public final StringPath f_name = createString("f_name");

    public final StringPath f_note = createString("f_note");

    public final NumberPath<Integer> facility_id = createNumber("facility_id", Integer.class);

    public QFacility(String variable) {
        this(Facility.class, forVariable(variable), INITS);
    }

    public QFacility(Path<? extends Facility> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFacility(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFacility(PathMetadata metadata, PathInits inits) {
        this(Facility.class, metadata, inits);
    }

    public QFacility(Class<? extends Facility> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.amusement = inits.isInitialized("amusement") ? new QAmusement(forProperty("amusement")) : null;
    }

}


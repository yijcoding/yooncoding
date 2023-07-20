package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRidesEntity is a Querydsl query type for RidesEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRidesEntity extends EntityPathBase<RidesEntity> {

    private static final long serialVersionUID = 1455995949L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRidesEntity ridesEntity = new QRidesEntity("ridesEntity");

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final com.exciting.entities.QAmusementEntity2 amusement;

    public final StringPath r_img = createString("r_img");

    public final StringPath r_info = createString("r_info");

    public final StringPath r_location = createString("r_location");

    public final NumberPath<Integer> r_max_height = createNumber("r_max_height", Integer.class);

    public final NumberPath<Integer> r_min_height = createNumber("r_min_height", Integer.class);

    public final StringPath r_name = createString("r_name");

    public final StringPath r_note = createString("r_note");

    public final StringPath r_video = createString("r_video");

    public final NumberPath<Integer> rides_id = createNumber("rides_id", Integer.class);

    public QRidesEntity(String variable) {
        this(RidesEntity.class, forVariable(variable), INITS);
    }

    public QRidesEntity(Path<? extends RidesEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRidesEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRidesEntity(PathMetadata metadata, PathInits inits) {
        this(RidesEntity.class, metadata, inits);
    }

    public QRidesEntity(Class<? extends RidesEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.amusement = inits.isInitialized("amusement") ? new com.exciting.entities.QAmusementEntity2(forProperty("amusement")) : null;
    }

}


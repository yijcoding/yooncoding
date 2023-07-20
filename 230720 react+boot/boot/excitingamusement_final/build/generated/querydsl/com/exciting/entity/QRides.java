package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRides is a Querydsl query type for Rides
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRides extends EntityPathBase<Rides> {

    private static final long serialVersionUID = 806146986L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRides rides = new QRides("rides");

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final QAmusement amusement;

    public final StringPath r_img = createString("r_img");

    public final StringPath r_info = createString("r_info");

    public final StringPath r_location = createString("r_location");

    public final NumberPath<Integer> r_max_height = createNumber("r_max_height", Integer.class);

    public final NumberPath<Integer> r_min_height = createNumber("r_min_height", Integer.class);

    public final StringPath r_name = createString("r_name");

    public final StringPath r_note = createString("r_note");

    public final StringPath r_video = createString("r_video");

    public final NumberPath<Integer> rides_id = createNumber("rides_id", Integer.class);

    public QRides(String variable) {
        this(Rides.class, forVariable(variable), INITS);
    }

    public QRides(Path<? extends Rides> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRides(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRides(PathMetadata metadata, PathInits inits) {
        this(Rides.class, metadata, inits);
    }

    public QRides(Class<? extends Rides> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.amusement = inits.isInitialized("amusement") ? new QAmusement(forProperty("amusement")) : null;
    }

}


package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacilityEntity is a Querydsl query type for FacilityEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacilityEntity extends EntityPathBase<FacilityEntity> {

    private static final long serialVersionUID = -792346601L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFacilityEntity facilityEntity = new QFacilityEntity("facilityEntity");

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final com.exciting.entities.QAmusementEntity2 amusement;

    public final StringPath f_img = createString("f_img");

    public final StringPath f_info = createString("f_info");

    public final StringPath f_location = createString("f_location");

    public final StringPath f_name = createString("f_name");

    public final StringPath f_note = createString("f_note");

    public final NumberPath<Integer> facility_id = createNumber("facility_id", Integer.class);

    public QFacilityEntity(String variable) {
        this(FacilityEntity.class, forVariable(variable), INITS);
    }

    public QFacilityEntity(Path<? extends FacilityEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFacilityEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFacilityEntity(PathMetadata metadata, PathInits inits) {
        this(FacilityEntity.class, metadata, inits);
    }

    public QFacilityEntity(Class<? extends FacilityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.amusement = inits.isInitialized("amusement") ? new com.exciting.entities.QAmusementEntity2(forProperty("amusement")) : null;
    }

}


package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAmuseImageEntity is a Querydsl query type for AmuseImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAmuseImageEntity extends EntityPathBase<AmuseImageEntity> {

    private static final long serialVersionUID = -260034636L;

    public static final QAmuseImageEntity amuseImageEntity = new QAmuseImageEntity("amuseImageEntity");

    public final NumberPath<Integer> aimg_id = createNumber("aimg_id", Integer.class);

    public final StringPath aimg_name = createString("aimg_name");

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final StringPath url = createString("url");

    public QAmuseImageEntity(String variable) {
        super(AmuseImageEntity.class, forVariable(variable));
    }

    public QAmuseImageEntity(Path<? extends AmuseImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAmuseImageEntity(PathMetadata metadata) {
        super(AmuseImageEntity.class, metadata);
    }

}


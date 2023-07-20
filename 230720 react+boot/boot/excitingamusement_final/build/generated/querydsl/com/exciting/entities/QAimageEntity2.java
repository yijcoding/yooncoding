package com.exciting.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAimageEntity2 is a Querydsl query type for AimageEntity2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAimageEntity2 extends EntityPathBase<AimageEntity2> {

    private static final long serialVersionUID = 539932866L;

    public static final QAimageEntity2 aimageEntity2 = new QAimageEntity2("aimageEntity2");

    public final NumberPath<Integer> aimg_id = createNumber("aimg_id", Integer.class);

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final StringPath url = createString("url");

    public QAimageEntity2(String variable) {
        super(AimageEntity2.class, forVariable(variable));
    }

    public QAimageEntity2(Path<? extends AimageEntity2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAimageEntity2(PathMetadata metadata) {
        super(AimageEntity2.class, metadata);
    }

}


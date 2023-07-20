package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAmuseImage is a Querydsl query type for AmuseImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAmuseImage extends EntityPathBase<AmuseImage> {

    private static final long serialVersionUID = -148424591L;

    public static final QAmuseImage amuseImage = new QAmuseImage("amuseImage");

    public final NumberPath<Integer> aimg_id = createNumber("aimg_id", Integer.class);

    public final StringPath aimg_name = createString("aimg_name");

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final StringPath url = createString("url");

    public QAmuseImage(String variable) {
        super(AmuseImage.class, forVariable(variable));
    }

    public QAmuseImage(Path<? extends AmuseImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAmuseImage(PathMetadata metadata) {
        super(AmuseImage.class, metadata);
    }

}


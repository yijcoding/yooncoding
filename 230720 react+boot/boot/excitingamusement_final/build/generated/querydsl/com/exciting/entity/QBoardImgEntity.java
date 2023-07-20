package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardImgEntity is a Querydsl query type for BoardImgEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardImgEntity extends EntityPathBase<BoardImgEntity> {

    private static final long serialVersionUID = -957471503L;

    public static final QBoardImgEntity boardImgEntity = new QBoardImgEntity("boardImgEntity");

    public final NumberPath<Integer> announcement_num = createNumber("announcement_num", Integer.class);

    public final NumberPath<Integer> board_id = createNumber("board_id", Integer.class);

    public final StringPath boardimg = createString("boardimg");

    public final NumberPath<Integer> boardimg_num = createNumber("boardimg_num", Integer.class);

    public final NumberPath<Integer> inquiry_num = createNumber("inquiry_num", Integer.class);

    public QBoardImgEntity(String variable) {
        super(BoardImgEntity.class, forVariable(variable));
    }

    public QBoardImgEntity(Path<? extends BoardImgEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardImgEntity(PathMetadata metadata) {
        super(BoardImgEntity.class, metadata);
    }

}


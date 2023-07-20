package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardEntity is a Querydsl query type for BoardEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardEntity extends EntityPathBase<BoardEntity> {

    private static final long serialVersionUID = 844806232L;

    public static final QBoardEntity boardEntity = new QBoardEntity("boardEntity");

    public final StringPath b_content = createString("b_content");

    public final StringPath b_title = createString("b_title");

    public final StringPath b_type = createString("b_type");

    public final NumberPath<Integer> board_id = createNumber("board_id", Integer.class);

    public final NumberPath<Integer> favorite = createNumber("favorite", Integer.class);

    public final NumberPath<Integer> hate = createNumber("hate", Integer.class);

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final StringPath member_id = createString("member_id");

    public final DateTimePath<java.time.LocalDateTime> postdate = createDateTime("postdate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> visitcount = createNumber("visitcount", Integer.class);

    public QBoardEntity(String variable) {
        super(BoardEntity.class, forVariable(variable));
    }

    public QBoardEntity(Path<? extends BoardEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardEntity(PathMetadata metadata) {
        super(BoardEntity.class, metadata);
    }

}


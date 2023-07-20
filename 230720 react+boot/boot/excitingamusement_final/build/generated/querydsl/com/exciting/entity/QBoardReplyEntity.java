package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardReplyEntity is a Querydsl query type for BoardReplyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardReplyEntity extends EntityPathBase<BoardReplyEntity> {

    private static final long serialVersionUID = 1566849048L;

    public static final QBoardReplyEntity boardReplyEntity = new QBoardReplyEntity("boardReplyEntity");

    public final StringPath b_reply = createString("b_reply");

    public final NumberPath<Integer> board_id = createNumber("board_id", Integer.class);

    public final NumberPath<Integer> lev = createNumber("lev", Integer.class);

    public final StringPath member_id = createString("member_id");

    public final DateTimePath<java.time.LocalDateTime> postdate = createDateTime("postdate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> ref = createNumber("ref", Integer.class);

    public final NumberPath<Integer> reply_num = createNumber("reply_num", Integer.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public QBoardReplyEntity(String variable) {
        super(BoardReplyEntity.class, forVariable(variable));
    }

    public QBoardReplyEntity(Path<? extends BoardReplyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardReplyEntity(PathMetadata metadata) {
        super(BoardReplyEntity.class, metadata);
    }

}


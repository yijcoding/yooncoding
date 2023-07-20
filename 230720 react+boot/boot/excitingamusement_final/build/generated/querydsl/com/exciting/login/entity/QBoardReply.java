package com.exciting.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardReply is a Querydsl query type for BoardReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardReply extends EntityPathBase<BoardReply> {

    private static final long serialVersionUID = -1492496368L;

    public static final QBoardReply boardReply = new QBoardReply("boardReply");

    public final StringPath b_reply = createString("b_reply");

    public final NumberPath<Integer> board_id = createNumber("board_id", Integer.class);

    public final NumberPath<Integer> lev = createNumber("lev", Integer.class);

    public final StringPath member_id = createString("member_id");

    public final DateTimePath<java.util.Date> postdate = createDateTime("postdate", java.util.Date.class);

    public final NumberPath<Integer> ref = createNumber("ref", Integer.class);

    public final NumberPath<Integer> reply_num = createNumber("reply_num", Integer.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public QBoardReply(String variable) {
        super(BoardReply.class, forVariable(variable));
    }

    public QBoardReply(Path<? extends BoardReply> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardReply(PathMetadata metadata) {
        super(BoardReply.class, metadata);
    }

}


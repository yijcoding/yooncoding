package com.exciting.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -435740934L;

    public static final QBoard board = new QBoard("board");

    public final StringPath b_content = createString("b_content");

    public final StringPath b_title = createString("b_title");

    public final StringPath b_type = createString("b_type");

    public final NumberPath<Integer> board_id = createNumber("board_id", Integer.class);

    public final NumberPath<Integer> favorite = createNumber("favorite", Integer.class);

    public final NumberPath<Integer> hate = createNumber("hate", Integer.class);

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final StringPath member_id = createString("member_id");

    public final DateTimePath<java.util.Date> postdate = createDateTime("postdate", java.util.Date.class);

    public final NumberPath<Integer> visitcount = createNumber("visitcount", Integer.class);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}


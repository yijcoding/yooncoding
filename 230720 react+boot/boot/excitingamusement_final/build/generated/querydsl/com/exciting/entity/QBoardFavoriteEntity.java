package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardFavoriteEntity is a Querydsl query type for BoardFavoriteEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardFavoriteEntity extends EntityPathBase<BoardFavoriteEntity> {

    private static final long serialVersionUID = -240810988L;

    public static final QBoardFavoriteEntity boardFavoriteEntity = new QBoardFavoriteEntity("boardFavoriteEntity");

    public final NumberPath<Integer> board_id = createNumber("board_id", Integer.class);

    public final NumberPath<Integer> favorite = createNumber("favorite", Integer.class);

    public final NumberPath<Integer> favorite_num = createNumber("favorite_num", Integer.class);

    public final NumberPath<Integer> hate = createNumber("hate", Integer.class);

    public final StringPath member_id = createString("member_id");

    public QBoardFavoriteEntity(String variable) {
        super(BoardFavoriteEntity.class, forVariable(variable));
    }

    public QBoardFavoriteEntity(Path<? extends BoardFavoriteEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardFavoriteEntity(PathMetadata metadata) {
        super(BoardFavoriteEntity.class, metadata);
    }

}


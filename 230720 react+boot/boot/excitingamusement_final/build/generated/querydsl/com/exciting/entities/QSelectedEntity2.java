package com.exciting.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSelectedEntity2 is a Querydsl query type for SelectedEntity2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSelectedEntity2 extends EntityPathBase<SelectedEntity2> {

    private static final long serialVersionUID = -1839759007L;

    public static final QSelectedEntity2 selectedEntity2 = new QSelectedEntity2("selectedEntity2");

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final StringPath member_id = createString("member_id");

    public final NumberPath<Integer> selected_id = createNumber("selected_id", Integer.class);

    public QSelectedEntity2(String variable) {
        super(SelectedEntity2.class, forVariable(variable));
    }

    public QSelectedEntity2(Path<? extends SelectedEntity2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSelectedEntity2(PathMetadata metadata) {
        super(SelectedEntity2.class, metadata);
    }

}


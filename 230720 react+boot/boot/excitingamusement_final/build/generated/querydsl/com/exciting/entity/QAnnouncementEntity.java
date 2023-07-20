package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnnouncementEntity is a Querydsl query type for AnnouncementEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnnouncementEntity extends EntityPathBase<AnnouncementEntity> {

    private static final long serialVersionUID = 1998705467L;

    public static final QAnnouncementEntity announcementEntity = new QAnnouncementEntity("announcementEntity");

    public final NumberPath<Integer> announcement_num = createNumber("announcement_num", Integer.class);

    public final StringPath c_content = createString("c_content");

    public final StringPath c_title = createString("c_title");

    public final StringPath c_type = createString("c_type");

    public final DateTimePath<java.time.LocalDateTime> postdate = createDateTime("postdate", java.time.LocalDateTime.class);

    public QAnnouncementEntity(String variable) {
        super(AnnouncementEntity.class, forVariable(variable));
    }

    public QAnnouncementEntity(Path<? extends AnnouncementEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnnouncementEntity(PathMetadata metadata) {
        super(AnnouncementEntity.class, metadata);
    }

}


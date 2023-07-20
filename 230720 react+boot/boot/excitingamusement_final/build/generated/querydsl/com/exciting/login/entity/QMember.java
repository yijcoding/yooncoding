package com.exciting.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -317039354L;

    public static final QMember member = new QMember("member1");

    public final StringPath m_address = createString("m_address");

    public final StringPath m_birth = createString("m_birth");

    public final StringPath m_email = createString("m_email");

    public final StringPath m_gender = createString("m_gender");

    public final StringPath m_github_id = createString("m_github_id");

    public final StringPath m_image = createString("m_image");

    public final StringPath m_kakao_id = createString("m_kakao_id");

    public final StringPath m_name = createString("m_name");

    public final StringPath m_pass = createString("m_pass");

    public final StringPath m_phone = createString("m_phone");

    public final DateTimePath<java.util.Date> m_regidate = createDateTime("m_regidate", java.util.Date.class);

    public final StringPath member_id = createString("member_id");

    public final StringPath roles = createString("roles");

    public final StringPath token = createString("token");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}


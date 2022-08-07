package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -1153870424L;

    public static final QQuestion question = new QQuestion("question");

    public final StringPath audioUrl = createString("audioUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath level = createString("level");

    public final StringPath questionContent = createString("questionContent");

    public final StringPath topic = createString("topic");

    public QQuestion(String variable) {
        super(Question.class, forVariable(variable));
    }

    public QQuestion(Path<? extends Question> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestion(PathMetadata metadata) {
        super(Question.class, metadata);
    }

}



create table if not exists organisation
(
    id BIGSERIAL,
    name VARCHAR,
    description VARCHAR,
    CONSTRAINT pk_organisation primary key (id)
);

create table if not exists specialisation
(
    id BIGSERIAL,
    name VARCHAR,
    CONSTRAINT pk_specialisation primary key (id)
);

create table if not exists organisation_specialisation
(
    id BIGSERIAL,
    organisation_id BIGINT,
    specialisation_id BIGINT,
    CONSTRAINT pk_organisation_specialisation primary key (id),
    CONSTRAINT fk_organisation_id FOREIGN KEY (organisation_id) REFERENCES organisation (id) ON DELETE CASCADE,
    CONSTRAINT fk_specialisation_id FOREIGN KEY (specialisation_id) REFERENCES specialisation (id) ON DELETE CASCADE
);

create table if not exists photo_organisation
(
    id BIGSERIAL,
    type VARCHAR,
    data bytea,
    organisation_id BIGINT,
    CONSTRAINT pk_photo primary key (id),
    CONSTRAINT fk_photo_organisation_id  FOREIGN KEY (organisation_id) REFERENCES organisation (id) ON DELETE CASCADE
);


create table if not exists users
(
    id BIGSERIAL,
    nickname VARCHAR,
    number VARCHAR,
    password VARCHAR,
    role VARCHAR,
    organisation_id BIGINT,
    CONSTRAINT pk_users primary key (id),
    CONSTRAINT fk_user_organisation_id  FOREIGN KEY (organisation_id) REFERENCES organisation (id) ON DELETE SET NULL
);

create table if not exists tokens
(
    id BIGSERIAL,
    access_token VARCHAR,
    logged_out BOOLEAN,
    user_id BIGINT,
    CONSTRAINT pk_tokens primary key (id),
    CONSTRAINT fk_token_user_id  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);




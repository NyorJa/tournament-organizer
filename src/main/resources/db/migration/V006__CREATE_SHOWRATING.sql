CREATE TABLE show_rating
(
    id        BIGINT NOT NULL,
    group_id  BIGINT,
    rating_id BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB

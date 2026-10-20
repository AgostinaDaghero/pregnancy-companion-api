CREATE TABLE exercises (
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name              VARCHAR(150)  NOT NULL,
    short_description VARCHAR(300)  NOT NULL,
    instructions      TEXT          NOT NULL,
    category          VARCHAR(30)   NOT NULL,
    duration_minutes  INTEGER       NOT NULL CHECK (duration_minutes > 0),
    difficulty        VARCHAR(20)   NOT NULL,
    image_url         VARCHAR(500),
    video_url         VARCHAR(500),
    active            BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at        TIMESTAMPTZ   NOT NULL,
    updated_at        TIMESTAMPTZ   NOT NULL
);

CREATE TABLE exercise_trimesters (
    exercise_id BIGINT      NOT NULL REFERENCES exercises (id) ON DELETE CASCADE,
    trimester   VARCHAR(10) NOT NULL,
    PRIMARY KEY (exercise_id, trimester)
);

CREATE INDEX idx_exercises_active_category ON exercises (active, category);
CREATE INDEX idx_exercise_trimesters_trimester ON exercise_trimesters (trimester);

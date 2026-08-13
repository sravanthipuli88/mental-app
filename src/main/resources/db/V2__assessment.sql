CREATE TABLE assessment_question
(
 id BIGINT AUTO_INCREMENT PRIMARY KEY,

 type VARCHAR(20) NOT NULL,

 question TEXT NOT NULL,

 question_number INT NOT NULL,

 reverse_score BOOLEAN DEFAULT FALSE
);



CREATE TABLE assessment
(
 id BIGINT AUTO_INCREMENT PRIMARY KEY,

 user_id BIGINT,

 type VARCHAR(20) NOT NULL,

 score INT,

 severity VARCHAR(100),

 interpretation TEXT,

 created_at TIMESTAMP
);

CREATE TABLE assessment_summary
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    user_id BIGINT NOT NULL,

    phq9_score INT,

    gad7_score INT,

    pss10_score INT,

    who5_score INT,

    ai_summary TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE assessment_answer
(
 id BIGINT AUTO_INCREMENT PRIMARY KEY,

 assessment_id BIGINT,

 question_number INT,

 answer INT,

 CONSTRAINT fk_assessment_answer
 FOREIGN KEY(assessment_id)
 REFERENCES assessment(id)
);
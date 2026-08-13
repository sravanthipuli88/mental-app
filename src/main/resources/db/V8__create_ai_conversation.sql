CREATE TABLE ai_conversation
(
 id BIGINT AUTO_INCREMENT PRIMARY KEY,

 user_id BIGINT,

 assessment_type VARCHAR(50),

 user_prompt TEXT,

 ai_response TEXT,

 created_at TIMESTAMP
);
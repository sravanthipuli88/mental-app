INSERT INTO assessment_question
(
    type,
    question,
    question_number,
    reverse_score
)
VALUES

(
'PHQ9',
'Little interest or pleasure in doing things',
1,
false
),

(
'PHQ9',
'Feeling down, depressed, or hopeless',
2,
false
),

(
'PHQ9',
'Trouble falling or staying asleep, or sleeping too much',
3,
false
),

(
'PHQ9',
'Feeling tired or having little energy',
4,
false
),

(
'PHQ9',
'Poor appetite or overeating',
5,
false
),

(
'PHQ9',
'Feeling bad about yourself — or that you are a failure or have let yourself or your family down',
6,
false
),

(
'PHQ9',
'Trouble concentrating on things, such as reading the newspaper or watching television',
7,
false
),

(
'PHQ9',
'Moving or speaking so slowly that other people could have noticed, or being so fidgety/restless',
8,
false
),

(
'PHQ9',
'Thoughts that you would be better off dead, or of hurting yourself',
9,
false
);


INSERT INTO assessment
(
    user_id,
    type,
    score,
    severity,
    interpretation,
    created_at
)
VALUES
(
    1,
    'PHQ9',
    8,
    'Mild Depression',
    'PHQ-9 depression screening completed',
    CURRENT_TIMESTAMP
);


INSERT INTO assessment_answer
(
    assessment_id,
    question_number,
    answer
)
VALUES

(1,1,2),

(1,2,1),

(1,3,2),

(1,4,1),

(1,5,0),

(1,6,1),

(1,7,1),

(1,8,0),

(1,9,0);

-- GAD Question

INSERT INTO assessment_question
(
    type,
    question,
    question_number,
    reverse_score
)
VALUES

(
'GAD7',
'Feeling nervous, anxious, or on edge',
1,
false
),

(
'GAD7',
'Not being able to stop or control worrying',
2,
false
),

(
'GAD7',
'Worrying too much about different things',
3,
false
),

(
'GAD7',
'Trouble relaxing',
4,
false
),

(
'GAD7',
'Being so restless that it is hard to sit still',
5,
false
),

(
'GAD7',
'Becoming easily annoyed or irritable',
6,
false
),

(
'GAD7',
'Feeling afraid as if something awful might happen',
7,
false
);

-- PSS10 Questin
INSERT INTO assessment_question
(
    type,
    question,
    question_number,
    reverse_score
)
VALUES

(
'PSS10',
'In the last month, how often have you been upset because of something that happened unexpectedly?',
1,
false
),

(
'PSS10',
'In the last month, how often have you felt that you were unable to control the important things in your life?',
2,
false
),

(
'PSS10',
'In the last month, how often have you felt nervous and stressed?',
3,
false
),

(
'PSS10',
'In the last month, how often have you felt confident about your ability to handle your personal problems?',
4,
true
),

(
'PSS10',
'In the last month, how often have you felt that things were going your way?',
5,
true
),

(
'PSS10',
'In the last month, how often have you found that you could not cope with all the things that you had to do?',
6,
false
),

(
'PSS10',
'In the last month, how often have you been able to control irritations in your life?',
7,
true
),

(
'PSS10',
'In the last month, how often have you felt that you were on top of things?',
8,
true
),

(
'PSS10',
'In the last month, how often have you been angered because of things that were outside of your control?',
9,
false
),

(
'PSS10',
'In the last month, how often have you felt difficulties were piling up so high that you could not overcome them?',
10,
false
);

-- who5 questions

INSERT INTO assessment_question
(
    type,
    question,
    question_number,
    reverse_score
)
VALUES

(
'WHO5',
'I have felt cheerful and in good spirits',
1,
false
),

(
'WHO5',
'I have felt calm and relaxed',
2,
false
),

(
'WHO5',
'I have felt active and vigorous',
3,
false
),

(
'WHO5',
'I woke up feeling fresh and rested',
4,
false
),

(
'WHO5',
'My daily life has been filled with things that interest me',
5,
false
);
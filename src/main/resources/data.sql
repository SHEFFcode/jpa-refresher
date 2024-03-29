INSERT INTO COURSE (ID, NAME, CREATED_DATE, LAST_UPDATED_DATE) VALUES (10001, 'Course', sysdate(), sysdate());
INSERT INTO COURSE (ID, NAME, CREATED_DATE, LAST_UPDATED_DATE) VALUES (10002, 'Course', sysdate(), sysdate());
INSERT INTO COURSE (ID, NAME, CREATED_DATE, LAST_UPDATED_DATE) VALUES (10003, 'Course', sysdate(), sysdate());
INSERT INTO COURSE (ID, NAME, CREATED_DATE, LAST_UPDATED_DATE) VALUES (10004, 'Course', sysdate(), sysdate());

INSERT INTO PASSPORT (ID, NUMBER) VALUES (40001, 'E123456');
INSERT INTO PASSPORT (ID, NUMBER) VALUES (40002, 'E234567');
INSERT INTO PASSPORT (ID, NUMBER) VALUES (40003, 'E345678');
INSERT INTO PASSPORT (ID, NUMBER) VALUES (40004, 'E456789');

INSERT INTO STUDENT (ID, NAME, PASSPORT_ID) VALUES (20001, 'Jeremy', 40001);
INSERT INTO STUDENT (ID, NAME, PASSPORT_ID) VALUES (20002, 'Steve', 40002);
INSERT INTO STUDENT (ID, NAME, PASSPORT_ID) VALUES (20003, 'James', 40003);
INSERT INTO STUDENT (ID, NAME, PASSPORT_ID) VALUES (20004, 'Jerry', 40004);

INSERT INTO REVIEW (ID, RATING, DESCRIPTION, COURSE_ID) VALUES (50001, '4', 'Great course!', 10001);
INSERT INTO REVIEW (ID, RATING, DESCRIPTION, COURSE_ID) VALUES (50002, '4.5', 'Loved it!!', 10001);
INSERT INTO REVIEW (ID, RATING, DESCRIPTION, COURSE_ID) VALUES (50003, '3', 'Meh', 10002);
INSERT INTO REVIEW (ID, RATING, DESCRIPTION, COURSE_ID) VALUES (50004, '3.5', 'It was ok...', 10003);

INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES (20001, 10001);
INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES (20002, 10001);
INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES (20003, 10001);
INSERT INTO STUDENT_COURSE (STUDENT_ID, COURSE_ID) VALUES (20001, 10003);
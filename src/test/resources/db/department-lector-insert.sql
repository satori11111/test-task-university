INSERT INTO lector (id,name, degree, salary)
VALUES (1,'Andrii', 'ASSISTANT', 1400);
INSERT INTO lector (id,name, degree, salary)
VALUES (2,'Roman', 'PROFESSOR', 2200);
INSERT INTO department (id,name, head_of_department_id)
VALUES (1,'Science', 2);

INSERT INTO department_lector (department_id, lector_id)
VALUES (1, 1);
INSERT INTO department_lector (department_id, lector_id)
VALUES (1, 2);

INSERT INTO `sql7266199`.`student_groups`
(
`RokRozpoczecia`,
`Nazwa_StudentGrups`)
VALUES
(
'2018-10-04',
'test_grupa');

INSERT INTO `sql7266199`.`student_wykladowca`
(
`id_student_groups`,
`Login`,
`Password`,
`Nazwisko`,
`Imie`,
`email`,
`Nr_Indexu`)
VALUES
(
1,
'test1',
'admin1',
'testowy',
'admin',
'test@test.com',
'1');
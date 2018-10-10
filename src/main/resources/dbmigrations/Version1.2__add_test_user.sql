INSERT INTO `dbprojektor`.`student_groups`
(
`RokRozpoczecia`,
`Nazwa_StudentGrups`)
VALUES
(
'2018-10-04',
'test_grupa');

INSERT INTO `dbprojektor`.`student_wykladowca`
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
Use petraSpringLab2Test
drop table if exists artists;
create table artists
(
    id bigint NOT NULL,
    name varchar(255) NULL,
    genre varchar(25) NULL,
    PRIMARY KEY (id)
);
INSERT INTO [dbo].[artists] VALUES (1, 'Petra', 'Pop');
INSERT INTO [dbo].[artists] VALUES (2, 'Olle', 'Rock');
INSERT INTO [dbo].[artists] VALUES (3, 'Otto', 'Opera');
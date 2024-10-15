
INSERT INTO countries VALUES
    (1,
     'Latveria');
INSERT INTO countries VALUES
    (2,
     'Madripoor');
INSERT INTO countries VALUES
    (3,
     'Wakanda');

INSERT INTO cities VALUES
    (1,
     1,
     'Doomstadt');

INSERT INTO cities VALUES
    (2,
     1,
     'Doomsburg');

INSERT INTO cities VALUES
    (3,
     2,
     'Hightown');

INSERT INTO cities VALUES
    (4,
     2,
     'Lowtown');

INSERT INTO cities VALUES
    (5,
     3,
     'Birnin Zana');

INSERT INTO cities VALUES
    (6,
     3,
     'Birnin TChaka');

INSERT INTO citizens VALUES
    (1,
     1,
     'Victor von Doom');

INSERT INTO citizens VALUES
    (2,
     1,
     'Kristoff Vernard');

INSERT INTO citizens VALUES
    (3,
     2,
     'Caroline le Fey');

INSERT INTO citizens VALUES
    (4,
     3,
     'Andreas von Strucker');

INSERT INTO citizens VALUES
    (5,
     3,
     'Andrea von Strucker');

INSERT INTO citizens VALUES
    (6,
     4,
     'Sapphire Styx');

INSERT INTO citizens VALUES
    (7,
     5,
     'Tchalla');

INSERT INTO citizens VALUES
    (8,
     5,
     'Shuri');

INSERT INTO citizens VALUES
    (9,
     6,
     'Mbaku');

CREATE SEQUENCE countries_id_autoincrement_seq START 4 INCREMENT 1;
CREATE SEQUENCE citizens_id_autoincrement_seq START 10 INCREMENT 1;
CREATE SEQUENCE cities_id_autoincrement_seq START 7 INCREMENT 1;
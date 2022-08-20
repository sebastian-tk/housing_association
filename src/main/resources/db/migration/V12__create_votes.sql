CREATE TABLE IF NOT EXISTS votes (
    id INTEGER PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    title VARCHAR(60) NOT NULL unique,
    description TEXT NOT NULL,
    up_vote INTEGER DEFAULT 0,
    down_vote INTEGER DEFAULT 0,
    date_finish DATE,
    file_path VARCHAR(250) NOT NULL unique,
    finished BIT DEFAULT 0,
    building_id INTEGER,
    CONSTRAINT FK_VoteBuilding FOREIGN KEY (building_id) REFERENCES buildings (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users_votes (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id INTEGER,
    vote_id INTEGER,
    CONSTRAINT FK_Users_Votes_User FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
    CONSTRAINT FK_Users_Votes_Vote FOREIGN KEY (vote_id) REFERENCES votes (id) ON DELETE SET NULL
);

INSERT INTO votes (title,description,up_vote,down_vote,date_finish,file_path,building_id)
VALUES
("Prace remontowe i wykonanie dekoracji artystycznej",
 "Głosowanie w sprawie prac remontowych i wykonania dekoracji artystycznej na wybranych elewacjach kamienic w ramach projektu nadania dekoracji artystycznych zespołowi elewacji kamienic",
 20 ,4 , "2022-08-16","/1.pdf",1),
("Prace remontowe dachu",
 "Głosowanie w sprawie wykonania, zakresu i sposobu sfinansowania prac remontowych",
 11 ,13 , "2022-10-20","/2.pdf",2);

INSERT INTO users_votes (user_id, vote_id)
VALUES
    (2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),
    (14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),
    (26,2),(27,2),(28,2),(29,2),(30,2),(31,2),(32,2),(33,2),(34,2),(35,2),(36,2),(37,2),
    (38,2),(39,2),(40,2),(41,2),(42,2),(43,1),(44,1),(45,2),(46,2),(47,2),(48,2),(49,2);
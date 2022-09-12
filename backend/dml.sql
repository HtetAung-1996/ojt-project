USE movie_theater;
INSERT INTO movie_theater.user (id, name, gmail, password, role, access_count, start_join_date)
VALUES (1, 'admin', 'admin@gmail.com', '1111', 'admin', 0, CURRENT_DATE());
DELETE
FROM quest;
DELETE
FROM app_user;

insert into app_user
    (id, email, password, role)
values (1, 'user@questengine.com', 'password', 'USER');

insert into roles(role_name) values ('User');
insert into roles(role_name) values ('Admin');
insert into roles(role_name) values ('Guest');

insert into users(user_name, role_id) values ('Ivan', 1);
insert into users(user_name, role_id) values ('Stepan', 1);
insert into users(user_name, role_id) values ('Emelya', 2);

insert into rules(rule_name) values ('Read');
insert into rules(rule_name) values ('Write');
insert into rules(rule_name) values ('Delete');

insert into roles_rules(role_id, rule_id) values (1, 1);
insert into roles_rules(role_id, rule_id) values (1, 2);
insert into roles_rules(role_id, rule_id) values (2, 1);
insert into roles_rules(role_id, rule_id) values (2, 2);
insert into roles_rules(role_id, rule_id) values (2, 3);
insert into roles_rules(role_id, rule_id) values (3, 1);

insert into states(state_name) values ('Accepted');
insert into states(state_name) values ('Processing');
insert into states(state_name) values ('Rejected');
insert into states(state_name) values ('Closed');

insert into categories(categorie_name) values ('Planned');
insert into categories(categorie_name) values ('Unscheduled');
insert into categories(categorie_name) values ('Urgent');
insert into categories(categorie_name) values ('Emergency');

insert into items(item_name, user_id, categorie_id, state_id) values ('1 item', 3, 1, 3);
insert into items(item_name, user_id, categorie_id, state_id) values ('2 item', 1, 2, 4);
insert into items(item_name, user_id, categorie_id, state_id) values ('3 item', 2, 4, 1);

insert into comments(comment_name, item_id) values ('Accept', 2);
insert into comments(comment_name, item_id) values ('Reject', 1);
insert into comments(comment_name, item_id) values ('Close', 2);

insert into attachs(attach_name, item_id) values ('Passport scan', 1);
insert into attachs(attach_name, item_id) values ('Diploma scan', 3);
insert into attachs(attach_name, item_id) values ('Military ID scan', 2);

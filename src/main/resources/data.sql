INSERT INTO customers (first_name, last_name, email, customername, password, created_at, last_modified)
VALUES
    ('John', 'Doe', 'john@example.com', 'johndoe', 'password1', '2022-12-01 11:11:11', '2022-12-01 11:11:11'),
    ('Jane', 'Smith', 'jane@example.com', 'janesmith', 'password2', '2022-12-01 11:11:11', '2022-12-01 11:11:11'),
    ('Michael', 'Johnson', 'michael@example.com', 'michaelj', 'password3', '2022-12-01 11:11:11', '2022-12-01 11:11:11'),
    ('Emily', 'Davis', 'emily@example.com', 'emilyd', 'password4', '2022-12-01 11:11:11', '2022-12-01 11:11:11'),
    ('David', 'Wilson', 'david@example.com', 'davidw', 'password5', '2022-12-01 11:11:11', '2022-12-01 11:11:11');

# INSERT INTO customers (first_name, last_name, email, customername, password)
# VALUES
#     ('John', 'Doe', 'john@example.com', 'johndoe', 'password1'),
#     ('Jane', 'Smith', 'jane@example.com', 'janesmith', 'password2'),
#     ('Michael', 'Johnson', 'michael@example.com', 'michaelj', 'password3'),
#     ('Emily', 'Davis', 'emily@example.com', 'emilyd', 'password4'),
#     ('David', 'Wilson', 'david@example.com', 'davidw', 'password5');

INSERT INTO savings_goals (goal_name, current_amount_of_cash, target_amount_of_cash, start_date, end_date, customerid, createdat, last_modified)
VALUES
    ('Vacation Fund', 1000.00, 5000.00, '2023-06-01', '2023-12-31', 2, now(), now()),
    ('Home Renovation', 5000.00, 10000.00, '2023-07-01', '2024-06-30', 2, now(), now()),
    ('Education Fund', 2000.00, 10000.00, '2023-06-01', '2024-12-31', 3, now(), now()),
    ('Car Purchase', 0.00, 20000.00, '2023-06-01', '2024-06-30', 4, now(), now()),
    ('Emergency Fund', 500.00, 5000.00, '2023-06-01', '2023-12-31', 5, now(), now());

# INSERT INTO savings_goals (goal_name, current_amount_of_cash, target_amount_of_cash, start_date, end_date, userid)
# VALUES
#     ('Vacation Fund', 1000.00, 5000.00, '2023-06-01', '2023-12-31', 2),
#     ('Home Renovation', 5000.00, 10000.00, '2023-07-01', '2024-06-30', 2),
#     ('Education Fund', 2000.00, 10000.00, '2023-06-01', '2024-12-31', 3),
#     ('Car Purchase', 0.00, 20000.00, '2023-06-01', '2024-06-30', 4),
#     ('Emergency Fund', 500.00, 5000.00, '2023-06-01', '2023-12-31', 5);
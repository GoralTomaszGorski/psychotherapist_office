insert into
    patient_role (name, description)
values
    ('ADMIN', 'pełne uprawnienia'),   -- 1
    ('USER', 'podstawowe uprawnienia, możliwość przeglądania'),   -- 2
    ('PATIENT', 'podstawowe uprawnienia + możliwość zapisania się na terapię');   -- 3

insert into
    patient_roles (patient_id, role_id)
values
    (1, 1),
    (2, 2),
    (3, 3);
insert into
    patient_role (name, description)
values
    ('ADMIN', 'pełne uprawnienia'),   -- 1
    ('USER', 'podstawowe uprawnienia, możliwość przeglądania'),   -- 2
    ('PATIENT', 'podstawowe uprawnienia + możliwość zapisania się na terapię');   -- 3

insert into
    patient_roles (patient_id, role_id)
values
    (	1	,	1	)	,
    (	2	,	2	)	,
    (	3	,	3	)	,
    (	4	,	3	)	,
    (	5	,	3	)	,
    (	6	,	3	)	,
    (	7	,	3	)	,
    (	8	,	3	)	,
    (	9	,	3	)	,
    (	10	,	2	)	,
    (	11	,	2	)	,
    (	12	,	2	)	,
    (	13	,	2	)	,
    (	14	,	3	)	,
    (	15	,	2	)	,
    (	16	,	3	)	,
    (	17	,	3	)	,
    (	18	,	3	)	,
    (	19	,	3	)	,
    (	20	,	3	)	,
    (	21	,	3	)	,
    (	22	,	3	)	,
    (	23	,	2	)	,
    (	24	,	2	)	,
    (	25	,	2	)	,
    (	26	,	2	)	,
    (	27	,	3	)	,
    (	28	,	2	)	,
    (	29	,	3	)	,
    (	30	,	3	)	,
    (	31	,	3	)	,
    (	32	,	3	)	,
    (	33	,	3	)	,
    (	34	,	3	)	,
    (	35	,	3	)	,
    (	36	,	2	)	,
    (	37	,	2	)	,
    (	38	,	2	)	,
    (	39	,	2	)	,
    (	40	,	3	)	,
    (	41	,	2	)	,
    (	42	,	3	)	,
    (	43	,	3	)	,
    (	44	,	3	)	,
    (	45	,	3	)	,
    (	46	,	3	)	,
    (	47	,	3	)	,
    (	48	,	3	)	,
    (	49	,	2	)	,
    (	50	,	2	)	;


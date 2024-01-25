insert into
    patient_role (name, description)
values
    ('ADMIN', 'pełne uprawnienia'),   -- 1
    ('PATIENT', 'podstawowe uprawnienia + możliwość zapisania się na terapię'),   -- 2
    ('USER', 'podstawowe uprawnienia przeglądanie strony');   -- 3

insert into
    patient_roles (patient_id, role_id)
values
    (	1	,	1	)	,
    (	2	,	1	)	,
    (	3	,	2	)	,
    (	4	,	2	)	,
    (	5	,	2	)	,
    (	6	,	2	)	,
    (	7	,	2	)	,
    (	8	,	2	)	,
    (	9	,	2	)	,
    (	10	,	2	)	,
    (	11	,	2	)	,
    (	12	,	2	)	,
    (	13	,	2	)	,
    (	14	,	2	)	,
    (	15	,	2	)	,
    (	16	,	2	)	,
    (	17	,	2	)	,
    (	18	,	2	)	,
    (	19	,	2	)	,
    (	20	,	2	)	,
    (	21	,	2	)	,
    (	22	,	2	)	,
    (	23	,	2	)	,
    (	24	,	2	)	,
    (	25	,	2	)	,
    (	26	,	2	)	,
    (	27	,	2	)	,
    (	28	,	2	)	,
    (	29	,	2	)	,
    (	30	,	2	)	,
    (	31	,	2	)	,
    (	32	,	2	)	,
    (	33	,	2	)	,
    (	34	,	2	)	,
    (	35	,	2	)	,
    (	36	,	2	)	,
    (	37	,	2	)	,
    (	38	,	2	)	,
    (	39	,	2	)	,
    (	40	,	2	)	,
    (	41	,	2	)	,
    (	42	,	2	)	,
    (	43	,	2	)	,
    (	44	,	2	)	,
    (	45	,	2	)	,
    (	46	,	2	)	,
    (	47	,	3	)	,
    (	48	,	3	)	,
    (	49	,	3	)	,
    (	50	,	3	)	;


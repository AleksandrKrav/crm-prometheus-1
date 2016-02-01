--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2015-12-14 13:10:51

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 2285 (class 0 OID 36239)
-- Dependencies: 173
-- Data for Name: browser; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO browser VALUES (1, 'Firefox');
INSERT INTO browser VALUES (2, 'Chrome');


--
-- TOC entry 2338 (class 0 OID 0)
-- Dependencies: 172
-- Name: browser_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('browser_id_seq', 2, true);


--
-- TOC entry 2287 (class 0 OID 36247)
-- Dependencies: 175
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO comment VALUES (1, '2015-01-01 00:00:00', 'Comment1');
INSERT INTO comment VALUES (2, '2015-01-02 00:00:00', 'Comment2');
INSERT INTO comment VALUES (3, '2015-01-03 00:00:00', 'Comment3');
INSERT INTO comment VALUES (4, '2015-01-04 00:00:00', 'Comment4');


--
-- TOC entry 2339 (class 0 OID 0)
-- Dependencies: 174
-- Name: comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('comment_id_seq', 4, true);


--
-- TOC entry 2322 (class 0 OID 36415)
-- Dependencies: 210
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO roles VALUES (1, 'Public');
INSERT INTO roles VALUES (2, 'Manager');


--
-- TOC entry 2333 (class 0 OID 36461)
-- Dependencies: 221
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users VALUES (1, 'Ivan Ivanov', 'i.ivanov', '111', 'i.ivanov@gmail.com', 1, false);


--
-- TOC entry 2289 (class 0 OID 36258)
-- Dependencies: 177
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO company VALUES (1, 'Google', 'USA', 'google.com', 'info@gmail.com', 1, false);
INSERT INTO company VALUES (2, 'Apple', 'USA', 'apple.com', 'general@apple.com', 1, false);


--
-- TOC entry 2290 (class 0 OID 36268)
-- Dependencies: 178
-- Data for Name: company_comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO company_comment VALUES (1, 1);


--
-- TOC entry 2313 (class 0 OID 36375)
-- Dependencies: 201
-- Data for Name: file; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO file VALUES (1, '2015-01-01 00:00:00', 'file1.bin', NULL);
INSERT INTO file VALUES (2, '2015-01-02 00:00:00', 'file2.bin', NULL);
INSERT INTO file VALUES (3, '2015-01-03 00:00:00', 'file3.bin', NULL);


--
-- TOC entry 2291 (class 0 OID 36273)
-- Dependencies: 179
-- Data for Name: company_file; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO company_file VALUES (1, 1);


--
-- TOC entry 2340 (class 0 OID 0)
-- Dependencies: 176
-- Name: company_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('company_id_seq', 2, true);


--
-- TOC entry 2318 (class 0 OID 36399)
-- Dependencies: 206
-- Data for Name: phone_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO phone_type VALUES (1, 'Рабочий');
INSERT INTO phone_type VALUES (2, 'Раб.прямой');
INSERT INTO phone_type VALUES (3, 'Мобильный');
INSERT INTO phone_type VALUES (4, 'Факс');
INSERT INTO phone_type VALUES (5, 'Домашний');
INSERT INTO phone_type VALUES (6, 'Другой');


--
-- TOC entry 2316 (class 0 OID 36391)
-- Dependencies: 204
-- Data for Name: phone; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO phone VALUES (1, '222-22-22', 1);
INSERT INTO phone VALUES (2, '222-33-33', 2);
INSERT INTO phone VALUES (3, '444-44-44', 1);
INSERT INTO phone VALUES (4, '444-55-55', 2);
INSERT INTO phone VALUES (5, '666-77-77', 1);
INSERT INTO phone VALUES (6, '888-88-99', 1);


--
-- TOC entry 2292 (class 0 OID 36278)
-- Dependencies: 180
-- Data for Name: company_phone; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO company_phone VALUES (1, 1);
INSERT INTO company_phone VALUES (2, 1);
INSERT INTO company_phone VALUES (3, 2);
INSERT INTO company_phone VALUES (4, 2);


--
-- TOC entry 2324 (class 0 OID 36423)
-- Dependencies: 212
-- Data for Name: tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tag VALUES (1, 'Tag1');
INSERT INTO tag VALUES (2, 'Tag2');
INSERT INTO tag VALUES (3, 'Tag3');


--
-- TOC entry 2293 (class 0 OID 36283)
-- Dependencies: 181
-- Data for Name: company_tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO company_tag VALUES (1, 1);


--
-- TOC entry 2295 (class 0 OID 36290)
-- Dependencies: 183
-- Data for Name: connection_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO connection_history VALUES (1, '2015-01-01 00:00:00', 1, '10.10.10.1', 1);
INSERT INTO connection_history VALUES (2, '2015-12-31 00:00:00', 1, '20.20.20.20', 1);


--
-- TOC entry 2341 (class 0 OID 0)
-- Dependencies: 182
-- Name: connection_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('connection_history_id_seq', 2, true);


--
-- TOC entry 2320 (class 0 OID 36407)
-- Dependencies: 208
-- Data for Name: positions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO positions VALUES (1, 'Директор');
INSERT INTO positions VALUES (2, 'Менеджер');
INSERT INTO positions VALUES (3, 'Дизайнер');


--
-- TOC entry 2297 (class 0 OID 36298)
-- Dependencies: 185
-- Data for Name: contact; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contact VALUES (1, 'Den Popov', 1, 1, 'd.popov@gmail.com', 'd.popov', 1, false);
INSERT INTO contact VALUES (2, 'Petrov Roman', 1, 2, 'r.petrov@gmail.com', 'r.petrov', 1, false);


--
-- TOC entry 2298 (class 0 OID 36308)
-- Dependencies: 186
-- Data for Name: contact_comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contact_comment VALUES (1, 2);


--
-- TOC entry 2299 (class 0 OID 36313)
-- Dependencies: 187
-- Data for Name: contact_file; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contact_file VALUES (1, 2);


--
-- TOC entry 2342 (class 0 OID 0)
-- Dependencies: 184
-- Name: contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contact_id_seq', 2, true);


--
-- TOC entry 2300 (class 0 OID 36318)
-- Dependencies: 188
-- Data for Name: contact_phone; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contact_phone VALUES (5, 1);
INSERT INTO contact_phone VALUES (6, 2);


--
-- TOC entry 2301 (class 0 OID 36323)
-- Dependencies: 189
-- Data for Name: contact_tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contact_tag VALUES (1, 2);


--
-- TOC entry 2303 (class 0 OID 36330)
-- Dependencies: 191
-- Data for Name: currency; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO currency VALUES (1, '840', 'Доллар');
INSERT INTO currency VALUES (2, '978', 'Евро');
INSERT INTO currency VALUES (3, '980', 'Гривна');


--
-- TOC entry 2343 (class 0 OID 0)
-- Dependencies: 190
-- Name: currency_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('currency_id_seq', 3, true);


--
-- TOC entry 2310 (class 0 OID 36362)
-- Dependencies: 198
-- Data for Name: deal_status; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO deal_status VALUES (1, 'Первичный контакт');
INSERT INTO deal_status VALUES (2, 'Переговоры');
INSERT INTO deal_status VALUES (3, 'Принимают решение');
INSERT INTO deal_status VALUES (4, 'Согласование договора');
INSERT INTO deal_status VALUES (5, 'Успешно реализовано');
INSERT INTO deal_status VALUES (6, 'Закрыто');
INSERT INTO deal_status VALUES (7, 'Не реализовано');

--
-- TOC entry 2305 (class 0 OID 36338)
-- Dependencies: 193
-- Data for Name: deal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO deal VALUES (1, '2015-01-01 00:00:00', 'Закупка новой техники', 200000, 1, 1, 1,1, false);
INSERT INTO deal VALUES (2, '2015-01-02 00:00:00', 'Закупка ПО для офиса', 100000, 1, 1, 1,2, false);


--
-- TOC entry 2306 (class 0 OID 36345)
-- Dependencies: 194
-- Data for Name: deal_comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO deal_comment VALUES (1, 3);


--
-- TOC entry 2307 (class 0 OID 36350)
-- Dependencies: 195
-- Data for Name: deal_contact; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO deal_contact VALUES (1, 1);
INSERT INTO deal_contact VALUES (2, 2);


--
-- TOC entry 2308 (class 0 OID 36355)
-- Dependencies: 196
-- Data for Name: deal_file; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO deal_file VALUES (1, 3);


--
-- TOC entry 2344 (class 0 OID 0)
-- Dependencies: 192
-- Name: deal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('deal_id_seq', 2, true);


--
-- TOC entry 2345 (class 0 OID 0)
-- Dependencies: 197
-- Name: deal_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('deal_status_id_seq', 4, true);


--
-- TOC entry 2311 (class 0 OID 36368)
-- Dependencies: 199
-- Data for Name: deal_tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO deal_tag VALUES (1, 3);


--
-- TOC entry 2346 (class 0 OID 0)
-- Dependencies: 200
-- Name: file_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('file_id_seq', 3, true);


--
-- TOC entry 2314 (class 0 OID 36384)
-- Dependencies: 202
-- Data for Name: permition; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO permition VALUES (true, true, true, 1);


--
-- TOC entry 2347 (class 0 OID 0)
-- Dependencies: 203
-- Name: phone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('phone_id_seq', 6, true);


--
-- TOC entry 2348 (class 0 OID 0)
-- Dependencies: 205
-- Name: phone_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('phone_type_id_seq', 2, true);


--
-- TOC entry 2349 (class 0 OID 0)
-- Dependencies: 207
-- Name: positions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('positions_id_seq', 3, true);


--
-- TOC entry 2350 (class 0 OID 0)
-- Dependencies: 209
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('roles_id_seq', 2, true);


--
-- TOC entry 2351 (class 0 OID 0)
-- Dependencies: 211
-- Name: tag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tag_id_seq', 3, true);


--
-- TOC entry 2329 (class 0 OID 36445)
-- Dependencies: 217
-- Data for Name: task_status; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO task_status VALUES (1, 'Не начата');
INSERT INTO task_status VALUES (2, 'В работе');
INSERT INTO task_status VALUES (3, 'Выполнена');
INSERT INTO task_status VALUES (4, 'Отменена');


--
-- TOC entry 2331 (class 0 OID 36453)
-- Dependencies: 219
-- Data for Name: task_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO task_type VALUES (1, 'Выполнить');
INSERT INTO task_type VALUES (2, 'Звонок');
INSERT INTO task_type VALUES (3, 'Встреча');


--
-- TOC entry 2326 (class 0 OID 36431)
-- Dependencies: 214
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO task VALUES (1, '2015-01-01 00:00:00', '2015-01-10 00:00:00', 'Позвонить, договориться', 1, 1, 1, 1, 1, 1, false);


--
-- TOC entry 2327 (class 0 OID 36438)
-- Dependencies: 215
-- Data for Name: task_comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO task_comment VALUES (1, 4);


--
-- TOC entry 2352 (class 0 OID 0)
-- Dependencies: 213
-- Name: task_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('task_id_seq', 1, true);


--
-- TOC entry 2353 (class 0 OID 0)
-- Dependencies: 216
-- Name: task_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('task_status_id_seq', 4, true);


--
-- TOC entry 2354 (class 0 OID 0)
-- Dependencies: 218
-- Name: task_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('task_type_id_seq', 3, true);


--
-- TOC entry 2355 (class 0 OID 0)
-- Dependencies: 220
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 1, true);


-- Completed on 2015-12-14 13:10:51

--
-- PostgreSQL database dump complete
--


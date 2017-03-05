--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2017-02-21 13:24:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 178 (class 1259 OID 48358)
-- Name: state; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE state (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    statecode character varying(255)
);


ALTER TABLE state OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 48356)
-- Name: state_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE state_id_seq OWNER TO postgres;

--
-- TOC entry 2017 (class 0 OID 0)
-- Dependencies: 177
-- Name: state_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE state_id_seq OWNED BY state.id;


--
-- TOC entry 1899 (class 2604 OID 48361)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY state ALTER COLUMN id SET DEFAULT nextval('state_id_seq'::regclass);


--
-- TOC entry 2012 (class 0 OID 48358)
-- Dependencies: 178
-- Data for Name: state; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO state (id, code, name, statecode) VALUES (111, 'HUE-22', 'FLORIDA', 'FL');
INSERT INTO state (id, code, name, statecode) VALUES (112, 'HUE-23', 'TEXAS', 'TX');
INSERT INTO state (id, code, name, statecode) VALUES (113, 'HUE-24', 'WASHINGTON', 'WA');
INSERT INTO state (id, code, name, statecode) VALUES (114, 'HUE-25', 'LOS ANGELES', 'CA');
INSERT INTO state (id, code, name, statecode) VALUES (115, 'HUE-26', 'GEORGIA', 'GA');
INSERT INTO state (id, code, name, statecode) VALUES (116, 'HUE-27', 'NEW YORK', 'NY');
INSERT INTO state (id, code, name, statecode) VALUES (118, 'HUE-122', 'HAWAII', 'HI');
INSERT INTO state (id, code, name, statecode) VALUES (129, 'HUE-133', 'HAWAII', 'HI');
INSERT INTO state (id, code, name, statecode) VALUES (119, 'HUE-123', 'VIRGINIA', 'VA');
INSERT INTO state (id, code, name, statecode) VALUES (120, 'HUE-124', 'ARIZONA', 'AZ');
INSERT INTO state (id, code, name, statecode) VALUES (121, 'HUE-125', 'OREGON', 'OR');
INSERT INTO state (id, code, name, statecode) VALUES (122, 'HUE-126', 'VERMONT', 'VT');
INSERT INTO state (id, code, name, statecode) VALUES (124, 'HUE-128', 'CALIFORNIA', 'CA');
INSERT INTO state (id, code, name, statecode) VALUES (125, 'HUE-129', 'COLORADO', 'CO');
INSERT INTO state (id, code, name, statecode) VALUES (126, 'HUE-130', 'ALASKA', 'AK');
INSERT INTO state (id, code, name, statecode) VALUES (117, 'HUE-28', 'ALABAMA', 'AL');
INSERT INTO state (id, code, name, statecode) VALUES (127, 'HUE-131', 'ALABAMA', 'AL');
INSERT INTO state (id, code, name, statecode) VALUES (128, 'HUE-132', 'IDAHO', 'ID');
INSERT INTO state (id, code, name, statecode) VALUES (130, 'HUE-134', 'DELAWARE', 'DE');
INSERT INTO state (id, code, name, statecode) VALUES (131, 'HUE-135', 'NEVADA', 'NV');
INSERT INTO state (id, code, name, statecode) VALUES (132, 'HUE-136', 'MONTANA', 'MT');
INSERT INTO state (id, code, name, statecode) VALUES (133, 'HUE-137', 'INDIANA', 'IN');
INSERT INTO state (id, code, name, statecode) VALUES (134, 'HUE-138', 'NEBRASKA', 'NE');
INSERT INTO state (id, code, name, statecode) VALUES (135, 'HUE-139', 'MAINE', 'ME');
INSERT INTO state (id, code, name, statecode) VALUES (136, 'HUE-140', 'ARKANSAS', 'AR');
INSERT INTO state (id, code, name, statecode) VALUES (137, 'HUE-141', 'CONNECTICUT', 'CT');
INSERT INTO state (id, code, name, statecode) VALUES (138, 'HUE-142', 'DISTRICT OF COLUMBIA', 'DC');
INSERT INTO state (id, code, name, statecode) VALUES (139, 'HUE-143', 'ILLINOIS', 'IL');
INSERT INTO state (id, code, name, statecode) VALUES (140, 'HUE-144', 'IOWA', 'IA');
INSERT INTO state (id, code, name, statecode) VALUES (141, 'HUE-145', 'KENTUCKY', 'KY');
INSERT INTO state (id, code, name, statecode) VALUES (142, 'HUE-146', 'LOUISIANA', 'LA');
INSERT INTO state (id, code, name, statecode) VALUES (143, 'HUE-147', 'MARYLAND', 'MD');
INSERT INTO state (id, code, name, statecode) VALUES (144, 'HUE-148', 'MASSACHUSETTS', 'MA');
INSERT INTO state (id, code, name, statecode) VALUES (145, 'HUE-149', 'MICHIGAN', 'MI');
INSERT INTO state (id, code, name, statecode) VALUES (146, 'HUE-151', 'MINESOTA', 'MN');
INSERT INTO state (id, code, name, statecode) VALUES (147, 'HUE-152', 'MISSISSIPI', 'MS');
INSERT INTO state (id, code, name, statecode) VALUES (148, 'HUE-153', 'NEW HAMPSHIRE', 'NH');
INSERT INTO state (id, code, name, statecode) VALUES (149, 'HUE-154', 'NEW JERSEY', 'NJ');
INSERT INTO state (id, code, name, statecode) VALUES (150, 'HUE-155', 'NEW MEXICO', 'NM');
INSERT INTO state (id, code, name, statecode) VALUES (151, 'HUE-156', 'NORTH CAROLINA', 'NC');
INSERT INTO state (id, code, name, statecode) VALUES (152, 'HUE-157', 'NORTH DAKOTA', 'ND');
INSERT INTO state (id, code, name, statecode) VALUES (153, 'HUE-158', 'OHIO', 'OH');
INSERT INTO state (id, code, name, statecode) VALUES (154, 'HUE-159', 'OKLAHOMA', 'OK');
INSERT INTO state (id, code, name, statecode) VALUES (155, 'HUE-160', 'PENNSYLVANIA', 'PA');
INSERT INTO state (id, code, name, statecode) VALUES (156, 'HUE-161', 'RHODE ISLAND', 'RI');
INSERT INTO state (id, code, name, statecode) VALUES (157, 'HUE-162', 'SOUTH CAROLINA', 'SC');
INSERT INTO state (id, code, name, statecode) VALUES (158, 'HUE-163', 'SOUTH DAKOTA', 'SD');
INSERT INTO state (id, code, name, statecode) VALUES (159, 'HUE-164', 'TENNESEE', 'TN');
INSERT INTO state (id, code, name, statecode) VALUES (160, 'HUE-165', 'UTAH', 'UT');
INSERT INTO state (id, code, name, statecode) VALUES (161, 'HUE-166', 'WEST VIRGINIA', 'WV');
INSERT INTO state (id, code, name, statecode) VALUES (162, 'HUE-167', 'WISCONSIN', 'WI');
INSERT INTO state (id, code, name, statecode) VALUES (163, 'HUE-168', 'WYOMING', 'WY');
INSERT INTO state (id, code, name, statecode) VALUES (164, 'HUE-169', 'MISSOURI', 'MO');
INSERT INTO state (id, code, name, statecode) VALUES (123, 'HUE-127', 'KANSAS', 'KS');
INSERT INTO state (id, code, name, statecode) VALUES (165, 'MIA-180', 'KANSAS', 'KS');


--
-- TOC entry 2018 (class 0 OID 0)
-- Dependencies: 177
-- Name: state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('state_id_seq', 165, true);


--
-- TOC entry 1901 (class 2606 OID 48366)
-- Name: state_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY state
    ADD CONSTRAINT state_pkey PRIMARY KEY (id);


-- Completed on 2017-02-21 13:24:58

--
-- PostgreSQL database dump complete
--


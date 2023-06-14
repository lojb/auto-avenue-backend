--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-06-14 18:43:37

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 20567)
-- Name: adverts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adverts (
    id bigint NOT NULL,
    description character varying(255),
    image_url character varying(255),
    is_active boolean NOT NULL,
    manufacturer character varying(255),
    model character varying(255),
    price double precision NOT NULL,
    seller_id bigint,
    title character varying(255),
    transmission character varying(255),
    year integer NOT NULL
);


ALTER TABLE public.adverts OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 20566)
-- Name: adverts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.adverts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.adverts_id_seq OWNER TO postgres;

--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 214
-- Name: adverts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.adverts_id_seq OWNED BY public.adverts.id;


--
-- TOC entry 217 (class 1259 OID 20576)
-- Name: message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message (
    id bigint NOT NULL,
    message character varying(255),
    advert_id bigint,
    writer_id bigint
);


ALTER TABLE public.message OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 20575)
-- Name: message_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.message_id_seq OWNER TO postgres;

--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 216
-- Name: message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.message_id_seq OWNED BY public.message.id;


--
-- TOC entry 219 (class 1259 OID 20583)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    password character varying(255),
    role character varying(255),
    username character varying(255),
    wishlist_id bigint
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 20582)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 218
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 221 (class 1259 OID 20592)
-- Name: wishlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wishlist (
    id bigint NOT NULL,
    user_id bigint
);


ALTER TABLE public.wishlist OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 20591)
-- Name: wishlist_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wishlist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wishlist_id_seq OWNER TO postgres;

--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 220
-- Name: wishlist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wishlist_id_seq OWNED BY public.wishlist.id;


--
-- TOC entry 222 (class 1259 OID 20598)
-- Name: wishlist_wishlist_items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wishlist_wishlist_items (
    wishlist_id bigint NOT NULL,
    wishlist_items_id bigint NOT NULL
);


ALTER TABLE public.wishlist_wishlist_items OWNER TO postgres;

--
-- TOC entry 3192 (class 2604 OID 20570)
-- Name: adverts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adverts ALTER COLUMN id SET DEFAULT nextval('public.adverts_id_seq'::regclass);


--
-- TOC entry 3193 (class 2604 OID 20579)
-- Name: message id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message ALTER COLUMN id SET DEFAULT nextval('public.message_id_seq'::regclass);


--
-- TOC entry 3194 (class 2604 OID 20586)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3195 (class 2604 OID 20595)
-- Name: wishlist id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist ALTER COLUMN id SET DEFAULT nextval('public.wishlist_id_seq'::regclass);


--
-- TOC entry 3355 (class 0 OID 20567)
-- Dependencies: 215
-- Data for Name: adverts; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.adverts VALUES (1, '206 Description', 'https://res.cloudinary.com/dekwx51ge/image/upload/v1686754794/xrtqxewf48dito7n0nfe.jpg', true, 'Peugeot', '206', 999, 2, 'Peugeot 206', 'Manual', 1999);
INSERT INTO public.adverts VALUES (2, '992 Description', 'https://res.cloudinary.com/dekwx51ge/image/upload/v1686754836/x0qfcfhgrvsydgtn72lt.jpg', true, 'Porsche', '911 992 GT3RS', 300000, 2, 'Porsche 911 992 GT3RS', 'Sequential', 2022);
INSERT INTO public.adverts VALUES (3, 'Accord Description', 'https://res.cloudinary.com/dekwx51ge/image/upload/v1686754890/njbteynchpgqlgnlqvmz.jpg', true, 'Honda', 'Accord', 2000, 1, 'Honda Accord', 'Manual', 2001);


--
-- TOC entry 3357 (class 0 OID 20576)
-- Dependencies: 217
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3359 (class 0 OID 20583)
-- Dependencies: 219
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES (2, 'user@user.com', '$2a$10$W2ewubhq0cNFZ0NfMikE2.29/olI1gHzGAQu4kPBjQlF.RT4Wh812', 'USER', 'user', NULL);
INSERT INTO public.users VALUES (1, 'admin@admin.com', '$2a$10$QJoJ9o3JmZop0HA1foI7wOmMW1g7zxoFlA6PinebH1U4UASyL7e1.', 'ADMIN', 'admin', NULL);


--
-- TOC entry 3361 (class 0 OID 20592)
-- Dependencies: 221
-- Data for Name: wishlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.wishlist VALUES (1, 1);
INSERT INTO public.wishlist VALUES (2, 2);


--
-- TOC entry 3362 (class 0 OID 20598)
-- Dependencies: 222
-- Data for Name: wishlist_wishlist_items; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.wishlist_wishlist_items VALUES (1, 2);


--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 214
-- Name: adverts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.adverts_id_seq', 3, true);


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 216
-- Name: message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.message_id_seq', 1, false);


--
-- TOC entry 3374 (class 0 OID 0)
-- Dependencies: 218
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- TOC entry 3375 (class 0 OID 0)
-- Dependencies: 220
-- Name: wishlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wishlist_id_seq', 2, true);


--
-- TOC entry 3197 (class 2606 OID 20574)
-- Name: adverts adverts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adverts
    ADD CONSTRAINT adverts_pkey PRIMARY KEY (id);


--
-- TOC entry 3199 (class 2606 OID 20581)
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- TOC entry 3205 (class 2606 OID 20602)
-- Name: wishlist_wishlist_items uk_c8dnvoah9s0bllcgo7usrp1fs; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist_wishlist_items
    ADD CONSTRAINT uk_c8dnvoah9s0bllcgo7usrp1fs UNIQUE (wishlist_items_id);


--
-- TOC entry 3201 (class 2606 OID 20590)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3203 (class 2606 OID 20597)
-- Name: wishlist wishlist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_pkey PRIMARY KEY (id);


--
-- TOC entry 3210 (class 2606 OID 20623)
-- Name: wishlist_wishlist_items fk235lce0b0jhao70j0y9uskndg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist_wishlist_items
    ADD CONSTRAINT fk235lce0b0jhao70j0y9uskndg FOREIGN KEY (wishlist_items_id) REFERENCES public.adverts(id);


--
-- TOC entry 3206 (class 2606 OID 20603)
-- Name: message fka2em7ap4wv1aimkvwyvt3uu5m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fka2em7ap4wv1aimkvwyvt3uu5m FOREIGN KEY (advert_id) REFERENCES public.adverts(id);


--
-- TOC entry 3208 (class 2606 OID 20613)
-- Name: users fkiuu0ogk6dqneua0ileqdtiow5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkiuu0ogk6dqneua0ileqdtiow5 FOREIGN KEY (wishlist_id) REFERENCES public.wishlist(id);


--
-- TOC entry 3211 (class 2606 OID 20628)
-- Name: wishlist_wishlist_items fkooj0w5hpl1yic0wol48udajlj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist_wishlist_items
    ADD CONSTRAINT fkooj0w5hpl1yic0wol48udajlj FOREIGN KEY (wishlist_id) REFERENCES public.wishlist(id);


--
-- TOC entry 3207 (class 2606 OID 20608)
-- Name: message fkpgdddk6p4tp14m38he49xydnq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fkpgdddk6p4tp14m38he49xydnq FOREIGN KEY (writer_id) REFERENCES public.users(id);


--
-- TOC entry 3209 (class 2606 OID 20618)
-- Name: wishlist fktrd6335blsefl2gxpb8lr0gr7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT fktrd6335blsefl2gxpb8lr0gr7 FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2023-06-14 18:43:39

--
-- PostgreSQL database dump complete
--


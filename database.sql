--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

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
-- Name: playersinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.playersinfo (
    name text NOT NULL,
    wins integer,
    losess integer
);


ALTER TABLE public.playersinfo OWNER TO postgres;

--
-- Name: replay; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.replay (
    id integer NOT NULL,
    name text,
    p_date date,
    moves integer[]
);


ALTER TABLE public.replay OWNER TO postgres;

--
-- Data for Name: playersinfo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.playersinfo (name, wins, losess) FROM stdin;
\.


--
-- Data for Name: replay; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.replay (id, name, p_date, moves) FROM stdin;
\.


--
-- Name: playersinfo playersinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playersinfo
    ADD CONSTRAINT playersinfo_pkey PRIMARY KEY (name);


--
-- Name: replay replay_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.replay
    ADD CONSTRAINT replay_pkey PRIMARY KEY (id);


--
-- Name: replay replay_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.replay
    ADD CONSTRAINT replay_name_fkey FOREIGN KEY (name) REFERENCES public.playersinfo(name);


--
-- PostgreSQL database dump complete
--


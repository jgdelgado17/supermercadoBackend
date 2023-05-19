-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

COMMENT ON SCHEMA public IS 'standard public schema';

-- DROP SEQUENCE public.buys_id_seq;

CREATE SEQUENCE public.buys_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.products_id_seq;

CREATE SEQUENCE public.products_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.sales_id_seq;

CREATE SEQUENCE public.sales_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;-- public.buys definition

-- Drop table

-- DROP TABLE public.buys;

CREATE TABLE public.buys (
	idtype varchar NULL,
	clientname varchar NULL,
	id bigserial NOT NULL,
	"document" varchar NULL,
	"date" timestamp NULL,
	CONSTRAINT buys_pkey PRIMARY KEY (id)
);


-- public.detailbuy definition

-- Drop table

-- DROP TABLE public.detailbuy;

CREATE TABLE public.detailbuy (
	id int8 NOT NULL DEFAULT nextval('sales_id_seq'::regclass),
	product int8 NULL,
	buy int8 NULL,
	quantity int8 NULL,
	CONSTRAINT sales_pkey PRIMARY KEY (id)
);


-- public.products definition

-- Drop table

-- DROP TABLE public.products;

CREATE TABLE public.products (
	id serial4 NOT NULL,
	"name" varchar NULL,
	ininventory int4 NULL,
	enabled bool NULL,
	min int4 NULL,
	max int4 NULL,
	CONSTRAINT name_uk UNIQUE (name),
	CONSTRAINT products_pkey PRIMARY KEY (id)
);

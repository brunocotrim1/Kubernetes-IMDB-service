drop table if exists aka;
drop table if exists crew;
drop table if exists person;
drop table if exists principal;
drop table if exists ratings;
drop table if exists title;
drop table if exists episode;
drop index if exists hundrer_filter_index;
drop index if exists title_by_genre;
drop index if exists title_by_name;
drop index if exists episode_find_byParent;
CREATE TABLE aka (
  title VARCHAR(65535),
   region VARCHAR(255),
   language VARCHAR(255),
   types VARCHAR(255),
   attributes VARCHAR(255),
   is_original_title VARCHAR(255),
   title_id VARCHAR(255) NOT NULL,
   ordering INTEGER NOT NULL,
   CONSTRAINT pk_aka PRIMARY KEY (title_id, ordering)
);

CREATE TABLE crew (
  tconst VARCHAR(255) NOT NULL,
   directors VARCHAR(65535),
   writers VARCHAR(65535),
   CONSTRAINT pk_crew PRIMARY KEY (tconst)
);
CREATE TABLE episode (
  tconst VARCHAR(255) NOT NULL,
   parent_tconst VARCHAR(255),
   season_number VARCHAR(255),
   episode_number VARCHAR(255),
   CONSTRAINT pk_episode PRIMARY KEY (tconst)
);
CREATE TABLE person (
  nconst VARCHAR(255) NOT NULL,
   primary_name VARCHAR(255),
   birth_year VARCHAR(255),
   death_year VARCHAR(255),
   primary_profession VARCHAR(255),
   known_for_titles VARCHAR(255),
   CONSTRAINT pk_person PRIMARY KEY (nconst)
);
CREATE TABLE principal (
  category VARCHAR(255),
   job VARCHAR(65535),
   characters VARCHAR(65535),
   tconst VARCHAR(255) NOT NULL,
   nconst VARCHAR(255) NOT NULL,
   ordering VARCHAR(255) NOT NULL,
   CONSTRAINT pk_principal PRIMARY KEY (tconst, nconst, ordering)
);

CREATE TABLE title (
  tconst VARCHAR(255) NOT NULL,
   title_type VARCHAR(255),
   primary_title VARCHAR(65535),
   original_title VARCHAR(65535),
   is_adult VARCHAR(255),
   start_year VARCHAR(255),
   end_year VARCHAR(255),
   runtime_minutes VARCHAR(255),
   genres VARCHAR(255),
   CONSTRAINT pk_title PRIMARY KEY (tconst)
);

CREATE TABLE ratings (
  tconst VARCHAR(255) NOT NULL,
   title_tconst VARCHAR(255),
   average_rating FLOAT NOT NULL,
   num_votes INTEGER NOT NULL,
   CONSTRAINT pk_ratings PRIMARY KEY (tconst)
);

ALTER TABLE ratings ADD CONSTRAINT FK_RATINGS_ON_TITLE_TCONST FOREIGN KEY (title_tconst) REFERENCES title (tconst);


ALTER TABLE public.aka SET UNLOGGED;
ALTER TABLE public.principal SET UNLOGGED;
ALTER TABLE public.ratings SET UNLOGGED;
ALTER TABLE public.title SET UNLOGGED;
ALTER TABLE public.person SET UNLOGGED;
ALTER TABLE public.episode SET UNLOGGED;
ALTER TABLE public.crew SET UNLOGGED;

COPY public.title(tconst,title_type ,primary_title,original_title,is_adult,start_year,end_year,runtime_minutes,genres) FROM '/dataset/title.basics.tsv' delimiter E'\t' HEADER;
COPY public.ratings(tconst,average_rating,num_votes) FROM '/dataset/title.ratings.tsv' delimiter E'\t' HEADER;
COPY public.principal(tconst,"ordering",nconst,category,job,"characters") FROM '/dataset/title.principals.tsv' delimiter E'\t' HEADER;
COPY public.person(nconst,primary_name,birth_year,death_year,primary_profession,known_for_titles) FROM '/dataset/name.basics.tsv' delimiter E'\t' HEADER;
COPY public.episode(tconst,parent_tconst,season_number,episode_number) FROM '/dataset/title.episode.tsv' delimiter E'\t' HEADER;
COPY public.crew(tconst,directors,writers) FROM '/dataset/title.crew.tsv' delimiter E'\t' HEADER;
COPY public.aka(title_id,"ordering",title,region,"language","types","attributes","is_original_title") FROM '/dataset/title.akas.tsv' delimiter E'\t' HEADER;

CREATE INDEX hundrer_filter_index ON public.title (title_type, start_year);
CREATE INDEX title_by_genre ON public.title (title_type, genres);
CREATE INDEX title_by_name ON public.title (primary_title);
CREATE INDEX episode_find_byParent ON public.episode (parent_tconst);
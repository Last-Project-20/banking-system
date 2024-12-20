-- init.sql

-- 'auth' 데이터베이스 생성
CREATE DATABASE auth
  WITH OWNER = banking
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1;

-- 'account' 데이터베이스 생성
CREATE DATABASE account
  WITH OWNER = banking
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1;

-- 'transaction_sequence' 시퀀스 생성 (account 데이터베이스에서 사용할 시퀀스)
CREATE SEQUENCE transaction_sequence
    INCREMENT BY 1
    MINVALUE 1
    START 1
    CACHE 50;

-- 'batch' 데이터베이스 생성
CREATE DATABASE batch_db
  WITH OWNER = banking
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1;

CREATE SEQUENCE batch_job_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE batch_step_seq START WITH 1 INCREMENT BY 1;

-- 'personal' 데이터베이스 생성
CREATE DATABASE personal
  WITH OWNER = banking
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1;

-- 'product' 데이터베이스 생성
CREATE DATABASE product
  WITH OWNER = banking
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1;

-- 'support' 데이터베이스 생성
CREATE DATABASE support
  WITH OWNER = banking
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1;

-- 'notification' 데이터베이스 생성
CREATE DATABASE notification
  WITH OWNER = banking
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1;

-- 'performance' 데이터베이스 생성
CREATE DATABASE performance
  WITH OWNER = banking
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default
  CONNECTION LIMIT = -1;
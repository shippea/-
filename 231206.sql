/* 테이블 생성 */ 
CREATE TABLE table1(
	name varchar2(20) NOT NULL,
	age number(3) DEFAULT 0
) TABLESPACE myts;

/* 테이블 제거 */

DROP TABLE TABLE1;

/* 컬럼 데이터타입 변경 */ 
ALTER TABLE table1
MODIFY (name varchar(30));

/* 컬럼 추가 */

ALTER TABLE TABLE1
ADD (addr varchar(200));

/*컬럼 제거 */

ALTER TABLE TABLE1
DROP COLUMN addr;

/* 컬럼명 제거 */

ALTER TABLE TABLE1
RENAME COLUMN name TO username;

/* 테이블 생성 */

CREATE TABLE table2(
	ch char(4),
	vc varchar2(300),
	nb number(5,2),
	dt DATE,
	cl clob,
	bl blob
);

/* 데이터 입력 , 전체 데이터를 입력할 경우() 생략가능 */

INSERT INTO table2 (ch,vc,nb,dt,cl,bl)
VALUES ('ABCD','안녕하세요',123.45,sysdate,empty_clob(),empty_blob());

/* 데이터 조회 */
SELECT * FROM TABLE2;

/* 특정 컬럼을 조회 */
SELECT ch,VC,DT FROM TABLE2;

/* 문자데이터 길이 */

SELECT LENGTH(ch), LENGTH(vc), LENGTHB(ch), LENGTHB(vc)
FROM TABLE2;

/* 특정 테이블의 컬럼 정보 확인 */

SELECT column_id, column_name, data_type, data_length
FROM USER_TAB_COLS
WHERE TABLE_name = 'TABLE2';

/* 모든 테이블의 컬럼 정보 확인 */

SELECT * 
FROM user_tab_cols;

/* 제약 조건 조회 */

SELECT CONSTRAINT_name, CONSTRAINT_type, TABLE_name,search_condition
FROM USER_CONSTRAINTS
WHERE table_name = 'TABLE1';

/* 컬럼에 제약조건을 가진 테이블 생성 */

CREATE TABLE table3(
	col1 varchar2(10) PRIMARY key,
	col2 varchar2(10) NOT NULL,
	col3 varchar2(10) UNIQUE,
	col4 varchar2(10)
constraints ck_col4 CHECK (col4 IN ('MALE','FEMALE'))
);

/* 테이블 생성 후 카피 */

CREATE TABLE table3copy AS
SELECT col1,col4
FROM table3;

/* 테이블4 삭제 */

DROP TABLE TABLE4;

/* 테이블4 외부키 가진 */

CREATE TABLE table4(
	col5 varchar2(10),
CONSTRAINT fk_col1 FOREIGN KEY (col5)
REFERENCES table3(col1)
);

/* 테이블3에 값 추가 */

INSERT INTO table3
VALUES ('1','1','1','MALE');

/* 테이블3 출력 */

SELECT * 
FROM table3;

/* 테이블4에 데이터 추가 */


INSERT INTO TABLE4 (col5)
VALUES ('1');

/* 테이블4 데이터 출력 */

SELECT * 
FROM table4;

/* 데이터 셀렉트한 뷰 생성 */

CREATE OR REPLACE VIEW my_vw AS
SELECT t3.col1,t3.col2,t3.col3,t3.col4,t4.col5
FROM table3 t3, TABLE4 t4
WHERE t3.COL1 =t4.col5;

 /* 뷰 출력 */

SELECT * 
FROM my_vw;

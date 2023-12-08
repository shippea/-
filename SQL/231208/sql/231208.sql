CREATE TABLE board (
	bid NUMBER PRIMARY KEY,
	bwriter varchar2(100) NOT NULL,
	btitle varchar2(2000) NOT NULL,
	bcontent varchar2(4000)
);

CREATE SEQUENCE seq_board;

SELECT *
FROM board;


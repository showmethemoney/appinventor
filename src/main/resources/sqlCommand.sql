CREATE TABLE CUSTOMER
(
	ID VARCHAR2(32) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	NICK_NAME VARCHAR2(50) NOT NULL,
	PASSWORD VARCHAR2(50) NOT NULL,
	ADDRESS VARCHAR2(300) NOT NULL,
	DELIVERY_MAN_ID VARCHAR2(50),
);

CREATE TABLE DELIVERY_MAN
(
	ID VARCHAR2(32) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	NICK_NAME VARCHAR2(50) NOT NULL,
	PASSWORD VARCHAR2(50) NOT NULL,
	LATITUDE VARCHAR2(20),
	LONGITUDE VARCHAR2(20),
); 

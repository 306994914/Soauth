--------------------------------------------------------
--  文件已创建 - 星期三-一月-10-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table U_USER
--------------------------------------------------------

  CREATE TABLE "U_USER" 
   (	"ID" NUMBER(20,0), 
	"USERNAME" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(50 BYTE), 
	"CREATE_TIME" DATE, 
	"LAST_LOGIN_TIME" DATE, 
	"STATUS" NUMBER(2,0), 
	"SALT" VARCHAR2(50 BYTE), 
	"DEPARTMENT" VARCHAR2(50 BYTE), 
	"UUID" VARCHAR2(10 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
 

   COMMENT ON COLUMN "U_USER"."USERNAME" IS '用户登录名';
 
   COMMENT ON COLUMN "U_USER"."PASSWORD" IS '加密密码';
 
   COMMENT ON COLUMN "U_USER"."CREATE_TIME" IS '创建时间';
 
   COMMENT ON COLUMN "U_USER"."LAST_LOGIN_TIME" IS '最后登录时间';
 
   COMMENT ON COLUMN "U_USER"."STATUS" IS '账号状态';
 
   COMMENT ON COLUMN "U_USER"."SALT" IS '盐值';
 
   COMMENT ON COLUMN "U_USER"."DEPARTMENT" IS '部门';
 
   COMMENT ON COLUMN "U_USER"."UUID" IS '用户图像id';
REM INSERTING into U_USER
SET DEFINE OFF;
Insert into U_USER (ID,USERNAME,PASSWORD,CREATE_TIME,LAST_LOGIN_TIME,STATUS,SALT,DEPARTMENT,UUID) values (14,'jz9524','501d9585aaf895f80cc398a8603569f1',to_date('01-9月 -17','DD-MON-RR'),to_date('01-9月 -17','DD-MON-RR'),1,'05c3aab45cebe078088d2615978536d6','董事会办公室','294e9068');
Insert into U_USER (ID,USERNAME,PASSWORD,CREATE_TIME,LAST_LOGIN_TIME,STATUS,SALT,DEPARTMENT,UUID) values (1,'jz9523','7f117f8de64d239f1ad623fdbeaa4c29',to_date('16-6月 -16','DD-MON-RR'),to_date('16-6月 -16','DD-MON-RR'),1,'83c981419b942a376884511301fc0a1d','测试工程部','294e9068');
Insert into U_USER (ID,USERNAME,PASSWORD,CREATE_TIME,LAST_LOGIN_TIME,STATUS,SALT,DEPARTMENT,UUID) values (2,'测试账号','s',to_date('08-7月 -17','DD-MON-RR'),to_date('08-7月 -17','DD-MON-RR'),1,'ss','测试工程部','294e9068');
--------------------------------------------------------
--  DDL for Index U_USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "U_USER_PK" ON "U_USER" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table U_USER
--------------------------------------------------------

  ALTER TABLE "U_USER" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "U_USER" MODIFY ("USERNAME" NOT NULL ENABLE);
 
  ALTER TABLE "U_USER" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "U_USER" MODIFY ("STATUS" NOT NULL ENABLE);
 
  ALTER TABLE "U_USER" MODIFY ("SALT" NOT NULL ENABLE);
 
  ALTER TABLE "U_USER" ADD CONSTRAINT "U_USER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;

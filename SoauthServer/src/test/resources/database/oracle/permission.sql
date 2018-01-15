--------------------------------------------------------
--  文件已创建 - 星期三-一月-10-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table PERMISSION
--------------------------------------------------------

  CREATE TABLE "PERMISSION" 
   (	"ID" NUMBER(20,0), 
	"URL" VARCHAR2(200 BYTE), 
	"PARENT" VARCHAR2(200 BYTE), 
	"URLNAME" VARCHAR2(200 BYTE), 
	"CSSNAME" VARCHAR2(200 BYTE), 
	"URLTYPE" VARCHAR2(200 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into PERMISSION
SET DEFINE OFF;
Insert into PERMISSION (ID,URL,PARENT,URLNAME,CSSNAME,URLTYPE) values (2,'/clients','1','client申请',' ','html');
Insert into PERMISSION (ID,URL,PARENT,URLNAME,CSSNAME,URLTYPE) values (1,'*','0','client管理','icon-gears','data');
--------------------------------------------------------
--  DDL for Index PERMISSION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PERMISSION_PK" ON "PERMISSION" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table PERMISSION
--------------------------------------------------------

  ALTER TABLE "PERMISSION" ADD CONSTRAINT "PERMISSION_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "PERMISSION" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "PERMISSION" MODIFY ("URL" NOT NULL ENABLE);
 
  ALTER TABLE "PERMISSION" MODIFY ("PARENT" NOT NULL ENABLE);
 
  ALTER TABLE "PERMISSION" MODIFY ("URLNAME" NOT NULL ENABLE);
 
  ALTER TABLE "PERMISSION" MODIFY ("URLTYPE" NOT NULL ENABLE);

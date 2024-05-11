-- -------------------------------------------------------------
-- -------------------------------------------------------------
-- TablePlus 1.1.8
--
-- https://tableplus.com/
--
-- Database: postgres
-- Generation Time: 2024-05-11 19:56:29.177874
-- -------------------------------------------------------------

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."m_account" (
    "id" varchar NOT NULL,
    "is_enable" bool,
    "password" varchar NOT NULL,
    "username" varchar NOT NULL,
    PRIMARY KEY ("id")
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."m_account_roles" (
    "account_id" varchar NOT NULL,
    "roles_id" varchar NOT NULL
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."m_customer" (
    "id" varchar NOT NULL,
    "address" varchar,
    "customer_name" varchar NOT NULL,
    "phone_number" varchar,
    "reward" int4,
    "account_id" varchar,
    PRIMARY KEY ("id")
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."m_merchant" (
    "id" varchar NOT NULL,
    "address" varchar,
    "merchant_name" varchar NOT NULL,
    "phone_number" varchar,
    "account_id" varchar,
    PRIMARY KEY ("id")
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."m_product" (
    "id" varchar NOT NULL,
    "price" int4 NOT NULL,
    "product_name" varchar NOT NULL,
    "stock" int4 NOT NULL,
    "merchant_id" varchar NOT NULL,
    "is_delete" bool,
    PRIMARY KEY ("id")
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."m_reward" (
    "id" varchar NOT NULL,
    "is_delete" bool,
    "point" int4 NOT NULL,
    "reward_name" varchar NOT NULL,
    PRIMARY KEY ("id")
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."m_role" (
    "id" varchar NOT NULL,
    "role" varchar,
    PRIMARY KEY ("id")
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."t_transaction" (
    "id" varchar NOT NULL,
    "date" timestamp,
    "customer_id" varchar,
    "merchant_id" varchar,
    PRIMARY KEY ("id")
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."t_transaction_detail" (
    "id" varchar NOT NULL,
    "price" int4,
    "qty" int4,
    "product_id" varchar NOT NULL,
    "trx_id" varchar NOT NULL,
    PRIMARY KEY ("id")
);

-- This script only contains the table creation statements and does not fully represent the table in database. It's still missing: indices, triggers. Do not use it as backup.

-- Table Definition
CREATE TABLE "public"."t_transaction_reward" (
    "id" varchar NOT NULL,
    "date" timestamp,
    "customer_id" varchar NOT NULL,
    "reward_id" varchar NOT NULL,
    PRIMARY KEY ("id")
);

INSERT INTO "public"."m_account" ("id","is_enable","password","username") VALUES 
('caec52d4-876c-42c2-88a6-84c1dba60673','TRUE','$2a$10$oWDxgDgSygvthK6Lw8ekQ.AxFHu4dOKyHlWuMCSEQboUU1cX1Iz0m','fulan'),
('94f0310c-48ba-46ef-afaf-fc0aa285da1a','TRUE','$2a$10$PZeT/k9Wkp1P//slTE1Rs.e8MpeTuCB8l5FX2aPX5xVmmsAlxOiAq','fulanah'),
('d374c1be-a97b-46d3-95a0-a42e4e1a20e4','FALSE','$2a$10$H9qcPUdqBBeqrk0O3HeWoeBzWPewjr8PyfvPrMKm6/TCYRLriekdq','ahmad'),
('49ed9ba3-1173-4455-a16a-b511e677b2e1','FALSE','$2a$10$Zpei4Edj7LI3HY3jmuquSu3aDIt5ngyi2CklOtr0IMr8PWK5Gc5bi','maman'),
('d10500b6-5479-4ec5-92fa-b8ba68fedf7e','TRUE','$2a$10$U/2SZn9n9yE7dpgo0vGv0.OrAEtIfUmGCvhOFSR44Il0IMtokCLT2','admin'),
('1f3ed0ec-cffe-4dd6-8bfa-c5d264c1177e','TRUE','$2a$10$F4wrcmjNcc4E1ud1cheUNuYWAQ8Dr0.FNhyVc.wSwYIuGsdELyLni','opal');

INSERT INTO "public"."m_account_roles" ("account_id","roles_id") VALUES 
('caec52d4-876c-42c2-88a6-84c1dba60673','0174f59c-73be-42d6-8c22-4439153842d2'),
('94f0310c-48ba-46ef-afaf-fc0aa285da1a','1dfdff1d-814d-4ec5-909f-4886b52db5d3'),
('d374c1be-a97b-46d3-95a0-a42e4e1a20e4','0174f59c-73be-42d6-8c22-4439153842d2'),
('49ed9ba3-1173-4455-a16a-b511e677b2e1','1dfdff1d-814d-4ec5-909f-4886b52db5d3'),
('d10500b6-5479-4ec5-92fa-b8ba68fedf7e','5b995487-b0ba-437c-9b7b-4d60cec7a0da'),
('1f3ed0ec-cffe-4dd6-8bfa-c5d264c1177e','1dfdff1d-814d-4ec5-909f-4886b52db5d3');

INSERT INTO "public"."m_customer" ("id","address","customer_name","phone_number","reward","account_id") VALUES 
('66d7cc15-3619-42df-a0a1-93498706ab96',NULL,'Ahmad Batu Alam',NULL,NULL,'d374c1be-a97b-46d3-95a0-a42e4e1a20e4'),
('97fecbe7-f3c6-4eaf-a455-810fdf34bc07','Jl. Ahmad yani','Fulan','08123456789',160,'caec52d4-876c-42c2-88a6-84c1dba60673');

INSERT INTO "public"."m_merchant" ("id","address","merchant_name","phone_number","account_id") VALUES 
('f96ccf62-3453-4e0c-9875-897aecdff640',NULL,'Maman Abdurrahman',NULL,'49ed9ba3-1173-4455-a16a-b511e677b2e1'),
('64f08132-ca4f-4c5b-af7d-d3cd458bddf1',NULL,'Opal Store',NULL,'1f3ed0ec-cffe-4dd6-8bfa-c5d264c1177e'),
('97635609-89f9-4aab-8ded-873e5dfe54c6','Jl. Merdeka no 14','Fulanah binti umar','0812943723','94f0310c-48ba-46ef-afaf-fc0aa285da1a');

INSERT INTO "public"."m_product" ("id","price","product_name","stock","merchant_id","is_delete") VALUES 
('82695c92-963a-477d-8dbb-d864a2a134ac',250000,'Celana Jeans Slim Fit',15,'97635609-89f9-4aab-8ded-873e5dfe54c6','FALSE'),
('ca894f93-69ab-4abc-8615-dbd412616be1',200000,'Sepatu Sneakers Casual',30,'97635609-89f9-4aab-8ded-873e5dfe54c6','TRUE'),
('a39b1836-b30d-4e35-a2d5-b90d26bf8301',150000,'Kemeja Batik Modern',0,'97635609-89f9-4aab-8ded-873e5dfe54c6','FALSE'),
('505d5b5f-b5eb-401c-86d8-075f23d5a909',300000,'Jaket Parka Unisex',4,'97635609-89f9-4aab-8ded-873e5dfe54c6','FALSE'),
('c18b6b5e-2882-4e72-b1df-f8f04c9b2c6a',150000,'Jas Hitam Pria',13,'97635609-89f9-4aab-8ded-873e5dfe54c6','FALSE');

INSERT INTO "public"."m_reward" ("id","is_delete","point","reward_name") VALUES 
('71a7bcd6-71a7-4788-ac70-d1a7bd892f83','FALSE',200,'Voucher Belanja Rp 50.000'),
('eb169f9b-02b5-418e-b5de-c7dbeafa5aa4','FALSE',300,'Tas Eksklusif Maju Mundur'),
('f05a3d58-8e32-4420-97e2-97f3ccd9b222','FALSE',250,'Diskon 20% untuk Pembelian Selanjutnya'),
('f310166f-6ac9-401e-b884-44f6942aeb92','FALSE',500,'Diskon 50% untuk Pembelian Selanjutnya'),
('296bbd05-589f-4025-90fc-09463438d2d4','TRUE',150,'Pulsa Gratis Rp 25.000'),
('81255313-cd49-41c7-8e5e-5d8bb4031f29','FALSE',50,'Diskon 5% untuk Pembelian Selanjutnya');

INSERT INTO "public"."m_role" ("id","role") VALUES 
('0174f59c-73be-42d6-8c22-4439153842d2','ROLE_CUSTOMER'),
('1dfdff1d-814d-4ec5-909f-4886b52db5d3','ROLE_MERCHANT'),
('5b995487-b0ba-437c-9b7b-4d60cec7a0da','ROLE_ADMIN');

INSERT INTO "public"."t_transaction" ("id","date","customer_id","merchant_id") VALUES 
('69afdafe-6c37-4ff5-8de5-552e1e2167c0','2024-05-10 14:21:02.73','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('882a4c58-cd33-4f96-8707-ce3ce7ac4363','2024-05-10 14:06:07.635','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('ff3b31ca-37e1-494d-9973-075647d88500','2024-05-10 13:52:57.245','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('12735b91-876b-42f2-b64c-655bf51910d9','2024-05-10 14:07:47.88','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('58885899-5cb2-4ed4-b861-37afec581dd1','2024-05-10 13:51:18.993','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('f02fce72-2079-49a6-92d8-9e38ea2cada9','2024-05-10 18:15:49.035','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('f4f9c2c7-4f03-4d37-a4df-251a212e81c4','2024-05-10 14:31:53.504','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('34475e7f-24ff-456e-8a78-ed41feb5e2fe','2024-05-10 14:31:44.078','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('16347b2d-c4c2-4176-b799-c0662d285e87','2024-05-10 14:20:40.78','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('be629af9-eec1-4e1f-b9fb-684f3f55bd75','2024-05-10 14:11:49.61','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('ebd330fe-8476-4a8d-9554-7f82941be14a','2024-05-10 14:07:32.153','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('bce54421-11ab-4809-87a8-b42aa021fbd3','2024-05-10 13:27:33.111','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('de64a7a2-2f43-4619-86c7-b4bf235e53ce','2024-05-10 13:20:40.001','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('5be9e5a2-8298-499d-8369-8b4178e79fb8','2024-05-10 13:13:38.82','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('d9976cb8-963e-47dc-8e31-ce62749df75b','2024-05-10 14:11:38.251','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('44d0871c-471e-4b82-a179-b7091fc8c9f9','2024-05-10 13:28:53.549','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('e8bd1210-da6a-4631-ac71-a16710452d9a','2024-05-10 12:58:37.806','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('02273030-ab3d-41ce-8030-48fecc52263f','2024-05-10 13:11:12.418','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('3a6a11fa-baaa-457e-bb07-a825c8171d19','2024-05-10 13:08:56.8','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('67423731-3c3a-4f5f-889f-0ddaa665e0a8','2024-05-10 14:07:21.532','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6'),
('c264600a-6bd5-4cf4-9ff5-2a12ff9307f9','2024-05-10 13:14:36.786','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','97635609-89f9-4aab-8ded-873e5dfe54c6');

INSERT INTO "public"."t_transaction_detail" ("id","price","qty","product_id","trx_id") VALUES 
('eadd88cb-7fac-4e34-ae20-d0cdd1cf8645',150000,2,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','e8bd1210-da6a-4631-ac71-a16710452d9a'),
('ce3f9f80-3a1b-4198-852d-eae32b8ae68e',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','3a6a11fa-baaa-457e-bb07-a825c8171d19'),
('539a609b-7456-43dc-ad6b-cb676e2591e9',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','02273030-ab3d-41ce-8030-48fecc52263f'),
('41a28730-e7bc-4edc-9289-7454ed031ff5',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','5be9e5a2-8298-499d-8369-8b4178e79fb8'),
('a70172c0-4a4e-49bf-8043-76745ea6ca1d',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','c264600a-6bd5-4cf4-9ff5-2a12ff9307f9'),
('85fad52c-c547-4207-94c7-3649e71f189a',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','de64a7a2-2f43-4619-86c7-b4bf235e53ce'),
('935546d2-ad4c-4921-8359-abbb009b8d5c',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','bce54421-11ab-4809-87a8-b42aa021fbd3'),
('864a6e3b-03e4-4211-aa00-82a7982c11d6',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','44d0871c-471e-4b82-a179-b7091fc8c9f9'),
('42a50f08-98b1-439d-ad3b-120cb06d5604',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','58885899-5cb2-4ed4-b861-37afec581dd1'),
('f9ece2c1-f7c9-4ab9-bf93-9436f9e2c69f',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','ff3b31ca-37e1-494d-9973-075647d88500'),
('28e6b244-49b3-42f9-9d39-bd5ec7f44c84',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','882a4c58-cd33-4f96-8707-ce3ce7ac4363'),
('baf6e872-2b33-4375-8be9-9a54dd265b2a',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','67423731-3c3a-4f5f-889f-0ddaa665e0a8'),
('35d0bee5-39dd-4423-bb5e-cd22494512dc',150000,1,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','ebd330fe-8476-4a8d-9554-7f82941be14a'),
('9a08bd20-aaff-4d76-a1ac-9ae51bab992d',150000,2,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','12735b91-876b-42f2-b64c-655bf51910d9'),
('7696d8fb-95f1-4ae5-889c-50cf6f88f5da',150000,2,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','d9976cb8-963e-47dc-8e31-ce62749df75b'),
('0f9e9105-e50d-46d4-be2b-83a9edbff0e5',150000,2,'a39b1836-b30d-4e35-a2d5-b90d26bf8301','be629af9-eec1-4e1f-b9fb-684f3f55bd75'),
('5d814429-10e5-40dd-98b7-38ef626b5bb4',300000,1,'505d5b5f-b5eb-401c-86d8-075f23d5a909','16347b2d-c4c2-4176-b799-c0662d285e87'),
('56360db4-b38d-43c9-abca-8c406ed84553',150000,1,'c18b6b5e-2882-4e72-b1df-f8f04c9b2c6a','16347b2d-c4c2-4176-b799-c0662d285e87'),
('a0f290b0-14c3-4c60-9a1e-e1fb6b8fae78',300000,1,'505d5b5f-b5eb-401c-86d8-075f23d5a909','69afdafe-6c37-4ff5-8de5-552e1e2167c0'),
('f9652d2b-104c-4a11-86cd-439bc85a3dc6',150000,2,'c18b6b5e-2882-4e72-b1df-f8f04c9b2c6a','69afdafe-6c37-4ff5-8de5-552e1e2167c0'),
('ed529194-7390-439b-b46b-6fa7c14d6187',300000,1,'505d5b5f-b5eb-401c-86d8-075f23d5a909','34475e7f-24ff-456e-8a78-ed41feb5e2fe'),
('655538cc-0384-4357-bcb0-1778219efe6c',150000,2,'c18b6b5e-2882-4e72-b1df-f8f04c9b2c6a','34475e7f-24ff-456e-8a78-ed41feb5e2fe'),
('9bd154fd-1719-4b31-b040-5256e8a5180a',300000,1,'505d5b5f-b5eb-401c-86d8-075f23d5a909','f4f9c2c7-4f03-4d37-a4df-251a212e81c4'),
('95a33626-7fb0-4e14-a142-aa3d680c54ce',150000,1,'c18b6b5e-2882-4e72-b1df-f8f04c9b2c6a','f4f9c2c7-4f03-4d37-a4df-251a212e81c4'),
('8fbe5d9b-f895-40d6-9d28-2698c1faa9fc',300000,2,'505d5b5f-b5eb-401c-86d8-075f23d5a909','f02fce72-2079-49a6-92d8-9e38ea2cada9'),
('fe81aa68-8f3e-4d36-be8a-4faa8cb315b5',150000,1,'c18b6b5e-2882-4e72-b1df-f8f04c9b2c6a','f02fce72-2079-49a6-92d8-9e38ea2cada9');

INSERT INTO "public"."t_transaction_reward" ("id","date","customer_id","reward_id") VALUES ('e6d540d5-0f5b-44a9-8d6e-c035af549b97','2024-05-10 21:25:36.95','97fecbe7-f3c6-4eaf-a455-810fdf34bc07','71a7bcd6-71a7-4788-ac70-d1a7bd892f83');


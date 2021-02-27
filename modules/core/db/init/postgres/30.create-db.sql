insert into METEOROLOGICALDATA_METEO_SETTING
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, TIME_TO_UPDATE, SELECT_COM_PORT)
values ('b6feac73-a65e-f5bd-3f57-f461106eedcd', 1, '2020-07-16 13:52:44', 'admin', '2020-07-16 13:52:44', null, null, null, 60, '/dev/ttyUSB0');

insert into SYS_SCHEDULED_TASK
(ID, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, DEFINED_BY, SYS_TENANT_ID, BEAN_NAME, METHOD_NAME,
 CLASS_NAME, SCRIPT_NAME, USER_NAME, IS_SINGLETON, IS_ACTIVE, PERIOD_, TIMEOUT, START_DATE, CRON, SCHEDULING_TYPE, TIME_FRAME,
 START_DELAY, PERMITTED_SERVERS, LOG_START, LOG_FINISH, LAST_START_TIME, LAST_START_SERVER, METHOD_PARAMS, DESCRIPTION)
values ('0ab01111-71f2-6dad-405a-3574f1a7cc3c', '2020-07-16 14:46:05', 'admin', '2020-09-19 11:54:09', 'admin', null, null,
'B', null, 'meteorologicaldata_ConnectService', 'writeUsb', null, null, 'admin', null, false, 5, null, null, null, 'D', null,
 null, null, null, null, null, null, '<?xml version="1.0" encoding="UTF-8"?><params><param type="java.lang.String" name="req">0R0!</param></params>',
 'Функция опроса станции');


insert into METEOROLOGICALDATA_METEO_DATA_ONLINE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, TIME_RESULT, WIND_VECTOR_STRING, DX, DN, DM, SN, SM, TP, RD, RI, HC, HD, HI, RP, HP, TH, VR, SX, TA, UA, PA, RC, VS, VH)
values ('8c3889bf-fe93-c9a7-ff28-53e80c70ea3a', 2, '2020-07-17 14:38:29', 'admin', '2020-09-18 13:48:50', 'admin', null, null, '2020-07-01 00:00:00', '***', 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

insert into SYS_SCHEDULED_TASK
(ID, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, DEFINED_BY, SYS_TENANT_ID, BEAN_NAME, METHOD_NAME,
CLASS_NAME, SCRIPT_NAME, USER_NAME, IS_SINGLETON, IS_ACTIVE, PERIOD_, TIMEOUT, START_DATE, CRON, SCHEDULING_TYPE, TIME_FRAME,
START_DELAY, PERMITTED_SERVERS, LOG_START, LOG_FINISH, LAST_START_TIME, LAST_START_SERVER, METHOD_PARAMS, DESCRIPTION)
values ('c285e907-7bc2-7a8e-0590-0f6bf812546b', '2020-09-18 13:30:22', 'admin', '2020-09-19 11:54:39', 'admin', null, null,
 'B', null, 'meteorologicaldata_EntityService', 'createMeteoData', null, null, 'admin', null, false, 59, null, null, null, 'D',
  null, null, null, null, null, null, null, '<?xml version="1.0" encoding="UTF-8"?><params/>', 'Функция записи данных в БД');


insert into METEOROLOGICALDATA_METEO_STATION_INFO
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, OPTIONS, CODE, CALIBRITY, INFO, SERIAL_NUMBER)
values ('7771e3da-1d94-71f8-a01f-f880d6833969', 1, '2020-09-18 15:18:22', 'admin', '2020-09-18 15:18:22', null, null, null, 'Нет данных', 'Нет данных', 'Нет данных', 'Нет данных', 'Нет данных');

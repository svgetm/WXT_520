-- begin METEOROLOGICALDATA_METEO_DATA_ONLINE
create table METEOROLOGICALDATA_METEO_DATA_ONLINE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TIME_RESULT timestamp,
    WIND_VECTOR_STRING varchar(255),
    DX double precision,
    DN double precision,
    DM double precision,
    SN double precision,
    SM double precision,
    TP double precision,
    RD double precision,
    RI double precision,
    HC double precision,
    HD double precision,
    HI double precision,
    RP double precision,
    HP double precision,
    TH double precision,
    VR double precision,
    SX double precision,
    TA double precision,
    UA double precision,
    PA double precision,
    RC double precision,
    VS double precision,
    VH double precision,
    --
    primary key (ID)
)^
-- end METEOROLOGICALDATA_METEO_DATA_ONLINE
-- begin METEOROLOGICALDATA_METEO_SETTING
create table METEOROLOGICALDATA_METEO_SETTING (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TIME_TO_UPDATE integer,
    SELECT_COM_PORT varchar(255),
    --
    primary key (ID)
)^
-- end METEOROLOGICALDATA_METEO_SETTING
-- begin METEOROLOGICALDATA_METEO_DATA
create table METEOROLOGICALDATA_METEO_DATA (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TIME_RESULT timestamp,
    DX double precision,
    DN double precision,
    DM double precision,
    SN double precision,
    SM double precision,
    TP double precision,
    RD double precision,
    RI double precision,
    HC double precision,
    HD double precision,
    HI double precision,
    RP double precision,
    HP double precision,
    TH double precision,
    VR double precision,
    SX double precision,
    TA double precision,
    UA double precision,
    PA double precision,
    RC double precision,
    VS double precision,
    VH double precision,
    --
    primary key (ID)
)^
-- end METEOROLOGICALDATA_METEO_DATA
-- begin METEOROLOGICALDATA_METEO_STATION_INFO
create table METEOROLOGICALDATA_METEO_STATION_INFO (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    OPTIONS varchar(255),
    CODE varchar(255),
    CALIBRITY varchar(255),
    INFO varchar(255),
    SERIAL_NUMBER varchar(255),
    --
    primary key (ID)
)^
-- end METEOROLOGICALDATA_METEO_STATION_INFO

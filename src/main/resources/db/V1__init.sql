/**
  * Création de la séquence pour la clé primaire de la table METRIC
 **/
create sequence SEQ_METRIC start 1000 increment 50;

/**
  * Création de la table METRIC
 **/
create table METRIC (
	MET_ID int8 not null,
	MET_TEMPERATURE int8 not null,
	MET_HUMIDITY int8 not null,
	MET_WIND_SPEED int8 not null,
	MET_PRECIPITATION int8 not null,
	MET_DATE_CREATION date,
	SEN_ID int8,
	constraint PK_METRIC primary key (MET_ID)
);

/**
  * Création de la séquence pour la clé primaire de la table SENSOR
 **/
create sequence SEQ_SENSOR start 1000 increment 50;
/**
  * Création de la table SENSOR
 **/
create table SENSOR (
	SEN_ID int8 not null,
	SEN_NAME varchar(250) not null,
	SEN_LOCATION varchar(250) not null,
	SEN_DATE_CREATION date,
	constraint PK_SENSOR primary key (SEN_ID)
);

/**
  * Création de l'index de clef étrangère pour METRIC.SEN_ID
 **/
create index IDX_MET_SEN_ID_FK on METRIC (
	SEN_ID ASC
);

/**
  * Génération de la contrainte de clef étrangère pour METRIC.SEN_ID
 **/
alter table METRIC
	add constraint FK_METRIC_SEN_ID foreign key (SEN_ID)
		references SENSOR (SEN_ID);

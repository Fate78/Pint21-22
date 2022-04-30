/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2017                    */
/* Created on:     08/04/2022 23:35:15                          */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('RESERVA') and o.name = 'FK_RESERVA_RELATIONS_SALA')
alter table RESERVA
   drop constraint FK_RESERVA_RELATIONS_SALA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('RESERVA') and o.name = 'FK_RESERVA_RELATIONS_UTILIZAD')
alter table RESERVA
   drop constraint FK_RESERVA_RELATIONS_UTILIZAD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SALA') and o.name = 'FK_SALA_RELATIONS_CENTRO_G')
alter table SALA
   drop constraint FK_SALA_RELATIONS_CENTRO_G
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TICKET') and o.name = 'FK_TICKET_RELATIONS_UTILIZAD')
alter table TICKET
   drop constraint FK_TICKET_RELATIONS_UTILIZAD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('UTILIZADOR') and o.name = 'FK_UTILIZAD_RELATIONS_TIPO_UTI')
alter table UTILIZADOR
   drop constraint FK_UTILIZAD_RELATIONS_TIPO_UTI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('UTILIZADOR_CENTRO') and o.name = 'FK_UTILIZAD_E_GERIDA_UTILIZAD')
alter table UTILIZADOR_CENTRO
   drop constraint FK_UTILIZAD_E_GERIDA_UTILIZAD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('UTILIZADOR_CENTRO') and o.name = 'FK_UTILIZAD_GERE_CENTRO_G')
alter table UTILIZADOR_CENTRO
   drop constraint FK_UTILIZAD_GERE_CENTRO_G
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CENTRO_GEOGRAFICO')
            and   type = 'U')
   drop table CENTRO_GEOGRAFICO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('RESERVA')
            and   type = 'U')
   drop table RESERVA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SALA')
            and   type = 'U')
   drop table SALA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TICKET')
            and   type = 'U')
   drop table TICKET
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TIPO_UTILIZADOR')
            and   type = 'U')
   drop table TIPO_UTILIZADOR
go

if exists (select 1
            from  sysobjects
           where  id = object_id('UTILIZADOR')
            and   type = 'U')
   drop table UTILIZADOR
go

if exists (select 1
            from  sysobjects
           where  id = object_id('UTILIZADOR_CENTRO')
            and   type = 'U')
   drop table UTILIZADOR_CENTRO
go

/*==============================================================*/
/* Table: CENTRO_GEOGRAFICO                                     */
/*==============================================================*/
create table CENTRO_GEOGRAFICO (
   ID_CENTRO            int                  IDENTITY(1,1) not null,
   NOME_CENTRO          varchar(1024)        UNIQUE not null,
   IMAGEM               varbinary            null,
   ATIVO                bit                  not null,
   constraint PK_CENTRO_GEOGRAFICO primary key (ID_CENTRO)
)
go

/*==============================================================*/
/* Table: RESERVA                                               */
/*==============================================================*/
create table RESERVA (
   ID_RESERVA           int                  IDENTITY(1,1) not null,
   ID_SALA              int                  null,
   ID_UTILIZADOR        int                  not null,
   HORA_INICIO          time             not null,
   HORA_FIM             time             not null,
   DATA_RESERVA         date                 not null,
   NUM_PESSOAS          int                  not null,
   ATIVO                bit                  not null,
   constraint PK_RESERVA primary key (ID_RESERVA)
)
go

/*==============================================================*/
/* Table: SALA                                                  */
/*==============================================================*/
create table SALA (
   ID_SALA              int                  IDENTITY(1,1) not null,
   ID_CENTRO            int                  null,
   N_SALA               int                  not null,
   LOTACAO_MAX          int                  not null,
   TEMPO_MIN_LIMP       time             not null,
   ATIVO                bit                  not null,
   constraint PK_SALA primary key (ID_SALA)
)
go

/*==============================================================*/
/* Table: TICKET                                                */
/*==============================================================*/
create table TICKET (
   ID_TICKET            int                  IDENTITY(1,1) not null,
   ID_UTILIZADOR        int                  not null,
   ASSUNTO              varchar(1024)        not null,
   CATEGORIA            varchar(1024)        not null,
   DESCRICAO            varchar(1024)        not null,
   RESOLVIDO            bit                  not null,
   constraint PK_TICKET primary key (ID_TICKET)
)
go

/*==============================================================*/
/* Table: TIPO_UTILIZADOR                                       */
/*==============================================================*/
create table TIPO_UTILIZADOR (
   ID_TIPO              int                  UNIQUE not null,
   NOME_TIPO            varchar(1024)        not null,
   DESCRICAO            varchar(1024)        null,
   constraint PK_TIPO_UTILIZADOR primary key (ID_TIPO)
)
go

/*==============================================================*/
/* Table: UTILIZADOR                                            */
/*==============================================================*/
create table UTILIZADOR (
   ID_UTILIZADOR        int                  IDENTITY(1,1) not null,
   ID_TIPO              int                  not null,
   NOME_UTILIZADOR      varchar(1024)        UNIQUE not null,
   NOME_COMPLETO        varchar(1024)        not null,
   PALAVRA_PASSE        varchar(1024)        not null,
   EMAIL                varchar(1024)        UNIQUE not null,
   DATA_NASCIMENTO      date                 null,
   VERIFICADO           bit                  not null,
   ATIVO                bit                  not null,
   constraint PK_UTILIZADOR primary key (ID_UTILIZADOR)
)
go

/*==============================================================*/
/* Table: UTILIZADOR_CENTRO                                     */
/*==============================================================*/
create table UTILIZADOR_CENTRO (
   ID_UTILIZADOR        int                  not null,
   ID_CENTRO            int                  not null,
   ATIVO                bit                  not null,
   constraint PK_UTILIZADOR_CENTRO primary key (ID_UTILIZADOR, ID_CENTRO)
)
go

alter table RESERVA
   add constraint FK_RESERVA_RELATIONS_SALA foreign key (ID_SALA)
      references SALA (ID_SALA)
go

alter table RESERVA
   add constraint FK_RESERVA_RELATIONS_UTILIZAD foreign key (ID_UTILIZADOR)
      references UTILIZADOR (ID_UTILIZADOR)
go

alter table SALA
   add constraint FK_SALA_RELATIONS_CENTRO_G foreign key (ID_CENTRO)
      references CENTRO_GEOGRAFICO (ID_CENTRO)
go

alter table TICKET
   add constraint FK_TICKET_RELATIONS_UTILIZAD foreign key (ID_UTILIZADOR)
      references UTILIZADOR (ID_UTILIZADOR)
go

alter table UTILIZADOR
   add constraint FK_UTILIZAD_RELATIONS_TIPO_UTI foreign key (ID_TIPO)
      references TIPO_UTILIZADOR (ID_TIPO)
go

alter table UTILIZADOR_CENTRO
   add constraint FK_UTILIZAD_E_GERIDA_UTILIZAD foreign key (ID_UTILIZADOR)
      references UTILIZADOR (ID_UTILIZADOR)
go

alter table UTILIZADOR_CENTRO
   add constraint FK_UTILIZAD_GERE_CENTRO_G foreign key (ID_CENTRO)
      references CENTRO_GEOGRAFICO (ID_CENTRO)
go


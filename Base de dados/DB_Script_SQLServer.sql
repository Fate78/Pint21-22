/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2017                    */
/* Created on:     08/04/2022 22:36:50                          */
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
            from  sysindexes
           where  id    = object_id('RESERVA')
            and   name  = 'RELATIONSHIP_3_FK'
            and   indid > 0
            and   indid < 255)
   drop index RESERVA.RELATIONSHIP_3_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('RESERVA')
            and   name  = 'RELATIONSHIP_2_FK'
            and   indid > 0
            and   indid < 255)
   drop index RESERVA.RELATIONSHIP_2_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('RESERVA')
            and   type = 'U')
   drop table RESERVA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SALA')
            and   name  = 'RELATIONSHIP_1_FK'
            and   indid > 0
            and   indid < 255)
   drop index SALA.RELATIONSHIP_1_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SALA')
            and   type = 'U')
   drop table SALA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('TICKET')
            and   name  = 'RELATIONSHIP_4_FK'
            and   indid > 0
            and   indid < 255)
   drop index TICKET.RELATIONSHIP_4_FK
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
            from  sysindexes
           where  id    = object_id('UTILIZADOR')
            and   name  = 'RELATIONSHIP_5_FK'
            and   indid > 0
            and   indid < 255)
   drop index UTILIZADOR.RELATIONSHIP_5_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('UTILIZADOR')
            and   type = 'U')
   drop table UTILIZADOR
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('UTILIZADOR_CENTRO')
            and   name  = 'GERE_FK'
            and   indid > 0
            and   indid < 255)
   drop index UTILIZADOR_CENTRO.GERE_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('UTILIZADOR_CENTRO')
            and   name  = 'E_GERIDA_FK'
            and   indid > 0
            and   indid < 255)
   drop index UTILIZADOR_CENTRO.E_GERIDA_FK
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
   NOME_CENTRO          varchar(1024)        not null,
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
   HORA_INICIO          datetime             not null,
   HORA_FIM             datetime             not null,
   NUM_PESSOAS          int                  not null,
   ATIVO                bit                  not null,
   constraint PK_RESERVA primary key (ID_RESERVA)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/




create nonclustered index RELATIONSHIP_2_FK on RESERVA (ID_SALA ASC)
go

/*==============================================================*/
/* Index: RELATIONSHIP_3_FK                                     */
/*==============================================================*/




create nonclustered index RELATIONSHIP_3_FK on RESERVA (ID_UTILIZADOR ASC)
go

/*==============================================================*/
/* Table: SALA                                                  */
/*==============================================================*/
create table SALA (
   ID_SALA              int                  IDENTITY(1,1) not null,
   ID_CENTRO            int                  null,
   N_SALA               int                  not null,
   LOTACAO_MAX          int                  not null,
   TEMPO_MIN_LIMP       datetime             not null,
   ATIVO                bit                  not null,
   constraint PK_SALA primary key (ID_SALA)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/




create nonclustered index RELATIONSHIP_1_FK on SALA (ID_CENTRO ASC)
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
/* Index: RELATIONSHIP_4_FK                                     */
/*==============================================================*/




create nonclustered index RELATIONSHIP_4_FK on TICKET (ID_UTILIZADOR ASC)
go

/*==============================================================*/
/* Table: TIPO_UTILIZADOR                                       */
/*==============================================================*/
create table TIPO_UTILIZADOR (
   ID_TIPO              int                  IDENTITY(1,1) not null,
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
   NOME_UTILIZADOR      varchar(1024)        not null,
   NOME_COMPLETO        varchar(1024)        not null,
   PALAVRA_PASSE        varchar(1024)        not null,
   EMAIL                varchar(1024)        not null,
   DATA_NASCIMENTO      datetime             null,
   ATIVO                bit                  not null,
   constraint PK_UTILIZADOR primary key (ID_UTILIZADOR)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_5_FK                                     */
/*==============================================================*/




create nonclustered index RELATIONSHIP_5_FK on UTILIZADOR (ID_TIPO ASC)
go

/*==============================================================*/
/* Table: UTILIZADOR_CENTRO                                     */
/*==============================================================*/
create table UTILIZADOR_CENTRO (
   ID_UTILIZADOR        int                  not null,
   ID_CENTRO            int                  not null,
   constraint PK_UTILIZADOR_CENTRO primary key (ID_UTILIZADOR, ID_CENTRO)
)
go

/*==============================================================*/
/* Index: E_GERIDA_FK                                           */
/*==============================================================*/




create nonclustered index E_GERIDA_FK on UTILIZADOR_CENTRO (ID_UTILIZADOR ASC)
go

/*==============================================================*/
/* Index: GERE_FK                                               */
/*==============================================================*/




create nonclustered index GERE_FK on UTILIZADOR_CENTRO (ID_CENTRO ASC)
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


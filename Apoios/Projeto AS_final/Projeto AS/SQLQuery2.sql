if exists (select 1
            from  sysobjects
           where  id = object_id('SERVICO_SALA')
            and   type = 'U')
   drop table SERVICO_SALA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SERVICO')
            and   type = 'U')
   drop table SERVICO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PARTICIPACAO')
            and   type = 'U')
   drop table PARTICIPACAO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('INSCRICAO')
            and   type = 'U')
   drop table INSCRICAO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('UTILIZADOR')
            and   type = 'U')
   drop table UTILIZADOR
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REUNIAO')
            and   type = 'U')
   drop table REUNIAO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SALA')
            and   type = 'U')
   drop table SALA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REQUISITANTE')
            and   type = 'U')
   drop table REQUISITANTE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('GERENCIA')
            and   type = 'U')
   drop table GERENCIA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('DEPARTAMENTO')
            and   type = 'U')
   drop table DEPARTAMENTO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('GESTOR')
            and   type = 'U')
   drop table GESTOR
go

/*==============================================================*/
/* Table: GESTOR                                                */
/*==============================================================*/
create table GESTOR (
	NGESTOR              int IDENTITY(1,1)    PRIMARY KEY,
	NOME                 text                 not null,
	NIF                  numeric(9)           null,
	MORADA               text                 null,
	EMAIL                text                 null,
	TLF                  numeric(9)           not null
);

/*==============================================================*/
/* Table: DEPARTAMENTO                                          */
/*==============================================================*/
create table DEPARTAMENTO (
	NDEP                 int IDENTITY(1,1)    PRIMARY KEY,
	DEP                  text                 not null,
	TLF                  numeric(9)           not null,
	EMAIL                text                 null,
	DESCRICAO            text                 null
);

/*==============================================================*/
/* Table: GERENCIA                                              */
/*==============================================================*/
create table GERENCIA (
	NGERENCIA            int IDENTITY(1,1)    PRIMARY KEY,
	NGESTOR              int                  FOREIGN KEY REFERENCES GESTOR(NGESTOR),
	NDEP                 int                  FOREIGN KEY REFERENCES DEPARTAMENTO(NDEP)
);

/*==============================================================*/
/* Table: REQUISITANTE                                          */
/*==============================================================*/
create table REQUISITANTE (
	NREQ                 int IDENTITY(1,1)    PRIMARY KEY,
	NOME                 text                 not null,
	NIF                  numeric(9)           null,
	MORADA               text                 null,
	EMAIL                text                 null,
	TLF                  numeric(9)           not null
);

/*==============================================================*/
/* Table: SALA                                                  */
/*==============================================================*/
create table SALA (
	NSALA                int IDENTITY(1,1)    PRIMARY KEY,
	NOME                 text                 not null,
	OBS                  text                 null,
	DIMENSOES            numeric              null,
	PISO                 numeric(1)           null
);

/*==============================================================*/
/* Table: REUNIAO                                              */
/*==============================================================*/
create table REUNIAO (
	NREUNIAO             int IDENTITY(1,1)    PRIMARY KEY,
	NSALA                int                  FOREIGN KEY REFERENCES SALA(NSALA),
	NGESTOR              int                  FOREIGN KEY REFERENCES GESTOR(NGESTOR),
	NREQ                 int                  FOREIGN KEY REFERENCES REQUISITANTE(NREQ),
	OBS                  text                 null
);

/*==============================================================*/
/* Table: UTILIZADOR                                            */
/*==============================================================*/
create table UTILIZADOR (
	NUTILIZADOR          int IDENTITY(1,1)    PRIMARY KEY,
	USERNAME             text                 not null,
	PASSE                text                 not null,
	NOME                 text                 not null,
	MORADA               text                 null,
	EMAIL                text                 null,
	TLF                  numeric(9)           not null
);

/*==============================================================*/
/* Table: INSCRICAO                                             */
/*==============================================================*/
create table INSCRICAO (
	NINSCRICAO           int IDENTITY(1,1)    PRIMARY KEY,
	NREUNIAO             int                  FOREIGN KEY REFERENCES REUNIAO(NREUNIAO),
	NUTILIZADOR          int                  FOREIGN KEY REFERENCES UTILIZADOR(NUTILIZADOR)
);

/*==============================================================*/
/* Table: PARTICIPACAO                                          */
/*==============================================================*/
create table PARTICIPACAO (
	NPARTICIPACAO        int IDENTITY(1,1)    PRIMARY KEY,
	NREUNIAO             int                  FOREIGN KEY REFERENCES REUNIAO(NREUNIAO),
	NUTILIZADOR          int                  FOREIGN KEY REFERENCES UTILIZADOR(NUTILIZADOR)
);

/*==============================================================*/
/* Table: SERVICO                                              */
/*==============================================================*/
create table SERVICO (
	NSERVICO             int IDENTITY(1,1)    PRIMARY KEY,
	TIPO                 text                 null,
	OBS                  text                 null
);

/*==============================================================*/
/* Table: SERVICO_SALA                                          */
/*==============================================================*/
create table SERVICO_SALA (
	NSS                  int IDENTITY(1,1)    PRIMARY KEY,
	NSALA                int                  FOREIGN KEY REFERENCES SALA(NSALA),
	NSERVICO             int                  FOREIGN KEY REFERENCES SERVICO(NSERVICO)
);



/*Inserir dados nas tabelas*/

insert into GESTOR (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Arnaldo', 123456789, 'Rua da Pedra 123', 'olaeadeus@email.com', 912345678);
insert into GESTOR (NOME, NIF, MORADA, EMAIL, TLF)
	values ('José', 222222222, 'Rua da Escola 321', 'escola@email.com', 911111111);
insert into GESTOR (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Amanda', 333333333, 'Rua da Alface', 'email@email.com', 900000000);
insert into GESTOR (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Pedro', 123123123, 'Rua dos Coentros', 'aulas@email.com', 922333444);
insert into GESTOR (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Carlos', 456456456, 'Rua das Formigas 14', 'gestor@email.com', 988231543);
insert into GESTOR (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Constantino', 929292929, 'Rua Escura', 'const@email.com', 999999999);
insert into GESTOR (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Maria', 090921234, 'Rua Clara', 'marmar@email.com', 988977966);
insert into GESTOR (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Manuel', 765409876, 'Rua Amarela', 'mail@email.com', 912034056);
insert into GESTOR (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Artur', 837465920, 'Rua de Camões', 'meuemail@email.com', 921346576);
insert into GESTOR (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Hélder', 100992287, 'Rua B', 'gestorhelder@email.com', 908756423);

insert into DEPARTAMENTO (DEP, TLF, EMAIL, DESCRICAO)
	values ('Recursos humanos', 922111111, 'mailmail@mail.com', 'Problemas com o pessoal');
insert into DEPARTAMENTO (DEP, TLF, EMAIL, DESCRICAO)
	values ('Produção', 922111112, 'mail32@mail.com', 'Produção de software');
insert into DEPARTAMENTO (DEP, TLF, EMAIL, DESCRICAO)
	values ('Revisão', 922111113, 'email543@mail.com', 'Revisão de Software');
insert into DEPARTAMENTO (DEP, TLF, EMAIL, DESCRICAO)
	values ('Marketing', 922111114, 'correio@mail.com', 'Compra dos produtos');
insert into DEPARTAMENTO (DEP, TLF, EMAIL, DESCRICAO)
	values ('Contabildade', 922111115, 'correio8@mail.com', 'Contagem de dinheiros');
insert into DEPARTAMENTO (DEP, TLF, EMAIL, DESCRICAO)
	values ('Compras', 922111116, 'correio30@mail.com', 'Compra de miscelaneos');
insert into DEPARTAMENTO (DEP, TLF, EMAIL, DESCRICAO)
	values ('Finanças', 922111117, 'correio42@mail.com', 'Tratamento de dinheiros');
insert into DEPARTAMENTO (DEP, TLF, EMAIL, DESCRICAO)
	values ('Distribuição', 922111118, 'correio69@gmail.com', 'Distribuição do produto');
insert into DEPARTAMENTO (DEP, TLF, EMAIL, DESCRICAO)
	values ('Segurança', 922111119, 'correio9@mail.com', 'Segurança informática');
insert into DEPARTAMENTO (DEP, TLF, EMAIL, DESCRICAO)
	values ('Apoio ao cliente', 922222111, 'correiomail@mail.com', 'Apoio ao cliente');

insert into GERENCIA (NGESTOR, NDEP)
	values (1, 1);
insert into GERENCIA (NGESTOR, NDEP)
	values (2, 1);
insert into GERENCIA (NGESTOR, NDEP)
	values (3, 2);
insert into GERENCIA (NGESTOR, NDEP)
	values (3, 1);
insert into GERENCIA (NGESTOR, NDEP)
	values (3, 3);
insert into GERENCIA (NGESTOR, NDEP)
	values (4, 7);
insert into GERENCIA (NGESTOR, NDEP)
	values (6, 4);
insert into GERENCIA (NGESTOR, NDEP)
	values (7, 8);
insert into GERENCIA (NGESTOR, NDEP)
	values (8, 6);
insert into GERENCIA (NGESTOR, NDEP)
	values (9, 9);

insert into REQUISITANTE (NOME, NIF, MORADA, EMAIL, TLF)
	values ('João', 100000000, 'Rua da Moeda', 'mail@email.pt', 991222111);
insert into REQUISITANTE (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Joana', 100000001, 'Rua da Nota', 'mail1@email.pt', 991222112);
insert into REQUISITANTE (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Jacinto', 100000002, 'Rua da Alemanha', 'mail2@email.pt', 991222113);
insert into REQUISITANTE (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Mafalda', 100000003, 'Rua da Casa', 'mail3@email.pt', 991222114);
insert into REQUISITANTE (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Matilde', 100000004, 'Rua dos Amarelos', 'mail4@email.pt', 991222115);
insert into REQUISITANTE (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Marco', 100000005, 'Rua dos Planetas', 'mail5@email.pt', 991222116);
insert into REQUISITANTE (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Amando', 100000006, 'Rua da Lapa', 'mail6@email.pt', 991222117);
insert into REQUISITANTE (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Joaquim', 100000007, 'Rua do Contra', 'omail8@email.pt', 991222118);
insert into REQUISITANTE (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Arménio', 100000008, 'Rua das Letras', 'mail7@email.pt', 991222119);
insert into REQUISITANTE (NOME, NIF, MORADA, EMAIL, TLF)
	values ('Marlene', 100000009, 'Rua da Mansão', 'mail9@email.pt', 991222120);

insert into SALA (NOME, OBS, DIMENSOES, PISO)
	values ('A3', 'Lab. de computadores', 100, 1);
insert into SALA (NOME, OBS, DIMENSOES, PISO)
	values ('A5', 'Lab. de computadores', 160, 1);
insert into SALA (NOME, OBS, DIMENSOES, PISO)
	values ('A6', 'Lab. de computadores', 170, 1);
insert into SALA (NOME, OBS, DIMENSOES, PISO)
	values ('A9', 'Lab. de computadores', 120, 3);
insert into SALA (NOME, OBS, DIMENSOES, PISO)
	values ('B3', 'Sala de conferencias', 100, 4);
insert into SALA (NOME, OBS, DIMENSOES, PISO)
	values ('B7', 'Sala de conferencias', 140, 4);
insert into SALA (NOME, OBS, DIMENSOES, PISO)
	values ('C3', 'Sala de estar', 180, 3);
insert into SALA (NOME, OBS, DIMENSOES, PISO)
	values ('C1', 'Sala de estar', 80, 2);
insert into SALA (NOME, OBS, DIMENSOES, PISO)
	values ('C2', 'Sala de estar', 100, 2);
insert into SALA (NOME, OBS, DIMENSOES, PISO)
	values ('C4', 'Sala de estar', 110, 2);

insert into REUNIAO (NSALA, NGESTOR, NREQ, OBS)
	values (1, 2, 3, 'Reunião geral');
insert into REUNIAO (NSALA, NGESTOR, NREQ, OBS)
	values (3, 2, 1, 'Reunião geral');
insert into REUNIAO (NSALA, NGESTOR, NREQ, OBS)
	values (2, 2, 2, 'Reunião geral');
insert into REUNIAO (NSALA, NGESTOR, NREQ, OBS)
	values (4, 4, 4, 'Discussão da lógica da batata');
insert into REUNIAO (NSALA, NGESTOR, NREQ, OBS)
	values (4, 2, 3, 'Reunião geral');
insert into REUNIAO (NSALA, NGESTOR, NREQ, OBS)
	values (4, 2, 6, 'Discussão da lógica da batata');
insert into REUNIAO (NSALA, NGESTOR, NREQ, OBS)
	values (5, 9, 9, 'Futuro da empresa');
insert into REUNIAO (NSALA, NGESTOR, NREQ, OBS)
	values (3, 9, 5, 'Discussão da lógica da batata');
insert into REUNIAO (NSALA, NGESTOR, NREQ, OBS)
	values (8, 6, 3, 'Reunião geral');
insert into REUNIAO (NSALA, NGESTOR, NREQ, OBS)
	values (5, 6, 6, 'Reunião geral');

insert into UTILIZADOR (USERNAME, PASSE, NOME, MORADA, EMAIL, TLF)
	values ('Gamer123', 'Passwd', 'Guilherme', 'Bairro do Olá', 'mail@yahoomail.pt', 999292910);
insert into UTILIZADOR (USERNAME, PASSE, NOME, MORADA, EMAIL, TLF)
	values ('User', 'Passwd', 'Afonso', 'Bairro do Olá', 'mail1@yahoomail.pt', 999292911);
insert into UTILIZADOR (USERNAME, PASSE, NOME, MORADA, EMAIL, TLF)
	values ('Name', '123', 'Pedro', 'Bairro do Bairro', 'mail2@yahoomail.pt', 999292912);
insert into UTILIZADOR (USERNAME, PASSE, NOME, MORADA, EMAIL, TLF)
	values ('HAHA', '321', 'Jaqueline', 'Bairro do Backup', 'mail3@yahoomail.pt', 999292913);
insert into UTILIZADOR (USERNAME, PASSE, NOME, MORADA, EMAIL, TLF)
	values ('PTBR', '1111', 'Jack', 'Bairro das Roseiras', 'mail4@yahoomail.pt', 999292914);
insert into UTILIZADOR (USERNAME, PASSE, NOME, MORADA, EMAIL, TLF)
	values ('Utilizador3', '0000', 'Dave', 'Bairro do Rosáceas', 'mail5@yahoomail.pt', 999292915);
insert into UTILIZADOR (USERNAME, PASSE, NOME, MORADA, EMAIL, TLF)
	values ('Utilizador7', '0011', 'David', 'Bairro do Rosas', 'mail6@yahoomail.pt', 999292916);
insert into UTILIZADOR (USERNAME, PASSE, NOME, MORADA, EMAIL, TLF)
	values ('Albino', 'almondegas', 'Drácula', 'Bairro da Rosa', 'mail7@yahoomail.pt', 999292917);
insert into UTILIZADOR (USERNAME, PASSE, NOME, MORADA, EMAIL, TLF)
	values ('Utilizador00', '0110', 'Henrique', 'Bairro da Rua', 'mail8@yahoomail.pt', 999292918);
insert into UTILIZADOR (USERNAME, PASSE, NOME, MORADA, EMAIL, TLF)
	values ('Alpinista', '1000100', 'Daniel', 'Rua do Bairro', 'mail9@yahoomail.pt', 999292919);

insert into INSCRICAO (NREUNIAO, NUTILIZADOR)
	values (1, 2);
insert into INSCRICAO (NREUNIAO, NUTILIZADOR)
	values (10, 1);
insert into INSCRICAO (NREUNIAO, NUTILIZADOR)
	values (9, 3);
insert into INSCRICAO (NREUNIAO, NUTILIZADOR)
	values (8, 9);
insert into INSCRICAO (NREUNIAO, NUTILIZADOR)
	values (7, 8);
insert into INSCRICAO (NREUNIAO, NUTILIZADOR)
	values (6, 10);
insert into INSCRICAO (NREUNIAO, NUTILIZADOR)
	values (5, 7);
insert into INSCRICAO (NREUNIAO, NUTILIZADOR)
	values (4, 6);
insert into INSCRICAO (NREUNIAO, NUTILIZADOR)
	values (3, 5);
insert into INSCRICAO (NREUNIAO, NUTILIZADOR)
	values (2, 4);

insert into PARTICIPACAO (NREUNIAO, NUTILIZADOR)
	values (1, 2);
insert into PARTICIPACAO (NREUNIAO, NUTILIZADOR)
	values (10, 1);
insert into PARTICIPACAO (NREUNIAO, NUTILIZADOR)
	values (9, 3);
insert into PARTICIPACAO (NREUNIAO, NUTILIZADOR)
	values (8, 9);
insert into PARTICIPACAO (NREUNIAO, NUTILIZADOR)
	values (7, 8);
insert into PARTICIPACAO (NREUNIAO, NUTILIZADOR)
	values (6, 10);
insert into PARTICIPACAO (NREUNIAO, NUTILIZADOR)
	values (5, 7);
insert into PARTICIPACAO (NREUNIAO, NUTILIZADOR)
	values (4, 6);
insert into PARTICIPACAO (NREUNIAO, NUTILIZADOR)
	values (3, 5);
insert into PARTICIPACAO (NREUNIAO, NUTILIZADOR)
	values (2, 4);

insert into SERVICO (TIPO, OBS)
	values ('Manutenção', 'Feito');
insert into SERVICO (TIPO, OBS)
	values ('Limpeza', 'Feito');
insert into SERVICO (TIPO, OBS)
	values ('Manutenção', 'Não Feito');
insert into SERVICO (TIPO, OBS)
	values ('Limpeza', 'Feito');
insert into SERVICO (TIPO, OBS)
	values ('Limpeza', 'Não Feito');
insert into SERVICO (TIPO, OBS)
	values ('Manutenção', 'Não Feito');
insert into SERVICO (TIPO, OBS)
	values ('Manutenção', 'Feito');
insert into SERVICO (TIPO, OBS)
	values ('Limpeza', 'Feito');
insert into SERVICO (TIPO, OBS)
	values ('Manutenção', 'Não Feito');
insert into SERVICO (TIPO, OBS)
	values ('Limpeza', 'Mal Feito');

insert into SERVICO_SALA (NSALA, NSERVICO)
	values (1, 10);
insert into SERVICO_SALA (NSALA, NSERVICO)
	values (2, 9);
insert into SERVICO_SALA (NSALA, NSERVICO)
	values (3, 8);
insert into SERVICO_SALA (NSALA, NSERVICO)
	values (4, 7);
insert into SERVICO_SALA (NSALA, NSERVICO)
	values (5, 6);
insert into SERVICO_SALA (NSALA, NSERVICO)
	values (6, 5);
insert into SERVICO_SALA (NSALA, NSERVICO)
	values (7, 4);
insert into SERVICO_SALA (NSALA, NSERVICO)
	values (8, 3);
insert into SERVICO_SALA (NSALA, NSERVICO)
	values (9, 2);
insert into SERVICO_SALA (NSALA, NSERVICO)
	values (10, 1);
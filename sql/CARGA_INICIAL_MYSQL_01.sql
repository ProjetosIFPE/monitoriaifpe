DROP DATABASE IF EXISTS MONITORIAIFPE;
CREATE DATABASE MONITORIAIFPE;
USE MONITORIAIFPE;
DROP TABLE IF EXISTS MONITORIAIFPE.TB_USUARIO;
CREATE TABLE MONITORIAIFPE.TB_USUARIO (
	USUARIO_ID INTEGER AUTO_INCREMENT,
	USUARIO_NOME VARCHAR(200) NOT NULL,
	USUARIO_SOBRENOME VARCHAR(200) NOT NULL,
	USUARIO_LOGIN VARCHAR(20) NOT NULL,
	USUARIO_SENHA VARCHAR(40) NOT NULL,
	USUARIO_EMAIL VARCHAR(50) NOT NULL,
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	PRIMARY KEY(USUARIO_ID)
);

DROP TABLE IF EXISTS MONITORIAIFPE.TB_PERIODO;
CREATE TABLE MONITORIAIFPE.TB_PERIODO (
	PERIODO_ID INTEGER NOT NULL AUTO_INCREMENT,
	SEMESTRE ENUM('PRIMEIRO', 'SEGUNDO') NOT NULL,	
	PERIODO_ANO INTEGER NOT NULL,
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	PRIMARY KEY(PERIODO_ID)
);


DROP TABLE IF EXISTS MONITORIAIFPE.TB_CURSO;
CREATE TABLE MONITORIAIFPE.TB_CURSO (
	CURSO_ID INTEGER NOT NULL AUTO_INCREMENT,
	GRAU ENUM('SUPERIOR', 'TECNICO') NOT NULL,	
	CURSO_DS VARCHAR(20) NOT NULL,
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	PRIMARY KEY(CURSO_ID)
);

DROP TABLE IF EXISTS MONITORIAIFPE.TB_PROFESSOR;
CREATE TABLE MONITORIAIFPE.TB_PROFESSOR( 
	PROFESSOR_ID INTEGER,
	PRIMARY KEY(PROFESSOR_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (PROFESSOR_ID) REFERENCES TB_USUARIO(USUARIO_ID)
);

DROP TABLE IF EXISTS MONITORIAIFPE.TB_DISCIPLINA;
CREATE TABLE MONITORIAIFPE.TB_DISCIPLINA ( 
	DISCIPLINA_ID INT NOT NULL AUTO_INCREMENT,
	PROFESSOR_ID INT,
	DISCIPLINA_DS varchar(200) NOT NULL,
	CURSO_ID INTEGER NOT NULL,
	PRIMARY KEY(DISCIPLINA_ID),
	FOREIGN KEY (PROFESSOR_ID) REFERENCES TB_PROFESSOR(PROFESSOR_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (CURSO_ID) REFERENCES TB_CURSO(CURSO_ID)
);


DROP TABLE IF EXISTS MONITORIAIFPE.TB_ALUNO;
CREATE TABLE MONITORIAIFPE.TB_ALUNO (
	ALUNO_ID INTEGER NOT NULL,
	ALUNO_MATRICULA VARCHAR(20) NOT NULL,
	CURSO_ID INTEGER NOT NULL,
	PRIMARY KEY (ALUNO_ID),
	FOREIGN KEY (CURSO_ID) REFERENCES TB_CURSO(CURSO_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (ALUNO_ID) REFERENCES TB_USUARIO(USUARIO_ID)
);


DROP TABLE IF EXISTS MONITORIAIFPE.TB_DISCIPLINA_ALUNO;
CREATE TABLE MONITORIAIFPE.TB_DISCIPLINA_ALUNO ( 
	ALUNO_ID INT NOT NULL,
	DISCIPLINA_ID INT NOT NULL,
	PRIMARY KEY (ALUNO_ID, DISCIPLINA_ID),
	FOREIGN KEY (ALUNO_ID) REFERENCES TB_ALUNO(ALUNO_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (DISCIPLINA_ID) REFERENCES TB_DISCIPLINA(DISCIPLINA_ID)
);

DROP TABLE IF EXISTS MONITORIAIFPE.TB_MONITORIA;
CREATE TABLE MONITORIAIFPE.TB_MONITORIA ( 
	MONITOR_ID INT NOT NULL AUTO_INCREMENT,
	ALUNO_ID INT NOT NULL,
	MODALIDADE ENUM('BOLSISTA', 'VOLUNTARIO'),
	DISCIPLINA_ID INT NOT NULL,
	PERIODO_ID INTEGER NOT NULL,
	HABILITADO BOOLEAN NOT NULL,
	PRIMARY KEY(MONITOR_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY(ALUNO_ID) REFERENCES TB_ALUNO(ALUNO_ID),
	FOREIGN KEY(PERIODO_ID) REFERENCES TB_PERIODO(PERIODO_ID),
	FOREIGN KEY(DISCIPLINA_ID) REFERENCES TB_DISCIPLINA(DISCIPLINA_ID)
);


DROP TABLE IF EXISTS MONITORIAIFPE.TB_RELATORIO_FREQUENCIA;
CREATE TABLE MONITORIAIFPE.TB_RELATORIO_FREQUENCIA (
	RELATORIO_ID INT NOT NULL AUTO_INCREMENT,
	RELATORIO_MES INT(2) NOT NULL,
	MONITOR_ID INT NOT NULL,
	SITUACAO_RELATORIO ENUM('APROVADO', 'ESPERA') NOT NULL,	
	PRIMARY KEY (RELATORIO_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
        FOREIGN KEY (MONITOR_ID) REFERENCES TB_MONITORIA(MONITOR_ID)
);

DROP TABLE IF EXISTS MONITORIAIFPE.TB_SEMANA;
CREATE TABLE MONITORIAIFPE.TB_SEMANA(
	SEMANA_ID INT NOT NULL AUTO_INCREMENT,
	SEMANA_DESCRICAO VARCHAR(200),
	SEMANA_OBS VARCHAR(200),
	RELATORIO_ID INT,
	PRIMARY KEY(SEMANA_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (RELATORIO_ID) REFERENCES TB_RELATORIO_FREQUENCIA(RELATORIO_ID)
);

DROP TABLE IF EXISTS MONITORIAIFPE.TB_ATIVIDADE;
CREATE TABLE MONITORIAIFPE.TB_ATIVIDADE (
	ATIVIDADE_ID INT NOT NULL AUTO_INCREMENT,
	ATIVIDADE_DATA DATE,
	HORARIO_ENTRADA TIMESTAMP,
	HORARIO_SAIDA TIMESTAMP,
	SEMANA_ID INT,
	PRIMARY KEY(ATIVIDADE_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (SEMANA_ID) REFERENCES TB_SEMANA(SEMANA_ID)
);



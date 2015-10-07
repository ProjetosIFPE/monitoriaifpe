DROP DATABASE IF EXISTS PROJETO_PERIODO;
CREATE DATABASE PROJETO_PERIODO;
USE PROJETO_PERIODO;
DROP TABLE IF EXISTS PROJETO_PERIODO.USUARIO;
CREATE TABLE PROJETO_PERIODO.USUARIO (
	USUARIO_ID INTEGER AUTO_INCREMENT,
	USUARIO_NOME VARCHAR(200),
	USUARIO_LOGIN VARCHAR(20),
	USUARIO_SENHA VARCHAR(40),
	USUARIO_EMAIL VARCHAR(50),
	ULTIMO_ACESSO TIMESTAMP,
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	SENHA_EXPIRADA BOOLEAN,
	PRIMARY KEY(USUARIO_ID)
);


DROP TABLE IF EXISTS PROJETO_PERIODO.CURSO;
CREATE TABLE PROJETO_PERIODO.CURSO (
	CURSO_ID INTEGER NOT NULL AUTO_INCREMENT,
	GRAU ENUM('SUPERIOR', 'TECNICO') NOT NULL,	
	CURSO_DS VARCHAR(20) NOT NULL,
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	PRIMARY KEY(CURSO_ID)
);

DROP TABLE IF EXISTS PROJETO_PERIODO.PROFESSOR;
CREATE TABLE PROJETO_PERIODO.PROFESSOR( 
	PROFESSOR_ID INTEGER,
	PROFESSOR_SIAPE VARCHAR(20) NOT NULL,
	PRIMARY KEY(PROFESSOR_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (PROFESSOR_ID) REFERENCES USUARIO(USUARIO_ID)
);

DROP TABLE IF EXISTS PROJETO_PERIODO.DISCIPLINA;
CREATE TABLE PROJETO_PERIODO.DISCIPLINA ( 
	DISCIPLINA_ID INT NOT NULL AUTO_INCREMENT,
	PROFESSOR_ID INT NOT NULL,
	DISCIPLINA_DS varchar(200),
	CURSO_ID INTEGER NOT NULL,
	PRIMARY KEY(DISCIPLINA_ID),
	FOREIGN KEY (PROFESSOR_ID) REFERENCES PROFESSOR(PROFESSOR_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (CURSO_ID) REFERENCES CURSO(CURSO_ID)
);


DROP TABLE IF EXISTS PROJETO_PERIODO.ALUNO;
CREATE TABLE PROJETO_PERIODO.ALUNO (
	ALUNO_ID INTEGER NOT NULL,
	ALUNO_MATRICULA VARCHAR(20) NOT NULL,
	CURSO_ID INTEGER NOT NULL,
	PRIMARY KEY (ALUNO_ID),
	FOREIGN KEY (CURSO_ID) REFERENCES CURSO(CURSO_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (ALUNO_ID) REFERENCES USUARIO(USUARIO_ID)
);


DROP TABLE IF EXISTS PROJETO_PERIODO.DISCIPLINA_ALUNO;
CREATE TABLE PROJETO_PERIODO.DISCIPLINA_ALUNO ( 
	ALUNO_ID INT NOT NULL,
	DISCIPLINA_ID INT NOT NULL,
	PRIMARY KEY (ALUNO_ID, DISCIPLINA_ID),
	FOREIGN KEY (ALUNO_ID) REFERENCES ALUNO(ALUNO_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (DISCIPLINA_ID) REFERENCES DISCIPLINA(DISCIPLINA_ID)
);

DROP TABLE IF EXISTS PROJETO_PERIODO.MONITOR;
CREATE TABLE PROJETO_PERIODO.MONITOR ( 
	MONITOR_ID INT NOT NULL,
	MODALIDADE ENUM('BOLSISTA', 'VOLUNTARIO'),
	DISCIPLINA_ID INT NOT NULL,
	PRIMARY KEY(MONITOR_ID),
	FOREIGN KEY(MONITOR_ID) REFERENCES DISCIPLINA_ALUNO(ALUNO_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY(DISCIPLINA_ID) REFERENCES DISCIPLINA_ALUNO(DISCIPLINA_ID)
);


DROP TABLE IF EXISTS PROJETO_PERIODO.RELATORIO_FREQUENCIA;
CREATE TABLE PROJETO_PERIODO.RELATORIO_FREQUENCIA (
	RELATORIO_ID INT NOT NULL AUTO_INCREMENT,
	RELATORIO_ANO INT(4),
	RELATORIO_MES INT(2),
	RELATORIO_CARGA_HORARIA INT(2),
	MONITOR_ID INT,
	RELATORIO_EDITAL VARCHAR(6),
	PROFESSOR_ID INT,
	DATA_ENTREGA DATE,
	PRIMARY KEY (RELATORIO_ID),
	FOREIGN KEY (PROFESSOR_ID) REFERENCES PROFESSOR(PROFESSOR_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
    FOREIGN KEY (MONITOR_ID) REFERENCES MONITOR(MONITOR_ID)
);

DROP TABLE IF EXISTS PROJETO_PERIODO.SEMANA;
CREATE TABLE PROJETO_PERIODO.SEMANA(
	SEMANA_ID INT NOT NULL AUTO_INCREMENT,
	SEMANA_DESCRICAO VARCHAR(200),
	SEMANA_OBS VARCHAR(200),
	RELATORIO_ID INT,
	PRIMARY KEY(SEMANA_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (RELATORIO_ID) REFERENCES RELATORIO_FREQUENCIA(RELATORIO_ID)
);

DROP TABLE IF EXISTS PROJETO_PERIODO.ATIVIDADE;
CREATE TABLE PROJETO_PERIODO.ATIVIDADE (
	ATIVIDADE_ID INT NOT NULL AUTO_INCREMENT,
	ATIVIDADE_DATA DATE,
	HORARIO_ENTRADA VARCHAR(5),
	HORARIO_SAIDA VARCHAR(5),
	SEMANA_ID INT,
	PRIMARY KEY(ATIVIDADE_ID),
	ULTIMA_ALTERACAO TIMESTAMP DEFAULT current_timestamp() NOT NULL,
	FOREIGN KEY (SEMANA_ID) REFERENCES SEMANA(SEMANA_ID)
);


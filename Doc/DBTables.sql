DROP SCHEMA PortoZoca_Dev;
CREATE SCHEMA PortoZoca_Dev;
USE PortoZoca_Dev;

CREATE TABLE IF NOT EXISTS Produto (
    ProdutoId int not null AUTO_INCREMENT COMMENT 'Código',
    Referencia varchar(255) COMMENT 'Referência',
    Descricao varchar(255) COMMENT 'Descrição',
    Observacao text COMMENT 'Observação',
	PRIMARY KEY (ProdutoId)
);

CREATE TABLE IF NOT EXISTS Localizacao (
    LocalizacaoId int not null COMMENT 'Código',
    LocalizacaoSuperiorId int COMMENT 'Localização Superior',
    Divisao varchar(255) COMMENT 'Divisão',
    Observacao text COMMENT 'Observação',
	PRIMARY KEY (LocalizacaoId)
);

CREATE TABLE IF NOT EXISTS Lpn (
    LpnId int not null COMMENT 'Código',
    ProdutoId int COMMENT 'Produto',
    Quantidade double(10,3) COMMENT 'Quantidade',
    LpnContenedorId int COMMENT 'Contenedor',
    LocalizacaoId int COMMENT 'Localização',
	PRIMARY KEY (LpnId),
	FOREIGN KEY (ProdutoId) REFERENCES Produto(ProdutoId),
	FOREIGN KEY (LocalizacaoId) REFERENCES Localizacao(LocalizacaoId)
);

CREATE TABLE IF NOT EXISTS Usuario (
    UsuarioId int not null COMMENT 'Código',
    Apelido varchar(255) COMMENT 'Apelido',
    Senha varchar(255) COMMENT 'Senha',
    Supervisor bit COMMENT 'Supervisor',
	PRIMARY KEY (UsuarioId)
);

ALTER TABLE Localizacao
   ADD FOREIGN KEY (LocalizacaoSuperiorId) REFERENCES Localizacao(LocalizacaoId);

ALTER TABLE Lpn
   ADD FOREIGN KEY (LpnContenedorId) REFERENCES Lpn(LpnId);
ALTER TABLE Localizacao
   ADD FOREIGN KEY (LocalizacaoSuperiorId) REFERENCES Localizacao(LocalizacaoId);

ALTER TABLE Lpn
   ADD FOREIGN KEY (LpnContenedorId) REFERENCES Lpn(LpnId);

CREATE TABLE IF NOT EXISTS Produto (
    ProdutoId int not null,
    Referencia varchar(255),
    Descricao varchar(255),
    Observacao text,
	PRIMARY KEY (ProdutoId)
);

CREATE TABLE IF NOT EXISTS Localizacao (
    LocalizacaoId int not null,
    LocalizacaoSuperiorId int,
    Divisao varchar(255),
    Observacao text,
	PRIMARY KEY (LocalizacaoId)
);

CREATE TABLE IF NOT EXISTS Lpn (
    LpnId int not null,
    ProdutoId int,
    Quantidade double(10,3),
    LpnContenedorId int,
    LocalizacaoId int,
	PRIMARY KEY (LpnId),
	FOREIGN KEY (ProdutoId) REFERENCES Produto(ProdutoId),
	FOREIGN KEY (LocalizacaoId) REFERENCES Localizacao(LocalizacaoId)
);

CREATE TABLE IF NOT EXISTS Usuario (
    UsuarioId int not null,
    Apelido varchar(255),
    Senha varchar(255),
    Supervisor bit,
	PRIMARY KEY (UsuarioId)
);

ALTER TABLE Localizacao
   ADD FOREIGN KEY (LocalizacaoSuperiorId) REFERENCES Localizacao(LocalizacaoId);

ALTER TABLE Lpn
   ADD FOREIGN KEY (LpnContenedorId) REFERENCES Lpn(LpnId);

class Utilizador{
    constructor(id_utilizador, nome_utilizador, nome_completo, palavra_passe, email, data_nascimento, ativo){
        this.id_utilizador = id_utilizador;
        this.nome_utilizador = nome_utilizador;
        this.nome_completo = nome_completo;
        this.palavra_passe = palavra_passe;
        this.email = email;
        this.data_nascimento = data_nascimento;
    }
}

module.exports = Utilizador;
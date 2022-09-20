package conta.sistema.dominio.model;

public class Erro {

    public static void obrigatorio(String name){
        throw new NegocioException(name + " é obrigatório.");
    }

    public static void inexistente(String name){
        throw new NegocioException(name + " inexistente");
    }

    public static void saldoInsuficiente(){
        throw new NegocioException("Saldo insuficiente.");
    }

    public static void mesmaConta(){
        throw new NegocioException("Contas repetidas. Altere uma das contas.");
    }
}

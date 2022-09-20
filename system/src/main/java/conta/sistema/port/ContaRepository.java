package conta.sistema.port;

import conta.sistema.dominio.model.Conta;

public interface ContaRepository {

    Conta get(Integer numero);
    void alterar(Conta conta);
}

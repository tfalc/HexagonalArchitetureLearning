package conta.adapter;

import conta.sistema.dominio.model.Conta;
import conta.sistema.port.ContaRepository;

public class ContaFakeAdapterImpl implements ContaRepository {
    @Override
    public Conta get(Integer numero) {
        return null;
    }

    @Override
    public void alterar(Conta conta) {

    }
}

package conta.sistema.usecase.port;

import conta.sistema.dominio.model.Conta;

import java.math.BigDecimal;

public interface TransferPortUseCase {

    Conta getConta(Integer numero);
    void transfer(Integer contaDebito, Integer contaCredito, BigDecimal valor);
}

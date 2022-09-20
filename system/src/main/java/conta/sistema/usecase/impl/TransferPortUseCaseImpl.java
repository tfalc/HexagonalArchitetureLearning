package conta.sistema.usecase.impl;

import conta.sistema.dominio.model.Conta;
import conta.sistema.usecase.port.TransferPortUseCase;

import javax.inject.Named;
import java.math.BigDecimal;

@Named
public class TransferPortUseCaseImpl implements TransferPortUseCase {
    @Override
    public Conta getConta(Integer numero) {
        return null;
    }

    @Override
    public void transfer(Integer contaDebito, Integer contaCredito, BigDecimal valor) {

    }
}

package conta.sistema.usecase.impl;

import conta.sistema.dominio.model.Conta;
import conta.sistema.dominio.service.Transferencia;
import conta.sistema.port.ContaRepository;
import conta.sistema.usecase.port.TransferPortUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


import javax.inject.Named;
import java.math.BigDecimal;

import static conta.sistema.dominio.model.Erro.*;
import static java.util.Objects.isNull;

@Named
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferPortUseCaseImpl implements TransferPortUseCase {

    private ContaRepository repository;
    private Transferencia transferencia;

    @Override
    public Conta getConta(Integer numero) {
        return repository.get(numero);
    }

    @Override
    @Transactional
    public void transfer(Integer contaDebito, Integer contaCredito, BigDecimal valor) {
        //Validar parametros de entrada
        if(isNull(contaDebito)){
            obrigatorio("Conta débito é obrigatória.");
        }
        if(isNull(contaCredito)){
            obrigatorio("Conta crédito é obrigatória.");
        }
        if(isNull(valor)){
            obrigatorio("Valor é obrigatório.");
        }

        //Validas as contas
        var debito = repository.get(contaDebito);
        var credito = repository.get(contaCredito);

        if(isNull(debito)){
            inexistente("Conta débito é obrigatória.");
        }
        if(isNull(credito)){
            inexistente("Conta crédito é obrigatória.");
        }

        if(debito.getNumero().equals(credito.getNumero())){
            mesmaConta();
        }

        //Operações
        transferencia.transferencia(valor, debito, credito);
        repository.alterar(debito);
        repository.alterar(credito);
    }
}

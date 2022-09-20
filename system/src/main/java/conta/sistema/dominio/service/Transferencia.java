package conta.sistema.dominio.service;

import conta.sistema.dominio.model.Conta;

import javax.inject.Named;
import java.math.BigDecimal;

import static java.util.Objects.isNull;
import static conta.sistema.dominio.model.Erro.obrigatorio;

@Named
public class Transferencia {

    public void transferencia(BigDecimal valor, Conta debito, Conta credito){
        if(isNull(valor)){
            obrigatorio("Valor da transferência");
        }

        if(isNull(debito)){
            obrigatorio("Conta débito");
        }

        if(isNull(credito)){
            obrigatorio("Conta crédito");
        }

        debito.debitar(valor);
        credito.creditar(valor);
    }

}

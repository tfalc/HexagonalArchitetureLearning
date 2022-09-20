package conta.sistema.dominio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import static conta.sistema.dominio.model.Erro.obrigatorio;
import static conta.sistema.dominio.model.Erro.saldoInsuficiente;
import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    private Integer numero;
    private BigDecimal saldo;
    private String correntista;

    public void creditar (BigDecimal credito) {
        if(isNull(credito)){
            obrigatorio("Valor crédito");
        }
        if (credito.compareTo(BigDecimal.ZERO) <= 0){
            obrigatorio("Valor crédito");
        }

        saldo = saldo.add(credito);

    }

    public void debitar (BigDecimal debito) {
        if(isNull(debito)){
            obrigatorio("Valor débito");
        }
        if (debito.compareTo(BigDecimal.ZERO) <= 0){
            obrigatorio("Valor débito");
        }
        if (debito.compareTo(saldo) > 0){
            saldoInsuficiente();
        }

        saldo = saldo.subtract(debito);
    }
}

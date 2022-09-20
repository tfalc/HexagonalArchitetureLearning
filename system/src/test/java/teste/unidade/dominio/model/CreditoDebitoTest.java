package teste.unidade.dominio.model;

import conta.sistema.dominio.model.Conta;
import conta.sistema.dominio.model.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de crédito de conta")
public class CreditoDebitoTest {

    BigDecimal cem = new BigDecimal(100);
    Conta contaValida;

    @BeforeEach
    void preparar() {
        contaValida = new Conta(1, cem, "Thiago");
    }

    @Test
    @DisplayName("Valor de débito maior que crédito")
    void testeValorMaior(){
        try{
            contaValida.debitar(BigDecimal.valueOf(200));
            fail("Saldo insuficiente.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Saldo insuficiente.");
        }
    }
}

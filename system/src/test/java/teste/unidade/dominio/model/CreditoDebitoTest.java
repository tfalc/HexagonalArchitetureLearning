package teste.unidade.dominio.model;

import conta.sistema.dominio.model.Conta;
import conta.sistema.dominio.model.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de débito de conta")
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
            fail("Valor acima do saldo.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Saldo insuficiente.");
        }
    }

    @Test
    @DisplayName("Valor de débito negativo")
    void testeValorNegativo(){
        try{
            contaValida.debitar(BigDecimal.valueOf(-10));
            fail("Valor débito é obrigatório.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
        }
    }

    @Test
    @DisplayName("Valor de débito zerado")
    void testeValorZero(){
        try{
            contaValida.debitar(BigDecimal.valueOf(0));
            fail("Valor débito é obrigatório.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
        }
    }

    @Test
    @DisplayName("Valor de débito igual saldo")
    void testeValorIgualSaldo(){
        try{
            contaValida.debitar(cem);
            assertEquals(contaValida.getSaldo(), BigDecimal.ZERO, "Saldo deve zerar.");
        }catch (NegocioException e){
            fail("Saldo insuficiente.");
        }
    }

    @Test
    @DisplayName("Valor de débito menor que saldo")
    void testeValorMenorSaldo(){
        try{
            contaValida.debitar(BigDecimal.TEN);
            var saldoFinal = cem.subtract(BigDecimal.TEN);
            assertEquals(contaValida.getSaldo(), saldoFinal, "Saldo deve zerar.");
        }catch (NegocioException e){
            fail("Saldo insuficiente.");
        }
    }
}

package teste.unidade.dominio.service;

import conta.sistema.dominio.model.Conta;
import conta.sistema.dominio.model.NegocioException;
import conta.sistema.dominio.service.Transferencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de transferência")
public class TransferenciaTest {

    BigDecimal cem = new BigDecimal(100);
    BigDecimal vinte = new BigDecimal(20);
    Conta contaDebito;
    Conta contaCredito;
    Transferencia transferencia;

    @BeforeEach
    void preparar(){
        contaDebito = new Conta(1, cem, "Thiago");
        contaCredito = new Conta(2, cem, "Janaina");
        transferencia = new Transferencia();
    }

    @Test
    @DisplayName("Valor nulo como obrigatório")
    void transferenciaValorNull(){
        try {
            transferencia.transferencia(null, contaDebito, contaCredito);
            fail("Valor transferência obrigatório");
        } catch (NegocioException e){
            assertEquals(e.getMessage(), "Valor da transferência é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta débito como obrigatório")
    void transferenciaSemContaDebito(){
        try {
            transferencia.transferencia(vinte, null, contaCredito);
            fail("Conta débito é obrigatório.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Conta débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta crédito como obrigatório")
    void transferenciaSemContaCredito(){
        try {
            transferencia.transferencia(vinte, contaDebito, null);
            fail("Conta crédito é obrigatório");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Conta crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Transfere vinte")
    void transferenciaValorVinte(){
        try{
            transferencia.transferencia(vinte, contaDebito, contaCredito);
            assertEquals(contaDebito.getSaldo(), cem.subtract(vinte), "Saldo da conta débito deve bater");
            assertEquals(contaCredito.getSaldo(), cem.add(vinte), "Saldo da conta crédito deve bater");
        }catch (NegocioException e){
            fail("Deve transferir com sucesso");
        }
    }
}

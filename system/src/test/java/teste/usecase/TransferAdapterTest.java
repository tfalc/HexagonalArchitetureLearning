package teste.usecase;

import conta.sistema.dominio.model.NegocioException;
import conta.sistema.usecase.port.TransferPortUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste de caso de uso - Serviço de Transferência")
@ContextConfiguration(classes = FirstBuild.class)
@ExtendWith(SpringExtension.class)
public class TransferAdapterTest {

    Integer contaCredito = 10;
    Integer contaDebito = 20;
    Integer contaInexistente = 30;
    BigDecimal cem = new BigDecimal(100);
    BigDecimal cinquenta = new BigDecimal(50);

    @Inject
    TransferPortUseCase transferPortUseCase;

    @Test
    @DisplayName("Pesquisa conta com número nulo")
    void PesquisaContaNull() {
        System.out.println("=== Testando pesquisa de conta com número nulo ===");
        try {
            var conta = transferPortUseCase.getConta(null);
            assertTrue(conta == null, "Conta deve ser nula.");
        } catch (NegocioException e) {
            fail("Deve carregar uma conta nula.");
        }
    }

    @Test
    @DisplayName("Pesquisa conta com número inexistente")
    void pesquisaContaNumeroInexistente() {
        System.out.println("=== Testando pesquisa de conta com número inexistente ===");
        try {
            var conta = transferPortUseCase.getConta(contaInexistente);
            assertTrue(conta == null, "Conta inexistente");
        } catch (NegocioException e) {
            fail("Deve carregar uma conta nula.");
        }
    }

    @Test
    @DisplayName("Conta débito obrigatória")
    void contadebitoObrigatoria() {
        System.out.println("=== Testando pesquisa de conta com conta crédito nulo ===");
        try {
            transferPortUseCase.transfer(null, contaCredito, cinquenta);
            fail("Conta de débito é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta crédito obrigatória")
    void contaCreditoObrigatoria() {
        System.out.println("=== Testando pesquisa de conta com conta débito nulo ===");
        try {
            transferPortUseCase.transfer(contaDebito, null, cinquenta);
            fail("Conta de crédito é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor obrigatório")
    void valorObrigatorio() {
        System.out.println("=== Testando pesquisa de conta com valor nulo ===");
        try {
            transferPortUseCase.transfer(contaDebito, contaCredito, null);
            fail("Valor é obrigatório.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta débito inexistente")
    void contaDebitoInexistente() {
        System.out.println("=== Testando pesquisa de conta débito inexistente ===");
        try {
            transferPortUseCase.transfer(contaInexistente, contaCredito, cinquenta);
            fail("Conta débito inexistente.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta débito é inexistente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta crédito inexistente")
    void contaCrebitoInexistente() {
        System.out.println("=== Testando pesquisa de conta crédito inexistente ===");
        try {
            transferPortUseCase.transfer(contaDebito, contaInexistente, cinquenta);
            fail("Conta crédito inexistente.");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta crédito é inexistente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta crédito e débito iguais")
    void contaCrebitoDebitoIdenticas() {
        System.out.println("=== Testando pesquisa de conta débito e crédito iguais ===");
        try {
            transferPortUseCase.transfer(contaDebito, contaDebito, cinquenta);
            fail("Conta crédito e débito são as mesmas");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Contas repetidas. Altere uma das contas.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Débito de R$50 em conta com sucesso")
    void debitoSucessoEmConta() {
        System.out.println("=== Testando transferência com sucesso ===");
        try {
            transferPortUseCase.transfer(contaDebito, contaCredito, cinquenta);
        } catch (NegocioException e) {
            fail("Não deve gerar erro de transferência - " + e.getMessage());
        }

        try {
            var credito = transferPortUseCase.getConta(contaCredito);
            var debito = transferPortUseCase.getConta(contaDebito);
            assertEquals(credito.getSaldo(), cem.add(cinquenta), "Saldo de crédito deve bater");
            assertEquals(debito.getSaldo(), cem.subtract(cinquenta), "Saldo de crédito deve bater");
            System.out.println("Realizado transferência de R$" + cinquenta +
                    " da conta número " + debito.getNumero() +
                    " do senhor " + debito.getCorrentista() +
                    ". Saldo atual da conta número " + credito.getNumero() +
                    " do senhor " + credito.getCorrentista() + " é: R$" +
                    credito.getSaldo());
        } catch (NegocioException e) {
            fail("Não deve gerar erro de validação de saldo - " + e.getMessage());
        }
    }
}

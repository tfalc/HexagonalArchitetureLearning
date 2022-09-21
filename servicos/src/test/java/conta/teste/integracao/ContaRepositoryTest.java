package conta.teste.integracao;

import conta.sistema.dominio.model.NegocioException;
import conta.sistema.port.ContaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste de Serviço - Acesso a Banco de Dados")
@ContextConfiguration(classes = Config.class)
@ExtendWith(SpringExtension.class)
public class ContaRepositoryTest {

    @Inject
    ContaRepository contaRepository;

    @Test
    @DisplayName("Teste de funcionamento")
    void PesquisaContaNull() {
        System.out.println("=== Testando funcionamento da classe de testeContaRepository ===");
        try {
            System.out.println("Teste realizado com sucesso");
        } catch (NegocioException e) {
            fail("Teste falhou.");
        }
    }

    @Test
    @DisplayName("Pesquisa conta com número nulo")
    void pesquisaContaNulo(){
        try{
            var conta = contaRepository.get(null);
            assertNull(conta, "Conta deve ser nula");
        } catch (NegocioException e){
            fail("Deve carregar conta nula");
        }
    }

    @Test
    @DisplayName("Pesquisa conta com número inexistente")
    void pesquisaContaInexistente(){
        try{
            var conta = contaRepository.get(4371);
            assertNull(conta, "Conta deve ser nula");
        } catch (NegocioException e){
            fail("Deve carregar conta");
        }
    }

    @Test
    @DisplayName("Pesquisa conta existente")
    void pesquisaContaExistente(){
        try{
            var conta = contaRepository.get(50);
            assertNotNull(conta, "Conta deve estar preenchida");
        } catch (NegocioException e){
            fail("Não deve carregar conta nula");
        }
    }

    @Test
    @DisplayName("Alterar conta nula")
    void alterarContaNula(){
        try{
            contaRepository.alterar(null);
            fail("Não deve alterar conta nula.");
        } catch (NegocioException e){
            assertEquals(e.getMessage(), "Conta é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Alterar conta com sucesso")
    void alterarContaSucesso(){
        try{
            var conta = contaRepository.get(50);
            conta.setSaldo(new BigDecimal("1.00"));
            conta.setCorrentista("Alterado");
            contaRepository.alterar(conta);

            var contaDois = contaRepository.get(50);
            assertEquals(conta.getSaldo(), contaDois.getSaldo(), "Deve bater o saldo.");
            assertEquals(conta.getCorrentista(), contaDois.getCorrentista(), "Deve bater o correntista.");
        } catch (NegocioException e){
            fail("Não deve alterar a conta");
        }
    }
}

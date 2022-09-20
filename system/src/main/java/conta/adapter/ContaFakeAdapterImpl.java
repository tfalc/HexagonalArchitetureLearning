package conta.adapter;

import conta.sistema.dominio.model.Conta;
import conta.sistema.dominio.model.NegocioException;
import conta.sistema.port.ContaRepository;

import javax.inject.Named;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Named
public class ContaFakeAdapterImpl implements ContaRepository {

    private Map<Integer, Conta> banco = new HashMap<>();

    public ContaFakeAdapterImpl(){
        banco.put(10, new Conta(10, new BigDecimal(100), "Fake Silva"));
        banco.put(20, new Conta(20, new BigDecimal(100), "Fake Sauro"));
    }

    @Override
    public Conta get(Integer numero) {
        System.out.println("Fake conta -> get(Integer numero)");
        return banco.get(numero);
    }

    @Override
    public void alterar(Conta conta) {
        System.out.println("Fake conta -> get(Conta conta)");
        var contaTeste = banco.get(conta.getNumero());
        if(isNull(contaTeste)){
            banco.put(conta.getNumero(), conta);
        }else{
            throw new NegocioException("Conta inexistente: " + conta.getNumero());
        }
    }
}

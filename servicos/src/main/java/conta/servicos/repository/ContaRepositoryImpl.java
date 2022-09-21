package conta.servicos.repository;

import conta.sistema.dominio.model.Conta;
import conta.sistema.dominio.model.NegocioException;
import conta.sistema.port.ContaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

import static java.util.Objects.isNull;

@Named
public class ContaRepositoryImpl implements ContaRepository {

    private static final String ERRO = "Erro inesperado de acesso ao banco de dados.";


    private final JdbcTemplate jdbcTemplate;

    @Inject
    public ContaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Conta get(Integer numero) {
        if (isNull(numero)) {
            return null;
        }
        var sql = "SELECT * FROM conta WHERE numero = ?";
        var param = new Object[]{numero};
        RowMapper<Conta> orm = (resultSet, nm) ->
                new Conta(
                        resultSet.getInt(1),
                        resultSet.getBigDecimal(2),
                        resultSet.getString(3));
        try {
            var lista = jdbcTemplate.query(sql, param, orm);
            if (lista.isEmpty()) {
                return null;
            }
            return lista.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NegocioException(ERRO);
        }
    }

    @Transactional
    @Override
    public void alterar(Conta conta) {
        if (isNull(conta)) {
            throw new NegocioException("Conta é obrigatório.");
        }

        var sql = "UPDATE conta SET saldo = ?" +
                ", correntista = ?" +
                ", WHERE numero = ?";
        var param = new Object[]{conta.getSaldo(), conta.getCorrentista(), conta.getNumero()};

        try {
            jdbcTemplate.update(sql, param);
        } catch (Exception e){
            e.printStackTrace();
            throw new NegocioException(ERRO);
        }
    }
}

package conta.sistema.dominio.model;

public class NegocioException extends RuntimeException{
    public NegocioException(String mensagem){
        super(mensagem);
    }
}

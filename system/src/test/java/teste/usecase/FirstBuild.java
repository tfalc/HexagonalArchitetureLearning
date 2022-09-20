package teste.usecase;

import org.junit.jupiter.api.DisplayName;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        //objetos do sistema
        "conta.sistema",
        //fake adapters
        "conta.adapter"})
@DisplayName("Teste de primeiro build")
public class FirstBuild {

}

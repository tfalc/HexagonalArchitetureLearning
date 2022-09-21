package conta.dsv;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        //Front-end adapters
        "conta.dsv",
        "conta.tela",
        //System objects
        "conta.sistema",
        //Fake adapters
        "conta.adapter"
})
public class DevBuild {
}

package conta.tela;

import conta.dsv.SecondBuild;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaFxAdapter extends Application {

    private ApplicationContext spring;

    @Override
    public void start(Stage stage) throws Exception {
        var form = spring.getBean(TransferenciaFrm.class);
        form.mostrar(stage);
    }

    @Override
    public void init() throws Exception{
        System.out.println("Iniciando o Spring...");
        spring = new AnnotationConfigApplicationContext(SecondBuild.class);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

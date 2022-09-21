package conta.tela;

import conta.dsv.DevBuild;
import conta.hml.HomolBuild;
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
        spring = new AnnotationConfigApplicationContext(DevBuild.class);
        //spring = new AnnotationConfigApplicationContext(HomolBuild.class);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

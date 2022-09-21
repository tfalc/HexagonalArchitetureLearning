module conta.desktop {

    //Uses system account
    requires conta.system;

    //Spring
    requires javax.inject;
    requires spring.tx;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires java.sql;

    //JavaFX
    requires javafx.controls;

    //Screens and Builds
    opens conta.tela;
    opens conta.dsv;
    opens conta.hml;
    opens conta.prd;
}
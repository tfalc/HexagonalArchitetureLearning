module conta.servicos {

    //Uses system account
    requires conta.system;

    //Spring
    requires javax.inject;
    requires spring.tx;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires java.sql;
    requires spring.jdbc;

    //Open Repository
    opens conta.servicos.repository;
}
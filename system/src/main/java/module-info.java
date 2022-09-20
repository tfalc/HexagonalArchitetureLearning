module conta.system {

    requires javax.inject;
    requires spring.tx;
    requires lombok;

    //Exports
    exports conta.sistema.usecase.port;
    exports conta.sistema.dominio.model;
    exports conta.sistema.dominio.service;
    exports conta.sistema.usecase.impl;
    exports conta.adapter;
}
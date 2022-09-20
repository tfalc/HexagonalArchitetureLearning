module conta.system {

    requires javax.inject;
    requires spring.tx;
    requires lombok;

    //Exports
    exports conta.sistema.usecase.impl;
    exports conta.sistema.dominio.service;
    exports conta.adapter;
}
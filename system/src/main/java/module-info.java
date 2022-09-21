module conta.system {

    requires javax.inject;
    requires spring.tx;
    requires lombok;

    //Exports entry ports
    exports conta.sistema.usecase.port;
    exports conta.sistema.usecase.impl;

    //Exports business system
    exports conta.sistema.dominio.model;
    exports conta.sistema.dominio.service;

    //exports out ports
    exports conta.sistema.port;
    exports conta.adapter;

    //Open Spring Reflection
    opens conta.sistema.usecase.port;
    opens conta.sistema.usecase.impl;
    opens conta.sistema.dominio.service;
    opens conta.adapter;
}
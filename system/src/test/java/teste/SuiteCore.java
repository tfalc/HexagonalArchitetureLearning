package teste;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectPackages({
        //Teste de regras
        "teste.unidade.dominio.model",
        //Teste de serviços
        "teste.unidade.dominio.service",
        //Teste de uso de caso
        "teste.usecase"})
@SuiteDisplayName("Teste contas bancárias Suite")
public class SuiteCore {
}

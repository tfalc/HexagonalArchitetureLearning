package conta.hml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan({
        //Front-end adapters
        "conta.hml",
        "conta.tela",
        //System objects
        "conta.sistema",
        //Real adapters
        "conta.servicos.repository"
})
public class HomolBuild {

    @Bean
    public DataSource dataSource() {
        var builder = new EmbeddedDatabaseBuilder();
        var db = builder.setType(EmbeddedDatabaseType.HSQL.HSQL)
                .addScript("create-db.sql")
                .addScript("insert-homol.sql")
                .build();
        return db;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}

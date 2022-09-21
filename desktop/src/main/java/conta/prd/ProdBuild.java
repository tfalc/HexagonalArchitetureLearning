package conta.prd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@EnableTransactionManagement
@ComponentScan({
        //Front-end adapters
        "conta.prd",
        "conta.tela",
        //System objects
        "conta.sistema",
        //Real adapters
        "conta.servicos.repository"
})
public class ProdBuild {

    @Bean
    public DataSource dataSource() {
        var db = new SimpleDriverDataSource();
        db.setDriverClass((Class<? extends Driver>) SimpleDriverDataSource.class);
        db.setUrl("fooBar");
        db.setUsername("sa");
        db.setPassword("pass");
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

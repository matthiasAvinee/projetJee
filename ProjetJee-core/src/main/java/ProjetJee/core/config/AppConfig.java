package ProjetJee.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = { "ProjetJee.core.service" })
public class AppConfig {

    @Bean
    public Properties dbProperties() throws IOException {
        Properties props = new Properties();
       // props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        props.setProperty("driverClass", "com.mysql.jdbc.Driver");
        props.setProperty("jdbcUrl", "jdbc:mysql://localhost:3306/projet_JEE");
        props.setProperty("username", "root");
        props.setProperty("password", "root");
        return props;
    }
}

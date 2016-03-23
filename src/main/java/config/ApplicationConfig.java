package config;

import org.apache.catalina.Context;
import org.apache.jasper.servlet.JasperInitializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Class {@link ApplicationConfig} for data base configuration
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 23.03.16
 */

@Configuration
public class ApplicationConfig {

    private static final Integer TASKS_POOL_SIZE = 10;

    @Value("${PORT:8080}")
    private int port;

//    @Value("${MYSQL_URL}")
    private String url;

//    @Value("${MYSQL_USERNAME}")
    private String username;

//    @Value("${MYSQL_PASSWORD}")
    private String password;


    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService taskExecutor() {
        return Executors.newScheduledThreadPool(TASKS_POOL_SIZE);
    }


    @Bean
    public TomcatEmbeddedServletContainerFactory factory() {

        TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = new TomcatEmbeddedServletContainerFactory();
        tomcatEmbeddedServletContainerFactory.addContextCustomizers(new TomcatContextCustomizer() {

            @Override
            public void customize(Context context) {
                context.addServletContainerInitializer(new JasperInitializer(), Collections.<Class<?>>emptySet());
            }
        });
        return tomcatEmbeddedServletContainerFactory;
    }


    @Bean
    public DataSource makeDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
        dataSource.setUrl("jdbc:mysql://localhost:3306/internetshop");
        dataSource.setUsername("root");
        dataSource.setPassword("skityashin");
        return dataSource;
    }
}

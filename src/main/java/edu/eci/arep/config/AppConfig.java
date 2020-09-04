package edu.eci.arep.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * Clase que configura MyBatis para acceder a la informacion de una base de datos externa
 */

@Configuration
@MapperScan("edu.eci.arep.myBatis")
public class AppConfig {

	/**
	 * Construye un dataSource de la base de datos
	 * @return DataSource con la sesion de la base de datos
	 */
	@Bean
	public DataSource dataSource() {
        SimpleDriverDataSource sdds = new SimpleDriverDataSource();
		sdds.setDriverClass(org.postgresql.Driver.class);
		sdds.setUrl("jdbc:postgresql://ec2-184-73-249-9.compute-1.amazonaws.com:5432/d3g5v3hgha9p7a?sslmode=require");
		sdds.setUsername("myfwjbrbsxucok");
        sdds.setPassword("046a0735e148d0ccbed8a19b9c3efd04b964a75835860e585ddc5d818fa0939e");
		return sdds;
	}

	/**
	 * Construye un dataSourceTransactionManager
	 * @return DataSourceTransactionManager con la sesion de la base de datos
	 */
	@Bean
	public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

	/**
	 * Construye una sesion sql 
	 * @return SqlSessionFactoryBean bean con la sesion de la base de datos
	 * @throws Exception si la sesion no se pudo crear
	 */
    @Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
		return sessionFactory;
	}
}
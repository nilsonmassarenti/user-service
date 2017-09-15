package com.nilsonmassarenti.app.userservice.config;

import java.io.File;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import com.nilsonmassarenti.app.userservice.entity.Phone;
import com.nilsonmassarenti.app.userservice.entity.User;

@Configuration
public class DataSourceConfig {
	
	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}
	
	@Bean
	public DataSource getDataSource(){
		File f = new File("file.txt");
        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());
        try {
            System.out.println(f.getCanonicalPath());
        } catch (Exception e) {
			// TODO: handle exception
		}
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		String file = classLoader.getResource("db/db.sql").getPath();
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
			.addScript(file)
			.build();
		return db;
	}
	
	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(getDataSource())
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Phone.class)
				.buildSessionFactory();
	}
	
	
	
	
}

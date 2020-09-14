package lineageM.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement// annotation기반 트랜잭션 활성화
@Configuration
public class TransactionConfig {
	
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	DataSource dataSource;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	    factoryBean.setDataSource( dataSource );
	    factoryBean.setVfs(SpringBootVFS.class);
	    // 매퍼 설정
	    Resource[] mapperLocations=applicationContext.getResources("classpath:/mapper/**/mapper-*.xml"); 
	    //mapper/**/ : mapper폴더 아래의 모든 폴더   
	    //mapper-*.xml: mapper-로 시작하고, 확장자는 모든파일
	    factoryBean.setMapperLocations(mapperLocations);
	    return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		//System.out.println("SqlSessionFactory :" +sqlSessionFactory());
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception{
		//System.out.println("dataSource: "+dataSource);
		return new DataSourceTransactionManager(dataSource);
	}

}

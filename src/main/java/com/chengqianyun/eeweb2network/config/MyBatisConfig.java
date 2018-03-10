package com.chengqianyun.eeweb2network.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/3/10
 */
@Configuration
public class MyBatisConfig {

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    try {
      ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
      bean.setConfigLocation(resolver.getResource("classpath:mybatis/mybatis.xml"));
      return bean.getObject();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean
  public DataSourceTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
package com.ffl.blog.dal.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 *
 * 具体见文档 http://mybatis.org/spring/zh/factorybean.html
 *
 * @author lff
 * @datetime 2020/01/01 13:57
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    private static final String DATA_SOURCE = "datasource";

    private static final String SQL_SESSION_FACTORY = "sqlSessionFactory";

    private static final String CONFIG_PATH = "/mybatis/mybatis-config.xml";
    private static final String MAPPER_PATH = "/mybatis/mapper/*.xml";
    private static final String BASE_DAO_PACKAGE = "com.ffl.blog.dal.dao";

    /**
     * 用户名
     */
    private static final String USER_NAME = "blog";

    /**
     * 密码
     */
    private static final String PASSWORD = "blog";

    /**
     * 数据库
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/blog";

    /**
     * 创建数据源
     * //,initMethod = "init",destroyMethod = "destroy"
     *
     * @return
     */
    // @Bean(name = DATA_SOURCE,initMethod = "init",destroyMethod = "destroy")
    @Bean(name = DATA_SOURCE)
    public DataSource getDataSource() {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver(DRIVER);
        pooledDataSource.setUrl(URL);
        pooledDataSource.setUsername(USER_NAME);
        pooledDataSource.setPassword(PASSWORD);
        return pooledDataSource;
    }

    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory getSqlSessionFactory(@Qualifier(DATA_SOURCE) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //巨坑：一个是 getResources 一个是 getResource
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_PATH));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(CONFIG_PATH));
        sqlSessionFactoryBean.setDataSource(dataSource);

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }


    /**
     * 配置事务管理器
     *
     * @param dataSource
     * @return
     */
    //@Bean
    //public TransactionManager transactionManager(@Qualifier(DATA_SOURCE) DataSource dataSource) {
    //    return new DataSourceTransactionManager(dataSource);
    //}

    //@Bean
    //public SqlSessionTemplate sqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory){
    //    return new SqlSessionTemplate(sqlSessionFactory);
    //}

    /**
     *
     * 注入映射器
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(SQL_SESSION_FACTORY);
        mapperScannerConfigurer.setBasePackage(BASE_DAO_PACKAGE);
        return mapperScannerConfigurer;
    }
}

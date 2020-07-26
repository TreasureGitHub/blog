package com.ffl.blog.dal.test.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 具体见文档 http://mybatis.org/spring/zh/factorybean.html
 *
 * @author lff
 * @datetime 2020/01/01 13:57
 */
@Configuration
@EnableTransactionManagement
public class TestDSConfig {

    private static final String DATA_SOURCE_TEST = "datasource-test";

    private static final String SQL_SESSION_FACTORY_TEST = "sqlSessionFactory-test";

    private static final String TX_MANAGER_TEXT = "transactionManager-test";

    private static final String CONFIG_PATH_TEST = "/mybatis/mybatis-config-test.xml";
    private static final String MAPPER_PATH_TEST = "/mybatis/test/*.xml";
    private static final String BASE_DAO_PACKAGE_TEST = "com.ffl.blog.dal.test.dao";
    private static final String MAPPER_SCAN_CONFIG_TEST = "mmapperScannerConfigurer-test";

    /**
     * 用户名
     */
    private static final String USER_NAME_TEST = "root";

    /**
     * 密码
     */
    private static final String PASSWORD_TEST = "root";

    /**
     * 数据库
     */
    private static final String DRIVER_TEST = "com.mysql.cj.jdbc.Driver";

    private static final String URL_TEST = "jdbc:mysql://localhost:3306/test";

    /**
     * 创建数据源
     * //,initMethod = "init",destroyMethod = "destroy"
     *
     * @return
     */
    // @Bean(name = DATA_SOURCE,initMethod = "init",destroyMethod = "destroy")
    @Bean(name = DATA_SOURCE_TEST)
    public DataSource getDataSource() {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver(DRIVER_TEST);
        pooledDataSource.setUrl(URL_TEST);
        pooledDataSource.setUsername(USER_NAME_TEST);
        pooledDataSource.setPassword(PASSWORD_TEST);
        return pooledDataSource;
    }

    @Bean(name = SQL_SESSION_FACTORY_TEST)
    public SqlSessionFactory getSqlSessionFactory(@Qualifier(DATA_SOURCE_TEST) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //巨坑：一个是 getResources 一个是 getResource
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_PATH_TEST));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(CONFIG_PATH_TEST));
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
    @Bean(name = TX_MANAGER_TEXT)
    public TransactionManager transactionManager(@Qualifier(DATA_SOURCE_TEST) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //@Bean
    //public SqlSessionTemplate sqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory){
    //    return new SqlSessionTemplate(sqlSessionFactory);
    //}

    /**
     * 注入映射器
     *
     * @return
     */
    @Bean(name = MAPPER_SCAN_CONFIG_TEST)
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(SQL_SESSION_FACTORY_TEST);
        mapperScannerConfigurer.setBasePackage(BASE_DAO_PACKAGE_TEST);
        return mapperScannerConfigurer;
    }
}

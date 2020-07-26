package com.ffl.blog.dal.blog.config;

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
 * 1.创建数据源
 * 2.创建getSqlSessionFactory
 * 3.事务管理器
 * 4.设置mapper
 *
 * @author lff
 * @datetime 2020/01/01 13:57
 */
@Configuration
@EnableTransactionManagement
public class BlogDSConfig {

    private static final String DATA_SOURCE_BLOG = "datasource-blog";

    private static final String SQL_SESSION_FACTORY_BLOG = "sqlSessionFactory-blog";

    private static final String TX_MANAGER_BLOG = "transactionManager-blog";

    private static final String CONFIG_PATH_BLOG = "/mybatis/mybatis-config-blog.xml";
    private static final String MAPPER_PATH_BLOG = "/mybatis/blog/*.xml";
    private static final String BASE_DAO_PACKAGE_BLOG = "com.ffl.blog.dal.blog.dao";
    private static final String MAPPER_SCAN_CONFIG_BLOG = "mmapperScannerConfigurer-blog";


    /**
     * 用户名
     */
    private static final String USER_NAME_BLOG = "blog";

    /**
     * 密码
     */
    private static final String PASSWORD_BLOG = "blog";

    /**
     * 数据库
     */
    private static final String DRIVER_BLOG = "com.mysql.cj.jdbc.Driver";

    private static final String URL_BLOG = "jdbc:mysql://localhost:3306/blog";

    /**
     * 创建数据源
     * //,initMethod = "init",destroyMethod = "destroy"
     *
     * @return
     */
    // @Bean(name = DATA_SOURCE,initMethod = "init",destroyMethod = "destroy")
    @Bean(name = DATA_SOURCE_BLOG)
    public DataSource getDataSource() {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver(DRIVER_BLOG);
        pooledDataSource.setUrl(URL_BLOG);
        pooledDataSource.setUsername(USER_NAME_BLOG);
        pooledDataSource.setPassword(PASSWORD_BLOG);
        return pooledDataSource;
    }

    @Bean(name = SQL_SESSION_FACTORY_BLOG)
    public SqlSessionFactory getSqlSessionFactory(@Qualifier(DATA_SOURCE_BLOG) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //巨坑：一个是 getResources 一个是 getResource
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_PATH_BLOG));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(CONFIG_PATH_BLOG));
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
    @Bean(name = TX_MANAGER_BLOG)
    public TransactionManager transactionManager(@Qualifier(DATA_SOURCE_BLOG) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // @Bean
    // public SqlSessionTemplate sqlSessionTemplate(@Qualifier(SQL_SESSION_FACTORY_BLOG) SqlSessionFactory sqlSessionFactory){
    //    return new SqlSessionTemplate(sqlSessionFactory);
    // }

    /**
     * 注入映射器
     *
     * @return
     */
    @Bean(name = MAPPER_SCAN_CONFIG_BLOG)
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(SQL_SESSION_FACTORY_BLOG);
        mapperScannerConfigurer.setBasePackage(BASE_DAO_PACKAGE_BLOG);
        return mapperScannerConfigurer;
    }
}

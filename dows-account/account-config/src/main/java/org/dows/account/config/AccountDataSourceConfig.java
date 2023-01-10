package org.dows.account.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dows.framework.crud.mybatis.utils.FieldFillHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.dows.account.mapper", sqlSessionTemplateRef = "accountSqlSessionTemplate")
public class AccountDataSourceConfig {

    @Autowired
    private FieldFillHandler fieldFillHandler;

    @Autowired
    private MybatisPlusProperties mybatisPlusProperties;

    @Bean(name = "accountHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = "accountDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.account")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "accountDataSource")
    public DataSource accountDataSource() {
        // 获取本应用对于的数据源
        DataSourceProperties dataSourceProperties = dataSourceProperties();
        // 设置连接池
        HikariConfig hikariConfig = hikariConfig();
        /**
         * 转换
         * 或者hikariConfig.setDataSourceProperties();
         */
        hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
        hikariConfig.setUsername(dataSourceProperties.getUsername());
        hikariConfig.setPassword(dataSourceProperties.getPassword());
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }

    @Bean(name = "accountSqlSessionFactory")
    public SqlSessionFactory accountSqlSessionFactory(@Qualifier("accountDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        /**
         * 设置global
         * GlobalConfig globalConfig = new GlobalConfig();
         * globalConfig.setMetaObjectHandler(fieldFillHandler);
         * globalConfig.setBanner(false);
         */
        GlobalConfig globalConfig = mybatisPlusProperties.getGlobalConfig();
        globalConfig.setMetaObjectHandler(fieldFillHandler);
        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);
        /**
         * 设置config
         */
        MybatisConfiguration configuration = mybatisPlusProperties.getConfiguration();
        mybatisSqlSessionFactoryBean.setConfiguration(configuration);
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        /**
         * 设置mapper
         * classpath*:account/mapper/*Mapper.xml
         * classpath*:rbac/mapper/*Mapper.xml
         * classpath*:user/mapper/*Mapper.xml
         * classpath*:member/mapper/*Mapper.xml
         * mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
         * getResources("classpath*:account/mapper/*Mapper.xml," +
         *                         "classpath*:rbac/mapper/*Mapper.xml," +
         *                         "classpath*:user/mapper/*Mapper.xml"));
         */
        mybatisSqlSessionFactoryBean.setMapperLocations(mybatisPlusProperties.resolveMapperLocations());

        SqlSessionFactory sqlSessionFactory = mybatisSqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);

        // 添加插件
        //mybatisSqlSessionFactoryBean.setPlugins();
        return mybatisSqlSessionFactoryBean.getObject();
    }


    // 配置 mapper 扫描路径
//    @Bean(name = "accountMapperScannerConfigurer")
//    public MapperScannerConfigurer accountMapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("org.dows.account.mapper.*");
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("accountSqlSessionTemplate");
//        return mapperScannerConfigurer;
//    }


    @Bean(name = "accountTransactionManager")
    public DataSourceTransactionManager accountTransactionManager(@Qualifier("accountDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "accountSqlSessionTemplate")
    public SqlSessionTemplate odsSqlSessionTemplate(@Qualifier("accountSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
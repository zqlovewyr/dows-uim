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
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@AutoConfiguration(before = )
@Configuration("accountDataSourceConfig")
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
        return mybatisSqlSessionFactoryBean.getObject();
    }

    @Bean(name = "accountTransactionManager")
    public DataSourceTransactionManager accountTransactionManager(@Qualifier("accountDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "accountSqlSessionTemplate")
    public SqlSessionTemplate accountSqlSessionTemplate(@Qualifier("accountSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 自动插入创建和更新时间
     * 多数据源下要创建多个，或者不创建，不然saveBatch等批量操作方法会报错，因为save方法是获取baseMapper，而批量操作的saveBatch方法是从全局配置 GlobalConfig 里获取的。
     */
    @Bean("accountGlobalConfig")
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new FieldFillHandler());
        return globalConfig;
    }



    // 配置 mapper 扫描路径
//    @Bean(name = "accountMapperScannerConfigurer")
//    public MapperScannerConfigurer accountMapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        //mapperScannerConfigurer.setProcessPropertyPlaceHolders(true);
//        mapperScannerConfigurer.setBasePackage("org.dows.account.mapper");
//        mapperScannerConfigurer.setSqlSessionTemplateBeanName("accountSqlSessionTemplate");
//        return mapperScannerConfigurer;
//    }
}
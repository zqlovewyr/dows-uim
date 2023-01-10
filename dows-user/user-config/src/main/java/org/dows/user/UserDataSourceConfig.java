package org.dows.user;

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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

@Configuration("userDataSourceConfig")
@MapperScan(basePackages = "org.dows.user.mapper", sqlSessionTemplateRef = "userSqlSessionTemplate")
public class UserDataSourceConfig {

    @Autowired
    private FieldFillHandler fieldFillHandler;

    @Autowired
    private MybatisPlusProperties mybatisPlusProperties;

    @Bean(name = "userHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig userHikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = "userDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "userDataSource")
    public DataSource userDataSource() {
        // 获取本应用对于的数据源
        DataSourceProperties dataSourceProperties = userDataSourceProperties();
        // 设置连接池
        HikariConfig hikariConfig = userHikariConfig();
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

    @Bean(name = "userSqlSessionFactory")
    @Primary
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSource") DataSource dataSource) throws Exception {
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

    @Bean(name = "userTransactionManager")
    public DataSourceTransactionManager userTransactionManager(@Qualifier("userDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "userSqlSessionTemplate")
    public SqlSessionTemplate odsSqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
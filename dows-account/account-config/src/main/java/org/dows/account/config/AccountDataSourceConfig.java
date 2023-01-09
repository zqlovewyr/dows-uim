package org.dows.account.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dows.framework.crud.mybatis.utils.FieldFillHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.dows.account.mapper", sqlSessionTemplateRef = "accountSqlSessionTemplate")
public class AccountDataSourceConfig {

    @Autowired
    private FieldFillHandler fieldFillHandler;

    @Bean(name = "accountDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.account")
    public DataSource accountDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "accountSqlSessionFactory")
    public SqlSessionFactory accountSqlSessionFactory(@Qualifier("accountDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(fieldFillHandler);
        globalConfig.setBanner(false);
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        /**
         * classpath*:account/mapper/*Mapper.xml
         * classpath*:rbac/mapper/*Mapper.xml
         * classpath*:user/mapper/*Mapper.xml
         * classpath*:member/mapper/*Mapper.xml
         */
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:account/mapper/*Mapper.xml," +
                        "classpath*:rbac/mapper/*Mapper.xml," +
                        "classpath*:user/mapper/*Mapper.xml"));
        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);
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
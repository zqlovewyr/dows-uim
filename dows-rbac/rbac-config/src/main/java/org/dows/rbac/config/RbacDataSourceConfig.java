//package org.dows.rbac.config;
//
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.dows.framework.crud.mybatis.utils.FieldFillHandler;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "org.dows.rbac.mapper", sqlSessionTemplateRef = "rbacSqlSessionTemplate")
//public class RbacDataSourceConfig {
//
//    @Autowired
//    private FieldFillHandler fieldFillHandler;
//
//    @Bean(name = "rbacDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.rbac")
//    public DataSource accountDataSource() {
//        return new HikariDataSource();
//    }
//
//    @Bean(name = "rbacSqlSessionFactory")
//    public SqlSessionFactory accountSqlSessionFactory(@Qualifier("rbacDataSource") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setMetaObjectHandler(fieldFillHandler);
//        globalConfig.setBanner(false);
//        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
//        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:rbac/mapper/*Mapper.xml"));
//        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);
//        SqlSessionFactory sqlSessionFactory = mybatisSqlSessionFactoryBean.getObject();
//        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
//
//        // 添加插件
//        //mybatisSqlSessionFactoryBean.setPlugins();
//        return mybatisSqlSessionFactoryBean.getObject();
//    }
//
//    @Bean(name = "rbacTransactionManager")
//    public DataSourceTransactionManager accountTransactionManager(@Qualifier("rbacDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "rbacSqlSessionTemplate")
//    public SqlSessionTemplate rbacSqlSessionTemplate(@Qualifier("rbacSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
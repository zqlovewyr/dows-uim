//package org.dows.account.config;
//
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.dows.framework.crud.mybatis.utils.FieldFillHandler;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "org.dows.account.mapper.*", sqlSessionTemplateRef = "accountSqlSessionTemplate")
//public class AccountMybatisConfig {
//
///*    @Bean(name = "accountDataSourceProperties")
//    @Qualifier("accountDataSourceProperties")
//    @ConfigurationProperties(prefix = "spring.datasource.account")
//    public DataSourceProperties accountDataSourceProperties() {
//        return new DataSourceProperties();
//    }*/
//    @Resource
//    private AccountDataSourceProperties properties;
//
//    @Bean(name = "accountDataSource")
//    @Qualifier("accountDataSource")
//    public DataSource accountDataSource() {
// /*       DataSourceProperties properties1 = properties.getInnerMap().get("account");*/
//        DataSourceProperties properties1 = new DataSourceProperties();
//        return properties1.initializeDataSourceBuilder().build();
//    }
//
//
//    @Bean(name = "accountSqlSessionFactory")
//    public SqlSessionFactory accountSqlSessionFactory(@Qualifier("accountDataSource") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setMetaObjectHandler(new FieldFillHandler());
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
//        return bean.getObject();
//    }
//
//    @Bean(name = "accountTransactionManager")
//    public DataSourceTransactionManager odsTransactionManager(@Qualifier("accountDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "accountSqlSessionTemplate")
//    public SqlSessionTemplate accountSqlSessionTemplate(@Qualifier("accountSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}

package org.dows.user.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dows.framework.crud.mybatis.utils.FieldFillHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.dows.user.mapper", sqlSessionTemplateRef = "userSqlSessionTemplate")
public class UserDataSourceConfig {

    @Autowired
    private FieldFillHandler fieldFillHandler;

    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource userDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "userSqlSessionFactory")
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSource") DataSource dataSource) throws Exception {
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
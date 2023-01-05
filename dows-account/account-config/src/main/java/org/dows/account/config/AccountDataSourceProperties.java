package org.dows.account.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@ComponentScan("org.dows.account.mapper.*")
@PropertySource(value = {"classpath:application-mysql.yml"})
@Data
public class AccountDataSourceProperties {
    @Value("${spring.datasource.account}")
    private Map<String, DataSourceProperties> innerMap;
}

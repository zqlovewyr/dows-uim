package org.dows.account.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@ComponentScan("org.dows.account.mapper.*")
@Data
public class AccountDataSourceProperties {
    @Value("${spring.datasource.account}")
    private Map<String, DataSourceProperties> innerMap;
}

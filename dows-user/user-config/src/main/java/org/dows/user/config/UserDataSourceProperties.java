package org.dows.user.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@ComponentScan("org.dows.user.mapper.*")
@Data
public class UserDataSourceProperties {
    @Value("${spring.datasource.user}")
    private Map<String, DataSourceProperties> innerMap;
}

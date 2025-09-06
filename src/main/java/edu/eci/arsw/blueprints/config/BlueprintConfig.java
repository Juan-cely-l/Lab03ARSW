package edu.eci.arsw.blueprints.config;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.filters.impl.RedundancyFilter;
import edu.eci.arsw.blueprints.filters.impl.SubsamplingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BlueprintConfig {

    @Bean(name="redundancyFilter")
    public BlueprintFilter redundancyFilter() {
        return new RedundancyFilter();
    }

    @Bean(name="subsamplingFilter")
    @Primary
    public BlueprintFilter subsamplingFilter() {
        return new SubsamplingFilter();
    }
}
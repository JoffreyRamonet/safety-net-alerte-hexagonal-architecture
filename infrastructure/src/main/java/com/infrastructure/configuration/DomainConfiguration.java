package com.infrastructure.configuration;


import com.domain.ddd.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Class to give to Spring the base package and classes annotations to create beans.
 */
@Configuration
@ComponentScan(
        basePackages = {"com.domain"},
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {DomainService.class})
        })
public class DomainConfiguration {

}

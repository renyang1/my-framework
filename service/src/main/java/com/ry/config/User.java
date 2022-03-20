package com.ry.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 10023991
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@ConfigurationProperties(prefix = "ry")
//@Component
public class User {
    private String name;
    private int age;
    private Cat cat;
}

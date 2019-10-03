package otonglet.HV1735.testcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import otonglet.HV1735.testcase.configuration.NoOpMessageInterpolator;

@SpringBootApplication
public class TestCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCaseApplication.class, args);
    }

    @Bean
    LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setMessageInterpolator(new NoOpMessageInterpolator());
        return factory;
    }
}
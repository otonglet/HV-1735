package otonglet.HV1735.testcase.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("otonglet.HV1735.testcase.entity")
@EnableTransactionManagement
public class PersistenceConfiguration {
}

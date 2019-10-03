package otonglet.HV1735.testcase;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import otonglet.HV1735.testcase.dao.LazyChildDao;
import otonglet.HV1735.testcase.dao.ParentDao;
import otonglet.HV1735.testcase.entity.LazyChild;
import otonglet.HV1735.testcase.entity.Parent;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestCaseApplication.class})
public class TestCase {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private LazyChildDao lazyChildDao;
    @Autowired
    private ParentDao parentDao;

    @Before
    public void setUp() throws Exception {
        IDatabaseConnection connection = new DataSourceDatabaseTester(dataSource).getConnection();
        connection.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
        DatabaseOperation.CLEAN_INSERT.execute(connection,
                new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResource("dataset.xml")));
        connection.close();
    }

    /**
     * There are two ways to make the test pass
     * 1° Uncomment the first line "lazyChildDao.getLazyChildWithLazilyInitializedParent(3L)" that will lazily load the "parent" entity.
     * 2° Add getters annotated with @Valid for the embeddedChild, eagerChildren and lazyChildren fields of the "parent" entity.
     */
    @Test
    @Transactional
    public void lazyLoadThenValidate() {
        LazyChild lazyChild = lazyChildDao.getLazyChildWithLazilyInitializedParent(3L);

        Parent parent = parentDao.get(1L); // retrieves parent entity proxy from Hibernate cache, which was loaded at the previous line.

        ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .traversableResolver(new AlwaysYesTraversableResolver())
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Parent>> violations = validator.validate(parent); // should return 4 violations.

        assertThat(violations.stream().map(ConstraintViolation::getPropertyPath).map(Object::toString).collect(Collectors.toList()))
                .containsAll(Arrays.asList(
                        "field",
                        "embeddedChild.field",
                        "eagerChildren[0].field",
                        "lazyChildren[0].field"
                ));
    }
}

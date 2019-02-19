package ru.alvisid.pacs.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.Employee;
import testdata.AbstractTestData;
import testdata.EmployeeTestData;

/**
 * Employee's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class EmployeeServiceTest extends AbstractServiceTest<Employee, EmployeeService> {
    /**
     * Constructs new <em>EmployeeServiceTest</em> object.
     */
    public EmployeeServiceTest() {
        super(new EmployeeTestData());
    }

    /**
     * Sets the {@code EmployeeService} to the superclass's field.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(EmployeeService service) {
        this.service = service;
    }
}

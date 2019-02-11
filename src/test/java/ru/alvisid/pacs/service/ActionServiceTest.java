package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.Action;
import testdata.ActionTestData;

/**
 * Action's specific tests.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see AbstractServiceTest
 */
public class ActionServiceTest extends AbstractServiceTest<Action, ActionService> {
    /**
     * Constructs new <em>ActionServiceTest</em> object.
     */
    public ActionServiceTest() {
        super(new ActionTestData());
    }

    /**
     * Sets the {@code ActionService} to the superclass.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(ActionService service) {
        this.service = service;
    }

    @Test
    public void createWithEmpIdAndPointActId() {
}

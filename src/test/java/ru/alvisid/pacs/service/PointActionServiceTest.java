package ru.alvisid.pacs.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alvisid.pacs.model.PointAction;
import testdata.AbstractTestData;
import testdata.PointActionTestData;

public class PointActionServiceTest extends AbstractServiceTest<PointAction, PointActionService> {
    /**
     * Constructs new <em>PointActionServiceTest</em> object.
     */
    public PointActionServiceTest() {
        super(new PointActionTestData());
    }

    /**
     * Sets the {@code PointActionService} to the superclass.
     *
     * @param service the specified Service.
     */
    @Override
    @Autowired
    public void setService(PointActionService service) {
        this.service = service;
    }

    @Test
    public void createWithCtrlPointId() {
        PointAction expectedPointAction = testData.getNew();
        int expectedCtrlPointId = expectedPointAction.getControlPoint().getId();
    }
}

package testdata;

import ru.alvisid.pacs.model.Position;

public class PositionTestData extends AbstractTestData <Position> {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final Position
            POSITION_1 = new Position(1, "Директор", "Глава предприятия."),
            POSITION_2 = new Position(2, "Начальник отдела кадров", "Руководит отделом кадров."),
            POSITION_3 = new Position(3, "Главный бухгалтер", "Руководит бухгалтерией."),
            POSITION_4 = new Position(4, "Секретарь", "Делопроизводство."),
            POSITION_5 = new Position(5, "Бухгалтер", "Сотрудник бухгалтерии."),
            POSITION_6 = new Position(6, "Администратор", "Администрация базы данных.");

    /**
     * New {@code Position} with id-null.
     */
    public static final Position NEW_POSITION = new Position("Начальник производства", "Руководит производством.");

    /**
     * POSITION_1 with an updated data.
     *
     * @see PositionTestData#POSITION_1
     */
    public static final Position UPDATED_POSITION_1 = new Position(1, "Президент", "Президент фирмы.");

    /**
     * Returns a new {@code Position} from the test data.
     *
     * @return the new {@code Position} from the test data.
     */
    @Override
    public Position getNew() {
        return new Position(NEW_POSITION);
    }

    /**
     * Returns an updated {@code Position} from the test data.
     *
     * @return the updated {@code Position} from the test data.
     */
    @Override
    public Position getUpdated() {
        return new Position(UPDATED_POSITION_1);
    }

    /**
     * Returns an {@code Position} that is equals entity which will be get from DB.
     *
     * @return the {@code Position} that is equals entity which will be get from DB.
     */
    @Override
    public Position getGotten() {
        return new Position(POSITION_3);
    }

    /**
     * Returns an {@code Position}'s id for delete.
     *
     * @return the {@code Position}'s id for delete.
     */
    @Override
    public int getDeletedId() {
        return POSITION_3.getId();
    }

    /**
     * Returns the all {@code Position} from the test data with the specified created object.
     *
     * @param expectedCreatedObj the specified created {@code Position}.
     * @return the all {@code Position} from test data with the specified created {@code Position}.
     */
    @Override
    public Position[] getCreatedArray(Position expectedCreatedObj) {
        return new Position[]{
                POSITION_6,
                POSITION_5,
                POSITION_3,
                POSITION_1,
                POSITION_2,
                expectedCreatedObj,
                POSITION_4};
    }

    /**
     * Returns the all {@code Position} from the test data without the deleted {@code Position}.
     *
     * @return the all {@code Position} from the test data without the deleted {@code Position}.
     */
    @Override
    public Position[] getDeletedArray() {
        return new Position[]{
                POSITION_6,
                POSITION_5,
                POSITION_1,
                POSITION_2,
                POSITION_4};
    }

    /**
     * Returns the all {@code Position} from the test data.
     *
     * @return the all {@code Position} from the test data.
     */
    @Override
    public Position[] getAllArray() {
        return new Position[]{
                POSITION_6,
                POSITION_5,
                POSITION_3,
                POSITION_1,
                POSITION_2,
                POSITION_4};
    }

    /**
     * Constructs new {@code PositionTestData} and sets
     * {@code IGNORING_FIELDS} field
     * in the superclass.
     * There are no ignoring fields in the class.
     *
     * @see AbstractTestData#IGNORING_FIELDS
     */
    public PositionTestData() {
        super();
    }
}

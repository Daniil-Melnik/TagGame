package testing.GameBackend.Implementations.Logic;

import testing.Interfaces.Logic;

import java.util.*;

/**
 * Реализация игровой логики для головоломки "Пятнашки".
 * <p>
 * Содержит правила выполнения ходов, проверку победы, перемешивание поля
 * и валидацию состояния поля.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Logic
 */
public class SimpleLogic implements Logic {
    /** Эталонное состояние поля, соответствующее победе */
    private static final List<String> winField = List.of("1", "2", "3", "4", "5", "6", "7", "8", "*");

    /**
     * Отображение правил ходов.
     * <p>
     * Ключ - индекс пустой клетки, значение - множество индексов соседних клеток,
     * которые могут быть перемещены на место пустоты.
     * </p>
     */
    private static final HashMap<Integer, HashSet<String>> enabledPositions = new HashMap<>();

    /**
     * Конструктор, инициализирующий правила ходов.
     * <p>
     * Заполняет отображение enabledPositions правилами перемещения
     * для игрового поля размером 3x3.
     * </p>
     */
    public SimpleLogic() {
        enabledPositions.put(0, new HashSet<>(List.of("1", "3")));
        enabledPositions.put(1, new HashSet<>(List.of("0", "2", "4")));
        enabledPositions.put(2, new HashSet<>(List.of("1", "5")));
        enabledPositions.put(3, new HashSet<>(List.of("0", "4", "6")));
        enabledPositions.put(4, new HashSet<>(List.of("1", "3", "5", "7")));
        enabledPositions.put(5, new HashSet<>(List.of("8", "4", "2")));
        enabledPositions.put(6, new HashSet<>(List.of("3", "7")));
        enabledPositions.put(7, new HashSet<>(List.of("6", "4", "8")));
        enabledPositions.put(8, new HashSet<>(List.of("7", "5")));
    }

    /**
     * Выполняет ход, перемещая указанную клетку на место пустоты.
     *
     * @param btnPos       индекс нажатой клетки
     * @param val          значение нажатой клетки (цифра)
     * @param zeroPosition текущий индекс пустой клетки
     * @param field        текущее состояние игрового поля
     * @return обновленное состояние поля после хода (если ход возможен)
     */
    @Override
    public ArrayList<String> move(int btnPos, String val, int zeroPosition, ArrayList<String> field) {
        if (enabledPositions.get(zeroPosition).contains(btnPos + "")) {
            field.set(zeroPosition, val);
            field.set(btnPos, "*");
        }
        return field;
    }

    /**
     * Создает новое случайное состояние игрового поля.
     *
     * @return случайно перемешанное поле
     */
    @Override
    public List<String> shuffleField() {
        List<String> field = new ArrayList<>(List.of("*", "1", "2", "3", "4", "5", "6", "7", "8"));
        Collections.shuffle(field);
        return field;
    }

    /**
     * Проверяет, является ли текущее состояние поля выигрышным.
     *
     * @param field состояние игрового поля для проверки
     * @return true, если поле соответствует эталону победы, иначе false
     */
    @Override
    public boolean isWin(List<String> field) {
        return field.equals(winField);
    }

    /**
     * Проверяет корректность состояния поля.
     * <p>
     * Используется для защиты от чтения некорректных файлов.
     * Проверяет, что поле содержит ровно по одному экземпляру каждого элемента
     * из набора {*, 1, 2, 3, 4, 5, 6, 7, 8}.
     * </p>
     *
     * @param testField состояние поля для проверки
     * @return true, если поле содержит корректный набор элементов, иначе false
     */
    @Override
    public boolean isCorrectField(List<String> testField) {
        List<String> correctSortedField = List.of("*", "1", "2", "3", "4", "5", "6", "7", "8");
        Collections.sort(testField);
        return testField.equals(correctSortedField);
    }
}
package testing.Interfaces;

import testing.GameBackend.Implementations.Logic.SimpleLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Интерфейс блока игровой логики для головоломки "Пятнашки".
 * <p>
 * Содержит бизнес-логику игры: правила ходов, проверку победы,
 * перемешивание поля и валидацию состояния.
 * </p>
 * <p>
 * Данный интерфейс служит мостом между логикой и GUI через контроллер.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see SimpleLogic
 */
public interface Logic {

    /**
     * Создает новое случайное состояние игрового поля.
     *
     * @return список строк, представляющий перемешанное поле (9 элементов)
     */
    List<String> shuffleField();

    /**
     * Проверяет корректность состояния поля.
     * <p>
     * Используется для защиты от чтения некорректных файлов. Проверяет,
     * что поле содержит ровно по одному экземпляру каждого элемента
     * из набора {*, 1, 2, 3, 4, 5, 6, 7, 8}.
     * </p>
     *
     * @param testField состояние поля для проверки
     * @return true, если поле содержит корректный набор элементов, иначе false
     */
    boolean isCorrectField(List<String> testField);

    /**
     * Проверяет, является ли текущее состояние поля выигрышным.
     *
     * @param field состояние игрового поля для проверки
     * @return true, если поле соответствует эталону победы, иначе false
     */
    boolean isWin(List<String> field);

    /**
     * Выполняет ход, перемещая указанную клетку на место пустоты.
     *
     * @param btnPos       индекс нажатой клетки (0-8)
     * @param val          значение нажатой клетки (цифра 1-8)
     * @param zeroPosition текущий индекс пустой клетки (*)
     * @param field        текущее состояние игрового поля
     * @return обновленное состояние поля после выполнения хода
     */
    ArrayList<String> move(int btnPos, String val, int zeroPosition, ArrayList<String> field);
}
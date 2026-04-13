package testing.Interfaces;

import testing.GameBackend.DTO.InDTO;
import testing.GameFrontend.MainFrame;

/**
 * Интерфейс контроллера для интеграции в архитектуру MVC.
 * <p>
 * Координирует взаимодействие между моделью и представлением,
 * обрабатывая команды пользователя и обновляя состояние модели.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Holder
 * @see MainFrame
 */
public interface Controller {

    /**
     * Выполняет ход в игре.
     *
     * @param inDTO DTO с параметрами хода (позиция и значение кнопки)
     */
    void makeMove(InDTO inDTO);

    /**
     * Создает новую игру со случайным расположением костяшек.
     */
    void createNewShuffleGame();

    /**
     * Создает новую игру на основе состояния, сохраненного в файле.
     *
     * @param inDTO DTO с путем к файлу
     */
    void createNewFileGame(InDTO inDTO);

    /**
     * Сохраняет текущее состояние игры в файл.
     *
     * @param inDTO DTO с путем к файлу
     */
    void saveGameToFile(InDTO inDTO);
}
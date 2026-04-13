package testing.GameBackend.Implementations.Command;

import lombok.AllArgsConstructor;
import testing.Interfaces.Command;
import testing.Interfaces.Controller;

/**
 * Команда для перемешивания игрового поля.
 * <p>
 * Реализует паттерн Command для инкапсуляции запроса на создание
 * новой случайной конфигурации игрового поля.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Command
 * @see Controller
 */
@AllArgsConstructor
public class ShuffleCommand implements Command {
    /** Контроллер, выполняющий операцию перемешивания */
    private Controller controller = null;

    /**
     * Выполняет команду перемешивания поля.
     * <p>
     * Вызывает соответствующий метод контроллера для создания новой
     * случайной игры.
     * </p>
     */
    @Override
    public void execute() {
        controller.createNewShuffleGame();
    }
}
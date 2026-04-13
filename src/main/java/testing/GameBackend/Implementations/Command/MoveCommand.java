package testing.GameBackend.Implementations.Command;

import lombok.AllArgsConstructor;
import testing.GameBackend.InDTOFactory;
import testing.Interfaces.Command;
import testing.Interfaces.Controller;

/**
 * Команда для выполнения хода в игре.
 * <p>
 * Реализует паттерн Command для инкапсуляции запроса на перемещение
 * игровой костяшки.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Command
 * @see Controller
 */
@AllArgsConstructor
public class MoveCommand implements Command {
    /** Контроллер, выполняющий операцию хода */
    private Controller controller = null;

    /** Значение кнопки (цифра), которая была нажата */
    private String value = "*";

    /** Позиция нажатой кнопки в массиве поля (0-8) */
    private int position = 0;

    /**
     * Выполняет команду хода.
     * <p>
     * Создает InDTO с позицией и значением кнопки и передает его контроллеру
     * для выполнения хода.
     * </p>
     */
    @Override
    public void execute() {
        controller.makeMove(InDTOFactory.createInitMoveInDTO(position, value));
    }
}
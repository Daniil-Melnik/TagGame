package testing.GameBackend.Implementations.Command;

import lombok.AllArgsConstructor;
import testing.GameBackend.InDTOFactory;
import testing.Interfaces.Command;
import testing.Interfaces.Controller;

/**
 * Команда для сохранения текущего состояния игры в файл.
 * <p>
 * Реализует паттерн Command для инкапсуляции запроса на сохранение
 * состояния игры в файловую систему.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Command
 * @see Controller
 */
@AllArgsConstructor
public class SaveCommand implements Command {
    /** Контроллер, выполняющий операцию сохранения */
    private Controller controller = null;

    /** Путь к файлу, в который будет выполнено сохранение */
    private String path;

    /**
     * Выполняет команду сохранения игры.
     * <p>
     * Создает InDTO с путем к файлу и передает его контроллеру для
     * выполнения операции сохранения.
     * </p>
     */
    @Override
    public void execute() {
        controller.saveGameToFile(InDTOFactory.createFileInDTO(path));
    }
}
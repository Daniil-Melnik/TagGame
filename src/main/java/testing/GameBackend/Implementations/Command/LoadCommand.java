package testing.GameBackend.Implementations.Command;

import lombok.AllArgsConstructor;
import testing.GameBackend.InDTOFactory;
import testing.Interfaces.Command;
import testing.Interfaces.Controller;

/**
 * Команда для загрузки сохраненной игры из файла.
 * <p>
 * Реализует паттерн Command для инкапсуляции запроса на загрузку состояния
 * игры из файловой системы.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Command
 * @see Controller
 */
@AllArgsConstructor
public class LoadCommand implements Command {
    /** Контроллер, выполняющий операцию загрузки */
    private Controller controller = null;

    /** Путь к файлу, из которого будет выполнена загрузка */
    private String path;

    /**
     * Выполняет команду загрузки игры.
     * <p>
     * Создает InDTO с путем к файлу и передает его контроллеру для
     * выполнения операции загрузки.
     * </p>
     */
    @Override
    public void execute() {
        controller.createNewFileGame(InDTOFactory.createFileInDTO(path));
    }
}
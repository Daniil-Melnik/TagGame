package testing.GameBackend.Implementations.Command;

import lombok.AllArgsConstructor;
import testing.GameBackend.InDTOFactory;
import testing.Interfaces.Command;
import testing.Interfaces.Controller;

// команда сохранения игры
@AllArgsConstructor
public class SaveCommand implements Command {
    private Controller controller = null; // контроллер
    private String path; // путь сохранения
    @Override
    public void execute() {
        controller.saveGameToFile(InDTOFactory.createFileInDTO(path));
    }
}

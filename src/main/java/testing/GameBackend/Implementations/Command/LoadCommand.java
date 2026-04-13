package testing.GameBackend.Implementations.Command;

import lombok.AllArgsConstructor;
import testing.GameBackend.InDTOFactory;
import testing.Interfaces.Command;
import testing.Interfaces.Controller;

@AllArgsConstructor
public class LoadCommand implements Command {
    private Controller controller = null; // экземпляр контроллера для выполнения операции
    private String path; // путь загрузки
    @Override
    public void execute() {
        controller.createNewFileGame(InDTOFactory.createFileInDTO(path));
    } // выполнение команды
}

package testing.GameBackend.Implementations.Command;

import lombok.AllArgsConstructor;
import testing.GameBackend.InDTOFactory;
import testing.Interfaces.Command;
import testing.Interfaces.Controller;

@AllArgsConstructor
public class MoveCommand implements Command { // команда выполнения хода
    private Controller controller = null; // экземпляр контроллера
    private String value = "*"; // значение кнопки перехода
    private int position = 0; // позиция кнопки хода

    @Override
    public void execute() {
        controller.makeMove(InDTOFactory.createInitMoveInDTO(position, value));
    }
}

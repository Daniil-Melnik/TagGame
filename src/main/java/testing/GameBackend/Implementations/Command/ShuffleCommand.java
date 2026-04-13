package testing.GameBackend.Implementations.Command;

import lombok.AllArgsConstructor;
import testing.Interfaces.Command;
import testing.Interfaces.Controller;

// класс описываюхий команду перемешивания поля
@AllArgsConstructor
public class ShuffleCommand implements Command {
    private Controller controller = null; // контроллер для вызова метода

    @Override
    public void execute() {
        controller.createNewShuffleGame();
    }
}

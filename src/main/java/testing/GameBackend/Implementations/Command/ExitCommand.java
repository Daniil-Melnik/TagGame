package testing.GameBackend.Implementations.Command;

import testing.Interfaces.Command;

// класс, описывающий команду выхода из игры
public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);;
    }
}

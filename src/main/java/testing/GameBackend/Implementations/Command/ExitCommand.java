package testing.GameBackend.Implementations.Command;

import testing.Interfaces.Command;

/**
 * Команда для выхода из приложения.
 * <p>
 * Реализует паттерн Command для инкапсуляции запроса на завершение работы
 * программы.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Command
 */
public class ExitCommand implements Command {
    /**
     * Выполняет команду выхода.
     * <p>
     * Завершает работу Java Virtual Machine с кодом 0 (нормальное завершение).
     * </p>
     */
    @Override
    public void execute() {
        System.exit(0);
    }
}
package testing.GameBackend.Implementations.Command;

import lombok.AllArgsConstructor;
import testing.Interfaces.Command;

import javax.swing.*;

/**
 * Команда для отображения диалогового окна "О программе".
 * <p>
 * Реализует паттерн Command для инкапсуляции запроса на показ информации
 * о приложении.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Command
 */
@AllArgsConstructor
public class AboutCommand implements Command {
    /** Родительское окно, относительно которого будет отображаться диалог */
    private JFrame mainFrame = null;

    /**
     * Выполняет команду показа окна "О программе".
     * <p>
     * Отображает модальное диалоговое окно с информацией об игре.
     * </p>
     */
    @Override
    public void execute() {
        JOptionPane.showMessageDialog(mainFrame, "Игра Пятнашка на 9 клеток");
    }
}
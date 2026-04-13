package testing.GameBackend.Implementations.Command;

import lombok.AllArgsConstructor;
import testing.Interfaces.Command;

import javax.swing.*;

// команда для показа окна "О программе"
@AllArgsConstructor
public class AboutCommand implements Command {
    private JFrame mainFrame = null; // окно, в которое отображается диалог

    @Override
    public void execute() { // метод вызова команды
        JOptionPane.showMessageDialog(mainFrame, "Игра Пятнашка на 9 клеток");;
    }
}

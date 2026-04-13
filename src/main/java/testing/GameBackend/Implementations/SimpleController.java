package testing.GameBackend.Implementations;

import lombok.AllArgsConstructor;
import lombok.Setter;
import testing.GameBackend.DTO.InDTO;

import testing.GameBackend.DTO.SaveDTO;
import testing.GameBackend.Implementations.Logic.SimpleLogic;
import testing.GameBackend.Implementations.Strategy.JsonStrategy;
import testing.GameBackend.Implementations.Strategy.TextStrategy;
import testing.GameBackend.Implementations.Strategy.XmlStrategy;
import testing.Interfaces.*;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// класс-реализация контроллера приложения

public class SimpleController implements Controller {
    private final HashMap<String, FileStrategy> strategies = // стратегии работы с айлами трёх типов
            new HashMap<>(Map.of(
                    ".txt", new TextStrategy(),
                    ".xml", new XmlStrategy(),
                    ".json", new JsonStrategy()));

    private Logic logic = null; // экземпляр логики
    private Holder holder = null; // экземпляр модели
    private FileStrategy strategy; // установленная стратегия работы с файлами
    @Setter
    private State state; // усстановленное состояние: выигрыш/игра

    public SimpleController(Holder holder){ // конструктор
        logic = new SimpleLogic(); // на месте
        this.holder = holder; // извне
        this.strategy = new TextStrategy(); // на месте
        state = new WinState(); // на месте
    }

    @Override
    public void makeMove(InDTO inDTO) { // метод выполнения хода
        state.makeMove(inDTO); // делегирования обязанностей выполнения хода текущему состоянию (игра - ходим, победа - не ходим)
        if (holder.isWin()) setState(new WinState()); // управление состоянием - установка в выигрышное
    }

    @Override
    public void createNewShuffleGame() {
        ArrayList<String> newField = (ArrayList<String>) logic.shuffleField(); // создание случайного поля
        holder.updateHolder(newField, -1, false); // обновление модели в соответствии с новым полем
        setState(new GameState(holder, logic)); // управление состоянием - сброс в игровое
    }

    @Override
    public void createNewFileGame(InDTO inDTO) { // метод загрузки игры из файла
        String path = inDTO.getPath(); // получение пути
        strategy = strategies.get(path.substring(path.lastIndexOf('.'))); // определение стратегии работы с файлом
                                                                              // по его расширению
        ArrayList<String> newField = null;
        try {
            SaveDTO saveDTO = strategy.load(path); // выполнение загрузки по стратегии
            newField = saveDTO.getField(); // получение загруженного поля
            if (logic.isCorrectField(newField)) holder.updateHolder(saveDTO.getField(), saveDTO.getZeroPosition(), saveDTO.getWin());
                // если поле корректное - обновление модели
            else JOptionPane.showMessageDialog(null, "Некорректный файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
            setState(new GameState(holder, logic)); // управление состоянием - сброс в игровое

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Файл исчез!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void saveGameToFile(InDTO inDTO){ // метод сохранения игры в файл
        String path = inDTO.getPath(); // получение пути
        strategy = strategies.get(path.substring(path.lastIndexOf('.'))); // определение стратегии
        ArrayList<String> field = (ArrayList<String>) holder.getField(); // получение поля из модели
        try {
            strategy.save(new SaveDTO(holder.isWin(), field, holder.getZeroPosition()), path); // сохранение по стратегии
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка сохранения игры!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    @AllArgsConstructor
    private static class GameState implements State{ // внутренний класс, описывающий состояние "Игра идёт"
        private Holder holder; // поля для "подмены" контроллера
        private Logic logic;

        @Override
        public void makeMove(InDTO inDTO) { // метод выполнения хода (ира идёт => можно ходить)
            int btnPos = inDTO.getPosition();
            String value = inDTO.getValue();

            ArrayList<String> field = holder.getField();
            int zeroPosition = holder.getZeroPosition();

            ArrayList<String> newField = logic.move(btnPos, value, zeroPosition, field);
            holder.updateHolder(newField, -1, logic.isWin(holder.getField())); // обновление поля
        }
    }

    private static class WinState implements State{ // класс описывающий состояние "Выигрыш" - ходить нельзя

        @Override
        public void makeMove(InDTO inDTO) {
            JOptionPane.showMessageDialog(null, "Игра уже выиграна!");
        }
    }
}

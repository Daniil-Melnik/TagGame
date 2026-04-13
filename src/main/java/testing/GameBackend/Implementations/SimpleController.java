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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация контроллера приложения (паттерн MVC).
 * <p>
 * Координирует взаимодействие между моделью ({@link Holder}) и представлением.
 * Реализует паттерны:
 * <ul>
 *     <li>State - управление состояниями игры (игра/победа)</li>
 *     <li>Strategy - выбор стратегии сохранения/загрузки файлов</li>
 * </ul>
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Controller
 * @see State
 */
public class SimpleController implements Controller {
    /**
     * Карта стратегий работы с файлами.
     * Ключ - расширение файла (.txt, .xml, .json), значение - соответствующая стратегия.
     */
    private final HashMap<String, FileStrategy> strategies = new HashMap<>(Map.of(
            ".txt", new TextStrategy(),
            ".xml", new XmlStrategy(),
            ".json", new JsonStrategy()));

    /** Экземпляр игровой логики */
    private Logic logic = null;

    /** Экземпляр модели (хранилище состояния игры) */
    private Holder holder = null;

    /** Текущая стратегия работы с файлами */
    private FileStrategy strategy;

    /** Текущее состояние игры (игра идет / победа) */
    @Setter
    private State state;

    /**
     * Конструктор контроллера.
     *
     * @param holder экземпляр модели, передаваемый извне
     */
    public SimpleController(Holder holder) {
        logic = new SimpleLogic();
        this.holder = holder;
        this.strategy = new TextStrategy();
        state = new WinState();
    }

    /**
     * Выполняет ход в игре.
     * <p>
     * Делегирует выполнение текущему состоянию. После выполнения хода проверяет
     * флаг победы и при необходимости переключает состояние на WinState.
     * </p>
     *
     * @param inDTO DTO с параметрами хода (позиция и значение кнопки)
     */
    @Override
    public void makeMove(InDTO inDTO) {
        state.makeMove(inDTO);
        if (holder.isWin()) setState(new WinState());
    }

    /**
     * Создает новую игру со случайным расположением клеток.
     * <p>
     * Генерирует случайное поле через логику, обновляет модель и
     * переключает состояние в режим игры.
     * </p>
     */
    @Override
    public void createNewShuffleGame() {
        ArrayList<String> newField = (ArrayList<String>) logic.shuffleField();
        holder.updateHolder(newField, -1, false);
        setState(new GameState(holder, logic));
    }

    /**
     * Загружает игру из файла.
     * <p>
     * Автоматически определяет стратегию загрузки по расширению файла.
     * Проверяет корректность загруженного поля перед обновлением модели.
     * </p>
     *
     * @param inDTO DTO с путем к файлу
     */
    @Override
    public void createNewFileGame(InDTO inDTO) {
        String path = inDTO.getPath();
        strategy = strategies.get(path.substring(path.lastIndexOf('.')));

        try {
            SaveDTO saveDTO = strategy.load(path);
            ArrayList<String> newField = saveDTO.getField();
            if (logic.isCorrectField(newField)) {
                holder.updateHolder(saveDTO.getField(), saveDTO.getZeroPosition(), saveDTO.getWin());
            } else {
                JOptionPane.showMessageDialog(null, "Некорректный файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            setState(new GameState(holder, logic));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Файл исчез!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Сохраняет текущее состояние игры в файл.
     * <p>
     * Автоматически определяет стратегию сохранения по расширению файла.
     * </p>
     *
     * @param inDTO DTO с путем к файлу
     */
    @Override
    public void saveGameToFile(InDTO inDTO) {
        String path = inDTO.getPath();
        strategy = strategies.get(path.substring(path.lastIndexOf('.')));
        ArrayList<String> field = (ArrayList<String>) holder.getField();

        try {
            strategy.save(new SaveDTO(holder.isWin(), field, holder.getZeroPosition()), path);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка сохранения игры!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Внутренний класс, описывающий состояние "Игра идет".
     * <p>
     * В этом состоянии разрешено выполнение ходов.
     * Реализует паттерн State.
     * </p>
     */
    @AllArgsConstructor
    private static class GameState implements State {
        /** Модель игры */
        private Holder holder;

        /** Логика игры */
        private Logic logic;

        /**
         * Выполняет ход в состоянии активной игры.
         *
         * @param inDTO DTO с параметрами хода
         */
        @Override
        public void makeMove(InDTO inDTO) {
            int btnPos = inDTO.getPosition();
            String value = inDTO.getValue();

            ArrayList<String> field = holder.getField();
            int zeroPosition = holder.getZeroPosition();

            ArrayList<String> newField = logic.move(btnPos, value, zeroPosition, field);
            holder.updateHolder(newField, -1, logic.isWin(holder.getField())); // обновление поля
        }
    }

    /**
     * Внутренний класс, описывающий состояние "Выигрыш".
     * <p>
     * В этом состоянии запрещено выполнение ходов.
     * Реализует паттерн State.
     * </p>
     */
    private static class WinState implements State{ // класс описывающий состояние "Выигрыш" - ходить нельзя

        @Override
        public void makeMove(InDTO inDTO) {
            JOptionPane.showMessageDialog(null, "Игра уже выиграна!");
        }
    }
}

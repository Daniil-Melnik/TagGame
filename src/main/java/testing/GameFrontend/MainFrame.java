package testing.GameFrontend;

import testing.GameBackend.Implementations.Command.*;
import testing.Interfaces.Controller;
import testing.Interfaces.Holder;
import testing.Interfaces.HolderObserver;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * Главное окно приложения (View в архитектуре MVC).
 * <p>
 * Реализует паттерн "Наблюдатель" через интерфейс {@link HolderObserver},
 * автоматически обновляя отображение при изменении модели.
 * </p>
 * <p>
 * Содержит:
 * <ul>
 *     <li>Игровую панель с кнопками-костяшками</li>
 *     <li>Главное меню для управления игрой</li>
 * </ul>
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see HolderObserver
 * @see Controller
 * @see Holder
 */
public class MainFrame extends JFrame implements HolderObserver {

    /** Высота главного окна в пикселях */
    private static final int FRAME_H = 415;

    /** Ширина главного окна в пикселях */
    private static final int FRAME_W = 360;

    /** Высота игровой панели в пикселях */
    private static final int GAME_PAN_H = 360;

    /** Ширина игровой панели в пикселях */
    private static final int GAME_PAN_W = 360;

    /** Игровая панель с кнопками */
    private static GamePanel gamePanel;

    /** Контроллер приложения */
    private static Controller controller;

    /** Модель игры */
    private static Holder holder;

    /**
     * Конструктор главного окна.
     *
     * @param c контроллер для управления игрой
     * @param h модель игры для отображения состояния
     */
    public MainFrame(Controller c, Holder h) {
        controller = c;
        holder = h;

        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_W, FRAME_H);
        setTitle("Пятнашки");
        setResizable(false);
        setIconImage(new ImageIcon(
                Objects.requireNonNull(this.getClass().getResource("/rubik.png"))
        ).getImage());

        gamePanel = new GamePanel(this);
        add(gamePanel, BorderLayout.SOUTH);
        setJMenuBar(new MainMenuBar(this, controller));
    }

    /**
     * Отображает диалоговое окно с сообщением о победе.
     */
    private void showWinMessage() {
        JOptionPane.showMessageDialog(this, "Выигрыш!");
    }

    /**
     * Обновляет представление при изменении модели (паттерн "Наблюдатель").
     *
     * @param holder обновленная модель игры
     */
    @Override
    public void update(Holder holder) {
        gamePanel.rePaintButtons((ArrayList<String>) holder.getField(), holder.isWin());
        if (holder.isWin()) {
            showWinMessage();
        }
    }

    /**
     * Игровая панель, содержащая кнопки-костяшки и пустое место.
     * <p>
     * Отображает игровое поле 3x3 в виде сетки.
     * </p>
     */
    private static class GamePanel extends JPanel {

        /** Отображение "цифра -> цвет" для индивидуальной окраски костяшек */
        public static Map<String, Color> colors = Map.of(
                "1", new Color(85, 85, 255),
                "2", new Color(254, 1, 154),
                "3", new Color(57, 255, 20),
                "4", new Color(188, 19, 254),
                "5", new Color(4, 217, 255),
                "6", new Color(248, 0, 0),
                "7", new Color(204, 255, 0),
                "8", new Color(255, 164, 32)
        );

        /** Ссылка на главное окно */
        private MainFrame mainFrame;

        /**
         * Конструктор игровой панели.
         *
         * @param mainFrame ссылка на главное окно
         */
        public GamePanel(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
            setLayout(new GridLayout(3, 3));
        }

        /**
         * Перерисовывает игровое поле на основе текущего состояния модели.
         *
         * @param field текущее состояние игрового поля
         * @param isWin флаг победы (при true кнопки блокируются)
         */
        public void rePaintButtons(ArrayList<String> field, boolean isWin) {
            int i = 0;

            removeAll();
            revalidate();
            repaint();

            GameButton btn;

            for (String s : field) {
                if (!s.equals("*")) {
                    btn = new GameButton(i, s, controller, mainFrame);
                    btn.setFont(new Font("Arial", Font.BOLD, 64));
                    btn.setBackground(colors.get(s));
                    btn.setEnabled(!isWin);
                    add(btn);
                } else {
                    add(new JPanel());
                }
                i++;
            }
            revalidate();
            repaint();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(GAME_PAN_H, GAME_PAN_W);
        }
    }

    /**
     * Игровая кнопка, представляющая костяшку с цифрой.
     * <p>
     * При нажатии создает и выполняет команду {@link MoveCommand}.
     * </p>
     */
    private static class GameButton extends JButton {

        /** Позиция кнопки в массиве поля (0-8) */
        private int pos;

        /**
         * Конструктор игровой кнопки.
         *
         * @param p          позиция кнопки на поле
         * @param val        значение кнопки (цифра)
         * @param controller контроллер для выполнения команды
         * @param mainFrame  главное окно (используется для диалогов)
         */
        public GameButton(int p, String val, Controller controller, MainFrame mainFrame) {
            super(val);
            pos = p;
            addActionListener((e) -> {
                new MoveCommand(controller, this.getVal(), this.getIndex()).execute();
            });
        }

        /**
         * Возвращает позицию кнопки в массиве поля.
         *
         * @return индекс кнопки (0-8)
         */
        public int getIndex() {
            return pos;
        }

        /**
         * Возвращает значение кнопки.
         *
         * @return цифровое значение кнопки (1-8)
         */
        public String getVal() {
            return super.getText();
        }
    }

    /**
     * Главное меню приложения.
     * <p>
     * Содержит пункты для управления игрой:
     * <ul>
     *     <li>Файл: Выход, О программе</li>
     *     <li>Игра: Новая случайная, Загрузить, Сохранить</li>
     * </ul>
     * </p>
     */
    private static class MainMenuBar extends JMenuBar {

        /**
         * Конструктор главного меню.
         *
         * @param mainFrame  главное окно (для диалогов)
         * @param controller контроллер для выполнения команд
         */
        public MainMenuBar(MainFrame mainFrame, Controller controller) {

            JMenu mainMenu = new JMenu("Файл (F)");
            JMenu gameMenu = new JMenu("Игра (G)");
            JMenu newGameMenu = new JMenu("Новая (N)");

            JMenuItem exitItem = new JMenuItem("Выход (E)", 'E');
            JMenuItem aboutItem = new JMenuItem("О программе (A)", 'A');
            JMenuItem newGameItem = new JMenuItem("Случайная (R)", 'R');
            JMenuItem saveGameItem = new JMenuItem("Сохранить (S)", 'S');
            JMenuItem loadGameItem = new JMenuItem("Загрузить (L)", 'L');

            gameMenu.setMnemonic(KeyEvent.VK_G);
            mainMenu.setMnemonic(KeyEvent.VK_F);
            newGameMenu.setMnemonic(KeyEvent.VK_N);

            newGameItem.addActionListener((e) -> new ShuffleCommand(controller).execute());
            exitItem.addActionListener((e) -> new ExitCommand().execute());
            aboutItem.addActionListener((e) -> new AboutCommand(mainFrame).execute());

            saveGameItem.addActionListener((e) -> {
                JFileChooser chooser = createFileChooser(JFileChooser.SAVE_DIALOG);

                if (chooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    FileFilter currentFilter = chooser.getFileFilter();
                    String extension = ((FileNameExtensionFilter) currentFilter).getExtensions()[0];
                    if (!filePath.endsWith(extension)) {
                        filePath += String.format(".%s", extension);
                    }
                    new SaveCommand(controller, filePath).execute();
                }
            });

            loadGameItem.addActionListener((e) -> {
                JFileChooser chooser = createFileChooser(JFileChooser.OPEN_DIALOG);

                if (chooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
                    new LoadCommand(controller, chooser.getSelectedFile().toString()).execute();
                }
            });

            mainMenu.add(exitItem);
            mainMenu.add(aboutItem);

            newGameMenu.add(newGameItem);
            newGameMenu.add(loadGameItem);

            gameMenu.add(newGameMenu);
            gameMenu.addSeparator();
            gameMenu.add(saveGameItem);

            add(mainMenu);
            add(gameMenu);
        }

        /**
         * Создает настроенный диалог выбора файла.
         *
         * @param type тип диалога (JFileChooser.SAVE_DIALOG или JFileChooser.OPEN_DIALOG)
         * @return настроенный экземпляр JFileChooser
         */
        private JFileChooser createFileChooser(int type) {
            Date date = new Date();
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle(type == JFileChooser.SAVE_DIALOG ? "Сохранить" : "Загрузить");

            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("XML files (*.xml)", "xml"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON files (*.json)", "json"));

            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setMultiSelectionEnabled(false);
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setFileFilter(chooser.getChoosableFileFilters()[0]);

            if (type == JFileChooser.SAVE_DIALOG) {
                chooser.setSelectedFile(new File(String.format(
                        "game_%tY%tm%td_%tH%tM%tS", date, date, date, date, date, date
                )));
            }

            return chooser;
        }
    }
}
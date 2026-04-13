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

// класс главного окна

public class MainFrame extends JFrame implements HolderObserver { // главное окно

    private static final int FRAME_H = 415; // константы размеров элементов интерфейса
    private static final int FRAME_W = 360;

    private static final int GAME_PAN_H = 360;
    private static final int GAME_PAN_W = 360;

    private static GamePanel gamePanel; // поле игровой панели с, непосредственно, кнопками
    private static Controller controller;
    private static Holder holder;

    public MainFrame(Controller c, Holder h) {
        controller = c; // представление представляет модели и взаимодействует с чней через контроллер
        holder = h;

        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_W, FRAME_H);
        setTitle("Пятнашки");
        setResizable(false);
        setIconImage(
                new ImageIcon(
                        Objects.requireNonNull(this.getClass().getResource("/rubik.png"))
                ).getImage());

        gamePanel = new GamePanel(this);

        add(gamePanel, BorderLayout.SOUTH);

        setJMenuBar(new MainMenuBar(this, controller)); // добавление строки меню
    }

    private void showWinMessage(){
        JOptionPane.showMessageDialog(this, "Выигрыш!");
    }

    @Override
    public void update(Holder holder) { // реализация обновления на основе наблюдения
        gamePanel.rePaintButtons((ArrayList<String>) holder.getField(), holder.isWin());
        if (holder.isWin()) showWinMessage();
    }


    private static class GamePanel extends JPanel { // класс описания интерфейса - игровой панели с кнопками
        public static Map<String, Color> colors = Map.of( // отображение "цифра - цвет" для индивидуальной
                "1", new Color(85, 85, 255),       // окраски костяшек
                "2", new Color(254, 1, 154),
                "3", new Color(57, 255, 20),
                "4", new Color(188, 19, 254),
                "5", new Color(4, 217, 255),
                "6", new Color(248, 0, 0),
                "7", new Color(204, 255, 0),
                "8", new Color(255, 164, 32)
        );

        private MainFrame mainFrame;

        public GamePanel( MainFrame mainFrame) {
            this.mainFrame = mainFrame;
            setLayout(new GridLayout(3, 3)); // установка сеточной компоновки
        }

        public void rePaintButtons(ArrayList<String> field, boolean isWin) { // метод отрисовк игрового поля состоящего из 8-ми спец. кнопок
            int i = 0;                // и 1 пустого места

            removeAll(); // первичная очистка поля от всего (для повторной отрисовки)
            revalidate(); // обновление интерфейса
            repaint();

            GameButton btn;

            for (String s : field) { // перебор поля из экземпляра логики
                if (!s.equals("*")) {               // все что не "*" - создание кнопки с установкой её положения
                    btn = new GameButton(i, s, controller, mainFrame);
                    btn.setFont(new Font("Arial", Font.BOLD, 64));
                    btn.setBackground(colors.get(s)); // выбор цвета из отображения выше
                    btn.setEnabled(!isWin); // блокировка кнопок при выигрышеы
                    add(btn);
                } else { // если "*" - добавить пустое место в виде панели
                    add(new JPanel());
                }

                i++; // непрерывный на пустоту счётчик для установки координат в кнопке
            }
            revalidate(); // пересчет интерфейса
            repaint();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(GAME_PAN_H, GAME_PAN_W);
        }
    }

    private static class GameButton extends JButton { // игровая кнопка

        private int pos;

        public GameButton(int p, String val, Controller controller, MainFrame mainFrame) { // конструирование по тексту
            super(val);
            pos = p;
            addActionListener((e) -> { // при нажатии выполняется ход
                new MoveCommand(controller, this.getVal(), this.getIndex()).execute(); // ход по игровой логике через команду
            });
        }

        public int getIndex() { // получение номера в массиве поля
            return pos;
        }

        public String getVal() {
            return super.getText();
        } // получить тектовое значение - цифру

    }


    private static class MainMenuBar extends JMenuBar { // строка главного меню

        public MainMenuBar(MainFrame mainFrame, Controller controller) {

            JMenu mainMenu = new JMenu("Файл (F)");
            JMenu gameMenu = new JMenu("Игра (G)");

            JMenu newGameMenu = new JMenu("Новая (N)");

            JMenuItem exitItem = new JMenuItem("Выход (E)", 'E'); // конструирование пунктов меню
            JMenuItem aboutItem = new JMenuItem("О программе (A)", 'A');// с мнемониками

            JMenuItem newGameItem = new JMenuItem("Случайная (R)", 'R');
            JMenuItem saveGameItem = new JMenuItem("Сохранить (S)", 'S');
            JMenuItem loadGameItem = new JMenuItem("Загрузить (L)", 'L');

            gameMenu.setMnemonic(KeyEvent.VK_G); // присвоение мнемоник Alt + __ меню
            mainMenu.setMnemonic(KeyEvent.VK_F);
            newGameMenu.setMnemonic(KeyEvent.VK_N);

            newGameItem.addActionListener((e) -> { // новая случайная игра
                new ShuffleCommand(controller).execute();
            });

            exitItem.addActionListener((e) -> new ExitCommand().execute());
            aboutItem.addActionListener((e) -> new AboutCommand(mainFrame).execute());

            saveGameItem.addActionListener((e) -> { // сохранение игры
                JFileChooser chooser = createFileChooser(JFileChooser.SAVE_DIALOG); // применение диалога выбора файлов

                if (chooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();

                    String filePath = selectedFile.getAbsolutePath();
                    FileFilter currentFilter = chooser.getFileFilter();

                    String extension = ((FileNameExtensionFilter) currentFilter).getExtensions()[0];
                    if (!filePath.endsWith(extension)) filePath += String.format(".%s", extension);

                    new SaveCommand(controller, filePath).execute(); // сохранение через команду
                }
            });

            loadGameItem.addActionListener((e) -> { // загрузка поля из файла
                JFileChooser chooser = createFileChooser(JFileChooser.OPEN_DIALOG); // применение диалога выбора фйалов

                if (chooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
                    new LoadCommand(controller, chooser.getSelectedFile().toString()).execute(); // загрузка через команду
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

        private JFileChooser createFileChooser(int type){
            Date date = new Date();
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle(type == JFileChooser.SAVE_DIALOG ? "Сохранить" : "Загрузить");

            // добавление фильтров доступных расширений сохранения/загрузки: json, txt, xml
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("XML files (*.xml)", "xml"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON files (*.json)", "json"));
            // только фалы
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            // запрет выбора нескольких
            chooser.setMultiSelectionEnabled(false);
            chooser.setAcceptAllFileFilterUsed(false); // "все типы" - запрет

            chooser.setFileFilter(chooser.getChoosableFileFilters()[0]); // установка по умолчанию первого из всех фильров (в данном слуае - txt)
            if (type == JFileChooser.SAVE_DIALOG) chooser.setSelectedFile(new File(String.format("game_%tY%tm%td_%tH%tM%tS", date, date, date, date, date, date)));

            return chooser;
        }
    }
}

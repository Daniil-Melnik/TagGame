package testing;

import testing.GameBackend.Implementations.Holder.SimpleHolder;
import testing.GameBackend.Implementations.Holder.SimplePublisher;
import testing.GameBackend.Implementations.SimpleController;
import testing.Interfaces.Controller;
import testing.GameFrontend.MainFrame;
import testing.Interfaces.Holder;
import testing.Interfaces.HolderChangePublisher;

import java.awt.*;

/**
 * Главный класс игры "Пятнашки".
 * <p>
 * Точка входа в приложение. Отвечает за инициализацию всех компонентов
 * в архитектуре MVC (Model-View-Controller):
 * <ul>
 *     <li>Model - {@link Holder} (SimpleHolder)</li>
 *     <li>View - {@link MainFrame} (графический интерфейс)</li>
 *     <li>Controller - {@link Controller} (SimpleController)</li>
 * </ul>
 * </p>
 * <p>
 * Также настраивает паттерн "Наблюдатель", подписывая представление
 * на уведомления об изменениях модели.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 */
public class Main {

    /**
     * Точка входа в приложение.
     * <p>
     * Выполняет следующие шаги:
     * <ol>
     *     <li>Создает менеджер уведомлений (SimplePublisher)</li>
     *     <li>Создает модель игры (SimpleHolder)</li>
     *     <li>Создает контроллер (SimpleController)</li>
     *     <li>Создает главное окно (MainFrame)</li>
     *     <li>Подписывает главное окно на уведомления модели</li>
     *     <li>Запускает графический интерфейс в потоке обработки событий AWT</li>
     * </ol>
     * </p>
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String... args) {
        HolderChangePublisher publisher = new SimplePublisher();

        Holder holder = new SimpleHolder(publisher);
        Controller controller = new SimpleController(holder);
        MainFrame mainFrame = new MainFrame(controller, holder);

        publisher.subscribe(mainFrame);

        EventQueue.invokeLater(() -> {
            mainFrame.setVisible(true);
        });
    }
}
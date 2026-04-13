package testing;

import testing.GameBackend.Implementations.Holder.SimpleHolder;
import testing.GameBackend.Implementations.Holder.SimplePublisher;
import testing.GameBackend.Implementations.SimpleController;
import testing.Interfaces.Controller;
import testing.GameFrontend.MainFrame;
import testing.Interfaces.Holder;
import testing.Interfaces.HolderChangePublisher;

import java.awt.*;

// главный класс игры
public class Main {

    public static void main(String... args){
        HolderChangePublisher publisher = new SimplePublisher(); // менеджер субъекта наблюдения

        Holder holder = new SimpleHolder(publisher); // формирование тройки MVC
        Controller controller = new SimpleController(holder);
        MainFrame mainFrame = new MainFrame(controller, holder);

        publisher.subscribe(mainFrame); // подписка главного окна на наблюдение

        EventQueue.invokeLater(() -> {
            mainFrame.setVisible(true);
        });
    }
}

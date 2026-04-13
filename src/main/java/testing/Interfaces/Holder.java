package testing.Interfaces;

import java.util.ArrayList;

// интерфес модели игры для интеграции в MVC (субъект в реализации паттерна "Наблюдатель" во взаимодействии интерфейса и
// контроллера и преставления)
public interface Holder {
    ArrayList<String> getField();
    int getZeroPosition();
    boolean isWin();

    void updateHolder (ArrayList<String> newField, int newZeroPosition, boolean win); // обновление состояния контроллера
    Holder getHolder(); // метод для получения наблюдателем экземпляра представления
}

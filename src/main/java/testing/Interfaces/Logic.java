// временный мост между логикой и ГУИ, мост будет с контроллером
// потом будет перевод под хранилище поля и взаимодействие из команды ???

package testing.Interfaces;

import java.util.ArrayList;
import java.util.List;

// интерфес блока логики игры
public interface Logic {
    List<String> shuffleField(); // перемешать поле
    boolean isCorrectField(List<String> testField); // проверка на корректность поля
    boolean isWin(List<String> field); // проверка на статус выигрыша

    ArrayList<String> move(int btnPos, String val, int zeroPosition, ArrayList<String> field); // выполнение хода
}

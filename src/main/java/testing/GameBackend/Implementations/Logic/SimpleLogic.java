// временное решение привести к Singleton - так как пока хранятся данные о поле. Позже будет введён класс-хранилище (или это будет зашито в команду)

package testing.GameBackend.Implementations.Logic;

import testing.Interfaces.Logic;

import java.util.*;

public class SimpleLogic implements Logic {

    private static final List<String> winField = List.of("1", "2", "3", "4", "5", "6", "7", "8", "*"); // выигрышное состояние поля

    private static final HashMap<Integer, HashSet<String>> enabledPositions = new HashMap<>(); // отображение правил ходов
    // индекс пустого места - множество разрешенных соседних индексов
    // для занятия пустоты. Индексы в массиве поля.

    public SimpleLogic(){ // заполнение отображения правил движения кнопок на пустое место
        enabledPositions.put(0, new HashSet<>(List.of("1", "3")));
        enabledPositions.put(1, new HashSet<>(List.of("0", "2", "4")));
        enabledPositions.put(2, new HashSet<>(List.of("1", "5")));
        enabledPositions.put(3, new HashSet<>(List.of("0", "4", "6")));
        enabledPositions.put(4, new HashSet<>(List.of("1", "3", "5", "7")));
        enabledPositions.put(5, new HashSet<>(List.of("8", "4", "2")));
        enabledPositions.put(6, new HashSet<>(List.of("3", "7")));
        enabledPositions.put(7, new HashSet<>(List.of("6", "4", "8")));
        enabledPositions.put(8, new HashSet<>(List.of("7", "5")));
    }


    @Override
    public ArrayList<String> move(int btnPos, String val, int zeroPosition, ArrayList<String> field){ // выполнение хода по массиву поля
        if (enabledPositions.get(zeroPosition).contains(btnPos + "")){ // если с нажатой кнопки можно пройти в пустоту
            // новая позиция кнопки - старая позиция пустоты
            field.set(zeroPosition, val); // в массиве поля на место * - цифру кнопки
            field.set(btnPos, "*"); // в массиве поля на место цифры кнопки - *
        }
        return field;
    }

    @Override
    public List<String> shuffleField(){ // перемешивание поля для случайной новой игры
        List<String> field = new ArrayList<>(List.of("*", "1", "2", "3", "4", "5", "6", "7", "8"));
        Collections.shuffle(field); // перетасовка массива поля
        return field;
    }

    @Override
    public boolean isWin(List<String> field){
        return field.equals(winField);
    } // проверка на победу

    @Override
    public boolean isCorrectField(List<String> testField){ // проверка корректности полученного в массиве поля
        // костыль - применяется для защиты от чтения не того файла
        List<String> correctSortedField = List.of("*", "1", "2", "3", "4", "5", "6", "7", "8");
        Collections.sort(testField);
        return testField.equals(correctSortedField);
    }
}

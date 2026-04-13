package testing.GameBackend.Implementations.Holder;

import testing.Interfaces.Holder;
import testing.Interfaces.HolderChangePublisher;

import java.util.ArrayList;
import java.util.List;

// класс-реализация модели игры (субъект наблюдения для главного окна)
public class SimpleHolder implements Holder {
    private ArrayList<String> field = new ArrayList<String>(List.of("1", "2", "3", "4", "5", "6", "7", "*", "8")) ; // поле
    private int zeroPosition = 7; // позиция пустоты
    private boolean isWin = false; // флаг выигрыша
    private HolderChangePublisher changePublisher = null; // экземпляр менеджера уведомлений для отправки уведомления наблюдателям

    public SimpleHolder(HolderChangePublisher publisher){ this.changePublisher = publisher; } // ввод менеджера для субъекта наблюдения

    @Override
    public ArrayList<String> getField() {
        return field;
    }

    @Override
    public int getZeroPosition() {
        return zeroPosition;
    }

    private void setField(List<String> newField) {
        this.field = (ArrayList<String>) newField;
    }

    private void setZeroPosition(int newZeroPosition) { // установка позиции пустоты
        if (newZeroPosition >= 0){
            if (newZeroPosition < 9) this.zeroPosition = newZeroPosition; // если задана (0-9) - установить заданную
        } else {
            zeroPosition = 0; // иначе определить перебором
            while (!field.get(zeroPosition).equals("*")) zeroPosition++;
        }
    }

    private void setIsWin(boolean isWin){
        this.isWin = isWin;
    }

    @Override
    public boolean isWin(){
        return isWin;
    }

    @Override
    public void updateHolder(ArrayList<String> newField, int newZeroPosition, boolean isWin) {
        setField(newField);
        setZeroPosition(newZeroPosition); // может прийти как -1 так и 0-9 - дальше определятся в методе
        setIsWin(isWin);

        changePublisher.notify(this); // отправка уведомления об изменении в менеджер
    }

    @Override
    public Holder getHolder(){
        return this;
    }
}

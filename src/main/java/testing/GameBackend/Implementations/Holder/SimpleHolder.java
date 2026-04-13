package testing.GameBackend.Implementations.Holder;

import testing.Interfaces.Holder;
import testing.Interfaces.HolderChangePublisher;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация модели игры (Subject в паттерне Observer).
 * <p>
 * Хранит текущее состояние игрового поля, позицию пустой клетки и флаг победы.
 * При изменении состояния уведомляет всех подписанных наблюдателей через
 * {@link HolderChangePublisher}.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Holder
 * @see HolderChangePublisher
 */
public class SimpleHolder implements Holder {
    /** Текущее состояние игрового поля (массив из 9 элементов) */
    private ArrayList<String> field = new ArrayList<String>(List.of("1", "2", "3", "4", "5", "6", "7", "*", "8"));

    /** Индекс позиции пустой клетки (*) в массиве field */
    private int zeroPosition = 7;

    /** Флаг, указывающий на то, что игра выиграна */
    private boolean isWin = false;

    /** Менеджер уведомлений для отправки оповещений наблюдателям */
    private HolderChangePublisher changePublisher = null;

    /**
     * Конструктор с указанием менеджера уведомлений.
     *
     * @param publisher экземпляр менеджера уведомлений для субъекта наблюдения
     */
    public SimpleHolder(HolderChangePublisher publisher) {
        this.changePublisher = publisher;
    }

    /**
     * Возвращает текущее состояние игрового поля.
     *
     * @return ArrayList с текущим состоянием поля
     */
    @Override
    public ArrayList<String> getField() {
        return field;
    }

    /**
     * Возвращает позицию пустой клетки.
     *
     * @return индекс пустой клетки в массиве field
     */
    @Override
    public int getZeroPosition() {
        return zeroPosition;
    }

    /**
     * Устанавливает новое состояние игрового поля.
     *
     * @param newField новое состояние поля
     */
    private void setField(List<String> newField) {
        this.field = (ArrayList<String>) newField;
    }

    /**
     * Устанавливает позицию пустой клетки.
     * <p>
     * Если переданное значение в диапазоне 0-8, устанавливает его.
     * Если передано -1, определяет позицию автоматическим поиском.
     * </p>
     *
     * @param newZeroPosition новая позиция пустой клетки или -1 для автоматического поиска
     */
    private void setZeroPosition(int newZeroPosition) {
        if (newZeroPosition >= 0) {
            if (newZeroPosition < 9) this.zeroPosition = newZeroPosition;
        } else {
            zeroPosition = 0;
            while (!field.get(zeroPosition).equals("*")) zeroPosition++;
        }
    }

    /**
     * Устанавливает флаг выигрыша.
     *
     * @param isWin новое значение флага победы
     */
    private void setIsWin(boolean isWin) {
        this.isWin = isWin;
    }

    /**
     * Возвращает флаг выигрыша.
     *
     * @return true, если игра выиграна, иначе false
     */
    @Override
    public boolean isWin() {
        return isWin;
    }

    /**
     * Обновляет состояние модели и уведомляет наблюдателей.
     *
     * @param newField      новое состояние поля
     * @param newZeroPosition новая позиция пустой клетки (-1 для автоматического поиска)
     * @param isWin         новое значение флага победы
     */
    @Override
    public void updateHolder(ArrayList<String> newField, int newZeroPosition, boolean isWin) {
        setField(newField);
        setZeroPosition(newZeroPosition);
        setIsWin(isWin);
        changePublisher.notify(this);
    }

    /**
     * Возвращает ссылку на текущий экземпляр Holder.
     *
     * @return текущий экземпляр SimpleHolder
     */
    @Override
    public Holder getHolder() {
        return this;
    }
}
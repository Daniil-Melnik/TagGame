package testing.GameBackend.DTO;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Data Transfer Object (DTO) для сохранения и загрузки состояния игры.
 * <p>
 * Поддерживает сериализацию в форматы:
 * <ul>
 *     <li>JSON - через Jackson (маппинг по геттерам)</li>
 *     <li>XML - через JAXB (маппинг по аннотациям)</li>
 * </ul>
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "gameSave")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaveDTO {
    /** Флаг, указывающий на то, что игра была выиграна на момент сохранения */
    private boolean win = false;

    /** Текущее состояние игрового поля (массив из 9 элементов) */
    private ArrayList<String> field = null;

    /** Индекс позиции пустой клетки (*) в массиве field */
    private int zeroPosition = 0;

    /**
     * Возвращает копию игрового поля.
     * <p>
     * Возврат копии обеспечивает защиту от нежелательных изменений со стороны
     * библиотек маппинга (например, Jackson).
     * </p>
     *
     * @return новый ArrayList, содержащий копию состояния поля
     */
    public ArrayList<String> getField() {
        return new ArrayList<>(field);
    }

    /**
     * Возвращает флаг выигрыша.
     *
     * @return true, если игра была выиграна, иначе false
     */
    public boolean getWin() {
        return win;
    }

    /**
     * Возвращает позицию пустой клетки.
     *
     * @return индекс пустой клетки в массиве field
     */
    public int getZeroPosition() {
        return zeroPosition;
    }
}
package testing.Interfaces.Builder;

import testing.GameBackend.DTO.InDTO;

/**
 * Интерфейс строителя для объектов {@link InDTO}.
 * <p>
 * Реализует паттерн "Строитель" для пошагового конструирования
 * объектов InDTO с использованием fluent-интерфейса.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see InDTO
 */
public interface InDTOBuilder {

    /**
     * Устанавливает позицию кнопки.
     *
     * @param position позиция от 0 до 8
     * @return текущий экземпляр строителя для цепочки вызовов
     */
    InDTOBuilder setPosition(int position);

    /**
     * Устанавливает значение кнопки.
     *
     * @param value цифровое значение кнопки (1-8)
     * @return текущий экземпляр строителя для цепочки вызовов
     */
    InDTOBuilder setValue(String value);

    /**
     * Устанавливает путь к файлу.
     *
     * @param path путь к файлу для операций сохранения/загрузки
     * @return текущий экземпляр строителя для цепочки вызовов
     */
    InDTOBuilder setPath(String path);

    /**
     * Сбрасывает все поля строителя в значения по умолчанию.
     *
     * @return текущий экземпляр строителя для цепочки вызовов
     */
    InDTOBuilder reset();

    /**
     * Создает новый экземпляр {@link InDTO} с установленными значениями.
     *
     * @return новый объект InDTO
     */
    InDTO build();
}
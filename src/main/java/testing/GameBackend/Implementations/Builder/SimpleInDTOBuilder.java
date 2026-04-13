package testing.GameBackend.Implementations.Builder;

import testing.GameBackend.DTO.InDTO;
import testing.Interfaces.Builder.InDTOBuilder;

/**
 * Реализация строителя для объектов {@link InDTO}.
 * <p>
 * Позволяет пошагово конструировать экземпляры InDTO с использованием
 * fluent-интерфейса. Поддерживает сброс состояния для повторного использования.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see InDTO
 * @see InDTOBuilder
 */
public class SimpleInDTOBuilder implements InDTOBuilder {
    /** Позиция кнопки (дублер поля position в InDTO) */
    private int position = -1;

    /** Значение кнопки (дублер поля value в InDTO) */
    private String value = "*";

    /** Путь к файлу (дублер поля path в InDTO) */
    private String path = "";

    /**
     * Устанавливает позицию кнопки.
     *
     * @param position позиция от 0 до 8
     * @return текущий экземпляр строителя для цепочки вызовов
     */
    @Override
    public InDTOBuilder setPosition(int position) {
        this.position = position;
        return this;
    }

    /**
     * Устанавливает значение кнопки.
     *
     * @param value цифровое значение кнопки (1-8)
     * @return текущий экземпляр строителя для цепочки вызовов
     */
    @Override
    public InDTOBuilder setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Устанавливает путь к файлу.
     *
     * @param path путь к файлу для операций сохранения/загрузки
     * @return текущий экземпляр строителя для цепочки вызовов
     */
    @Override
    public InDTOBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * Создает новый экземпляр {@link InDTO} с установленными значениями.
     *
     * @return новый объект InDTO
     */
    @Override
    public InDTO build() {
        return new InDTO(position, value, path);
    }

    /**
     * Сбрасывает все поля строителя в значения по умолчанию.
     * <p>
     * Позволяет переиспользовать один экземпляр строителя для создания
     * нескольких объектов InDTO.
     * </p>
     *
     * @return текущий экземпляр строителя для цепочки вызовов
     */
    @Override
    public InDTOBuilder reset() {
        this.position = -1;
        this.value = "*";
        this.path = "";
        return this;
    }
}
package testing.GameBackend;

import testing.GameBackend.DTO.InDTO;
import testing.GameBackend.Implementations.Builder.SimpleInDTOBuilder;
import testing.Interfaces.Builder.InDTOBuilder;

/**
 * Фабрика для создания экземпляров {@link InDTO}.
 * <p>
 * Реализует паттерн "Фабрика" на основе паттерна "Строитель".
 * Позволяет создавать DTO для двух типов операций:
 * <ul>
 *     <li>Выполнение хода - требуется позиция и значение кнопки</li>
 *     <li>Файловые операции - требуется путь к файлу</li>
 * </ul>
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see InDTO
 * @see InDTOBuilder
 */
public class InDTOFactory {

    /** Экземпляр строителя для создания объектов InDTO */
    private static InDTOBuilder builder = new SimpleInDTOBuilder();

    /**
     * Создает DTO для выполнения хода.
     *
     * @param position позиция кнопки на игровом поле (0-8)
     * @param value    значение кнопки (цифра 1-8)
     * @return готовый экземпляр InDTO с заполненными полями position и value
     */
    public static InDTO createInitMoveInDTO(int position, String value) {
        return builder
                .reset()
                .setPosition(position)
                .setValue(value)
                .build();
    }

    /**
     * Создает DTO для файловых операций (сохранение/загрузка).
     *
     * @param path путь к файлу для сохранения или загрузки
     * @return готовый экземпляр InDTO с заполненным полем path
     */
    public static InDTO createFileInDTO(String path) {
        return builder
                .reset()
                .setPath(path)
                .build();
    }
}
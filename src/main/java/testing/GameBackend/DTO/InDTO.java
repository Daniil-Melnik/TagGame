package testing.GameBackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) для передачи данных в команды контроллера.
 * <p>
 * Используется в двух сценариях:
 * <ul>
 *     <li>Выполнение хода - передаются position и value</li>
 *     <li>Файловые операции - передается path</li>
 * </ul>
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InDTO {
    /** Позиция кнопки на игровом поле (используется при выполнении хода) */
    private int position;

    /** Значение кнопки (цифра) на игровом поле (используется при выполнении хода) */
    private String value;

    /** Путь к файлу для операций сохранения/загрузки (используется при файловых операциях) */
    private String path;
}
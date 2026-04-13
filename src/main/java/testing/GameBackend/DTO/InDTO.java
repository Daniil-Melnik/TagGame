package testing.GameBackend.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// класс, описывающий DTO для посылки в команду - два случая: выполнение хода / файловая операция
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InDTO {
    private int position; // позиция кнопки (выполнение хода)
    private String value; // значение кнопки (выполнение хода)

    private String path; // путь (файловая операция)
}

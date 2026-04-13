package testing.GameBackend.Implementations.Strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import testing.GameBackend.DTO.SaveDTO;
import testing.Interfaces.FileStrategy;

import java.io.IOException;

/**
 * Стратегия работы с JSON-файлами для сохранения и загрузки состояния игры.
 * <p>
 * Использует библиотеку Jackson для сериализации/десериализации объектов
 * {@link SaveDTO} в формат JSON и обратно.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see FileStrategy
 * @see SaveDTO
 */
public class JsonStrategy implements FileStrategy {
    /** Экземпляр ObjectMapper от Jackson для работы с JSON */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Загружает состояние игры из JSON-файла.
     *
     * @param path путь к JSON-файлу
     * @return объект SaveDTO с загруженным состоянием
     * @throws IOException если возникла ошибка при чтении файла или парсинге JSON
     */
    @Override
    public SaveDTO load(String path) throws IOException {
        try (var fileInput = new java.io.FileInputStream(path)) {
            return objectMapper.readValue(fileInput, SaveDTO.class);
        }
    }

    /**
     * Сохраняет состояние игры в JSON-файл.
     *
     * @param saveDTO объект с состоянием игры для сохранения
     * @param path    путь для сохранения JSON-файла
     * @throws IOException если возникла ошибка при записи файла
     */
    @Override
    public void save(SaveDTO saveDTO, String path) throws IOException {
        try (var fileOutput = new java.io.FileOutputStream(path)) {
            objectMapper.writeValue(fileOutput, saveDTO);
        }
    }
}
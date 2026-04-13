package testing.Interfaces;

import testing.GameBackend.DTO.SaveDTO;

import java.io.IOException;

/**
 * Интерфейс стратегии выбора алгоритма загрузки/сохранения игры.
 * <p>
 * Реализует паттерн "Стратегия", позволяя подменять алгоритмы
 * сериализации для различных форматов файлов (txt, json, xml).
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see SaveDTO
 */
public interface FileStrategy {

    /**
     * Загружает состояние игры из файла.
     *
     * @param path путь к файлу для загрузки
     * @return объект SaveDTO с загруженным состоянием
     * @throws IOException если возникла ошибка при чтении файла
     */
    SaveDTO load(String path) throws IOException;

    /**
     * Сохраняет состояние игры в файл.
     *
     * @param saveDTO объект с состоянием игры для сохранения
     * @param path    путь для сохранения файла
     * @throws IOException если возникла ошибка при записи файла
     */
    void save(SaveDTO saveDTO, String path) throws IOException;
}
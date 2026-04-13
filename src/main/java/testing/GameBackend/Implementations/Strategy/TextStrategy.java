package testing.GameBackend.Implementations.Strategy;

import testing.GameBackend.DTO.SaveDTO;
import testing.Interfaces.FileStrategy;

import java.io.*;
import java.util.ArrayList;

/**
 * Стратегия работы с TXT-файлами для сохранения и загрузки состояния игры.
 * <p>
 * Использует простой текстовый формат, где каждая строка файла соответствует
 * одному элементу игрового поля.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see FileStrategy
 * @see SaveDTO
 */
public class TextStrategy implements FileStrategy {

    /**
     * Загружает состояние игры из TXT-файла.
     * <p>
     * Формат файла: 9 строк, каждая содержит значение клетки (1-8 или *).
     * </p>
     *
     * @param path путь к TXT-файлу
     * @return объект SaveDTO с загруженным состоянием
     * @throws IOException если возникла ошибка при чтении файла
     */
    @Override
    public SaveDTO load(String path) throws IOException {
        ArrayList<String> newField = new ArrayList<>(9);
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            for (int i = 0; i < 9; i++) {
                line = reader.readLine();
                if (line != null) newField.add(line);
            }
        }
        return new SaveDTO(false, newField, -1);
    }

    /**
     * Сохраняет состояние игры в TXT-файл.
     * <p>
     * Каждое значение клетки записывается в отдельную строку.
     * </p>
     *
     * @param saveDTO объект с состоянием игры для сохранения
     * @param path    путь для сохранения TXT-файла
     * @throws IOException если возникла ошибка при записи файла
     */
    @Override
    public void save(SaveDTO saveDTO, String path) throws IOException {
        ArrayList<String> field = saveDTO.getField();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)))) {
            for (String s : field) {
                writer.write(s + "\n");
            }
        }
    }
}
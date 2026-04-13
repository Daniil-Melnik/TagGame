package testing.Interfaces;

import testing.GameBackend.DTO.SaveDTO;

import java.io.IOException;

// интерфес стратегии выбора алгоритма загрузки/сохранения в файлы txt, json и xml
public interface FileStrategy {
    SaveDTO load(String path) throws IOException; // загрузить
    void save(SaveDTO saveDTO, String path) throws IOException; // сохранить
}

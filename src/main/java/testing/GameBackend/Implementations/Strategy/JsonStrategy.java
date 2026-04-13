package testing.GameBackend.Implementations.Strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import testing.GameBackend.DTO.SaveDTO;
import testing.Interfaces.FileStrategy;

import java.io.IOException;

public class JsonStrategy implements FileStrategy { // стратегия работы с JSON-файлами
    private final ObjectMapper objectMapper =  new ObjectMapper(); // применение Jackson

    @Override
    public SaveDTO load(String path) throws IOException { // загрузка и файла
        try (var fileInput = new java.io.FileInputStream(path)) { // try с ресурсами на поток ввода
            SaveDTO saveDTO = objectMapper.readValue(fileInput, SaveDTO.class); // чтение размапленного объекта SaveDTO
            return saveDTO;
        }
    }

    @Override
    public void save(SaveDTO saveDTO, String path) throws IOException { // сохранение в файл
        try (var fileOutput = new java.io.FileOutputStream(path)) { // поток вывода
            objectMapper.writeValue(fileOutput, saveDTO); // запись размаппленного экземпляра saveDTO
        }
    }
}

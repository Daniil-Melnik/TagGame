package testing.GameBackend.Implementations.Strategy;

import testing.GameBackend.DTO.SaveDTO;
import testing.Interfaces.FileStrategy;

import java.io.*;
import java.util.ArrayList;

public class TextStrategy implements FileStrategy { // стратегия работы с txt-файлами

    @Override
    public SaveDTO load(String path) throws IOException { // прочитать поле из файла
        ArrayList<String> newField = new ArrayList<>(9);
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)));) {
            String line = null;
            for (int i = 0; i < 9; i++){
                line = reader.readLine();
                if (line != null) newField.add(line);
            }
        }
        return new SaveDTO(false, newField, -1);
    }

    @Override
    public void save(SaveDTO saveDTO, String path) throws IOException { // сохранить поле в файл
        ArrayList<String> field = saveDTO.getField();
        try (BufferedWriter writer = new BufferedWriter((new FileWriter(new File(path))));) {
            for (String s : field) writer.write(s + "\n");
        }
    }
}

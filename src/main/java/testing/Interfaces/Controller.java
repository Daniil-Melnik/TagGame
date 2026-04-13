package testing.Interfaces;

import testing.GameBackend.DTO.InDTO;

// интерфейс экземпляра контроллера для интеграция в MVC
public interface Controller {
    void makeMove(InDTO inDTO); // сделать ход
    void createNewShuffleGame(); // создать случайную перемешанную игру
    void createNewFileGame(InDTO inDTO); // создание новой игры на основе сохраненного в файле
    void saveGameToFile(InDTO inDTO); // сохранение игры в файл
}

package testing.Interfaces;

import testing.GameBackend.DTO.InDTO;

// интерфейс, описывающий экземпляр поведения контроллера в соответсвии с паттерном "Состояние"
public interface State {
    void makeMove(InDTO inDTO); // выполнить ход
}

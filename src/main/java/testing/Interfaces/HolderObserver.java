package testing.Interfaces;

// интерфейс наблюдателя (в данном случае за изменениями в модели игры)
public interface HolderObserver {
    void update(Holder holder); // обновление на основе нового состояния модели игры
}

package testing.Interfaces;

// интерфейс опевещателя об изменениях в субъекте для реагирования наблюдателя
public interface HolderChangePublisher {
    void subscribe(HolderObserver observer); // метод для подписки на обновления
    void unsubscribe(HolderObserver observer);// метод для отписки от обновлений
    void notify(Holder holder); // метод для оповещения с передачей действующего экземпляра субъекта
}

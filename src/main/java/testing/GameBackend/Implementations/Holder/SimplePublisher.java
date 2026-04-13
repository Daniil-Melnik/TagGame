package testing.GameBackend.Implementations.Holder;

import testing.Interfaces.Holder;
import testing.Interfaces.HolderChangePublisher;
import testing.Interfaces.HolderObserver;

import java.util.HashSet;
import java.util.Set;

public class SimplePublisher implements HolderChangePublisher { // класс менеджера наблюдений

    Set<HolderObserver> observers = new HashSet<>(); // множество наблюдателей

    @Override
    public void subscribe(HolderObserver observer) {
        observers.add(observer);
    } // подписать = добавить в множество наблюдателей

    @Override
    public void unsubscribe(HolderObserver observer) {
        observers.remove(observer);
    } // отписать - удалить из множества наблюдателей

    @Override
    public void notify(Holder holder) { // отправить уведомления
        for (HolderObserver hO : observers) hO.update(holder); // вызов обновления для каждого наблюдателя из множества
    }
}

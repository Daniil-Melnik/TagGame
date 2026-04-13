package testing.GameBackend.Implementations.Holder;

import testing.Interfaces.Holder;
import testing.Interfaces.HolderChangePublisher;
import testing.Interfaces.HolderObserver;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация менеджера уведомлений для паттерна Observer.
 * <p>
 * Управляет подпиской и отпиской наблюдателей, а также рассылкой уведомлений
 * об изменениях в модели {@link SimpleHolder}.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see HolderChangePublisher
 * @see HolderObserver
 */
public class SimplePublisher implements HolderChangePublisher {
    /** Множество зарегистрированных наблюдателей */
    private Set<HolderObserver> observers = new HashSet<>();

    /**
     * Подписывает наблюдателя на уведомления об изменениях.
     *
     * @param observer наблюдатель, который будет подписан
     */
    @Override
    public void subscribe(HolderObserver observer) {
        observers.add(observer);
    }

    /**
     * Отписывает наблюдателя от уведомлений об изменениях.
     *
     * @param observer наблюдатель, который будет отписан
     */
    @Override
    public void unsubscribe(HolderObserver observer) {
        observers.remove(observer);
    }

    /**
     * Уведомляет всех подписанных наблюдателей об изменении состояния.
     * <p>
     * Для каждого наблюдателя вызывается метод {@link HolderObserver#update(Holder)}.
     * </p>
     *
     * @param holder текущий экземпляр субъекта наблюдения (модель)
     */
    @Override
    public void notify(Holder holder) {
        for (HolderObserver hO : observers) {
            hO.update(holder);
        }
    }
}
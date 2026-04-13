package testing.Interfaces;

/**
 * Интерфейс оповещателя об изменениях в субъекте (паттерн "Наблюдатель").
 * <p>
 * Управляет подпиской и отпиской наблюдателей, а также рассылкой уведомлений
 * об изменениях состояния модели.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Holder
 * @see HolderObserver
 */
public interface HolderChangePublisher {

    /**
     * Подписывает наблюдателя на уведомления об изменениях.
     *
     * @param observer наблюдатель, который будет получать уведомления
     */
    void subscribe(HolderObserver observer);

    /**
     * Отписывает наблюдателя от уведомлений об изменениях.
     *
     * @param observer наблюдатель, который больше не будет получать уведомления
     */
    void unsubscribe(HolderObserver observer);

    /**
     * Уведомляет всех подписанных наблюдателей об изменении состояния.
     * <p>
     * Вызывает метод {@link HolderObserver#update(Holder)} для каждого
     * зарегистрированного наблюдателя.
     * </p>
     *
     * @param holder текущий экземпляр субъекта наблюдения (модель)
     */
    void notify(Holder holder);
}
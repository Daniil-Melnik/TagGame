package testing.Interfaces;

/**
 * Интерфейс наблюдателя за изменениями в модели игры (паттерн "Наблюдатель").
 * <p>
 * Реализуется компонентами, которые должны реагировать на изменения состояния
 * игрового поля (например, графический интерфейс).
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see Holder
 * @see HolderChangePublisher
 */
public interface HolderObserver {

    /**
     * Обновляет состояние наблюдателя на основе новой модели игры.
     * <p>
     * Вызывается оповещателем {@link HolderChangePublisher} при изменении
     * состояния модели.
     * </p>
     *
     * @param holder обновленный экземпляр модели игры
     */
    void update(Holder holder);
}
package co.torres.christhian.testgrability.lib;

/**
 * Created by Christhian Hernando Torres on 05/11/2016.
 */

/**
 * Interface para el manejo de eventos
 */
public interface EventBus {
    void register(Object Subscriber);
    void unregister(Object Subscriber);
    void post(Object Event);
}

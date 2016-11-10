package co.torres.christhian.testgrability.lib;

/**
 * Created by Christhian Hernando Torres on 05/11/2016.
 */

/**
 * Clase que hace uso de la libreria Event Bus  de Green Robot
 */
public class GreenRobotEventBus implements EventBus {

    de.greenrobot.event.EventBus eventBus;


    //Singleton para el manejo de una instancia unica del bus de eventos
    private static class SingletonEventBus {
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    //Metodo publico que maneja la instancia del bus de eventos
    public static GreenRobotEventBus getInstance() {
        return SingletonEventBus.INSTANCE;
    }

    //Constructor de la clase para la inicializacion del bus de eventos
    public GreenRobotEventBus() {
        this.eventBus = de.greenrobot.event.EventBus.getDefault();
    }

    /**
     * Metodo sobrecargad de la interface EventBus para el registro de eventos
     *
     * @param Subscriber
     */
    @Override
    public void register(Object Subscriber) {
        //Llamado al metodo de  registro de la libreria eventbus de GreenRobot
        eventBus.register(Subscriber);
    }

    /**
     * Metodo sobrecargado de la interface EventBus para el retiro del registro de eventos
     *
     * @param Subscriber
     */
    @Override
    public void unregister(Object Subscriber) {

        //Llamado al metodo de retiro del registro de la libreria eventbus de GreenRobot
        eventBus.unregister(Subscriber);
    }

    /**
     * Metodo sobrecargado de la interface EventBus para el manejo de los eventos
     *
     * @param Event
     */
    @Override
    public void post(Object Event) {
        //Llamado al metodo de manejo de eventos de la libreria eventbus de GreenRobot
        eventBus.post(Event);
    }
}

package caso1;

public class CubbyHole {
    private int contenido;
    private boolean disponible = false;

    public synchronized int get() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        disponible = false;
        notify();
        return contenido;
    }

    public synchronized void put(int value) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        contenido = value;
        disponible = true;
        notify();
    }
}

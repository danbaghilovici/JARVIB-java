package inter;

public interface Initiable {
    void init();
    void destroy();

    void start();
    void stop();
    void terminate();
}

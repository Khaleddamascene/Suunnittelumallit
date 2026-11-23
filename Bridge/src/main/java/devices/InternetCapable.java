package devices;

/**
 * Optional capability interface (part of the Implementor side of the bridge).
 * Devices that can access the internet implement it in addition to Device.
 */
public interface InternetCapable {
    boolean isConnected();

    void connect(String network);

    void browseInternet(String url);

    String getCurrentPage();
}

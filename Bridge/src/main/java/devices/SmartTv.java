package devices;

/**
 * A TV with internet access. Reuses Tv behaviour through inheritance and adds
 * the InternetCapable capability on top of the unchanged Device interface.
 */
public class SmartTv extends Tv implements InternetCapable {
    private String network = null;
    private String currentPage = null;

    @Override
    public void disable() {
        super.disable();
        currentPage = null;           // browser closes when the TV is switched off
    }

    @Override
    public boolean isConnected() {
        return network != null;
    }

    @Override
    public void connect(String network) {
        this.network = network;
    }

    @Override
    public void browseInternet(String url) {
        if (!isEnabled()) {
            System.out.println("SmartTV: cannot browse, the TV is off");
            return;
        }
        if (!isConnected()) {
            System.out.println("SmartTV: cannot browse, no network connection");
            return;
        }
        currentPage = url;
        System.out.println("SmartTV: browsing " + url + " via " + network);
    }

    @Override
    public String getCurrentPage() {
        return currentPage;
    }

    @Override
    public void printStatus() {
        System.out.println("------------------------------------");
        System.out.println("| I'm Smart TV set.");
        System.out.println("| I'm " + (isEnabled() ? "enabled" : "disabled"));
        System.out.println("| Current volume is " + getVolume() + "%");
        System.out.println("| Current channel is " + getChannel());
        System.out.println("| Network: " + (isConnected() ? network : "not connected"));
        System.out.println("| Browsing: " + (currentPage != null ? currentPage : "-"));
        System.out.println("------------------------------------\n");
    }
}

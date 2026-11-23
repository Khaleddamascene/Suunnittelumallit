package remotes;


import devices.Device;
import devices.InternetCapable;

/**
 * Refined abstraction with voice control. Works with ANY Device; commands that
 * need extra capabilities (browsing) degrade gracefully on devices without them.
 */
public class SmartRemote extends AdvancedRemote {

    public SmartRemote(Device device) {
        super(device);
    }

    /** Interprets a spoken command and delegates to the existing remote operations. */
    public void voiceControl(String command) {
        System.out.println("Remote: voice command \"" + command + "\"");
        if (command == null || command.trim().isEmpty()) {
            System.out.println("Remote: I didn't catch that");
            return;
        }
        String cmd = command.trim().toLowerCase();

        if (cmd.equals("power") || cmd.equals("power on") || cmd.equals("power off")) {
            power();
        } else if (cmd.equals("volume up")) {
            volumeUp();
        } else if (cmd.equals("volume down")) {
            volumeDown();
        } else if (cmd.equals("channel up")) {
            channelUp();
        } else if (cmd.equals("channel down")) {
            channelDown();
        } else if (cmd.equals("mute")) {
            mute();
        } else if (cmd.startsWith("volume ") && isNumber(cmd.substring(7))) {
            device.setVolume(Integer.parseInt(cmd.substring(7).trim()));
        } else if (cmd.startsWith("channel ") && isNumber(cmd.substring(8))) {
            device.setChannel(Integer.parseInt(cmd.substring(8).trim()));
        } else if (cmd.startsWith("browse ")) {
            browseInternet(cmd.substring(7).trim());
        } else {
            System.out.println("Remote: unknown command");
        }
    }

    /** Capability-aware: only devices implementing InternetCapable can browse. */
    public void browseInternet(String url) {
        if (device instanceof InternetCapable) {
            ((InternetCapable) device).browseInternet(url);
        } else {
            System.out.println("Remote: this device cannot browse the internet");
        }
    }

    /** Convenience for connecting the device to a network, if it supports it. */
    public void connectToNetwork(String network) {
        if (device instanceof InternetCapable) {
            System.out.println("Remote: connecting to " + network);
            ((InternetCapable) device).connect(network);
        } else {
            System.out.println("Remote: this device has no network support");
        }
    }

    private static boolean isNumber(String s) {
        try {
            Integer.parseInt(s.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

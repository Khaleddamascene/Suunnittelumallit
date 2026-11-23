
import devices.*;
import remotes.AdvancedRemote;
import remotes.BasicRemote;
import remotes.SmartRemote;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SmartTests {
    static int passed = 0, failed = 0;

    static void check(String name, boolean cond) {
        if (cond) { passed++; System.out.println("PASS  " + name); }
        else      { failed++; System.out.println("FAIL  " + name); }
    }

    // Run code while silencing the remote's console chatter
    static void quiet(Runnable r) {
        PrintStream old = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        try { r.run(); } finally { System.setOut(old); }
    }

    public static void main(String[] args) {
        // --- SmartTv on its own ---
        SmartTv tv = new SmartTv();
        check("SmartTv is a Device", tv instanceof Device);
        check("SmartTv starts disconnected", !tv.isConnected());
        quiet(() -> tv.browseInternet("a.com"));
        check("browse is rejected while TV is off", tv.getCurrentPage() == null);
        tv.enable();
        quiet(() -> tv.browseInternet("a.com"));
        check("browse is rejected without network", tv.getCurrentPage() == null);
        tv.connect("HomeWiFi");
        quiet(() -> tv.browseInternet("a.com"));
        check("browse works when on + connected", "a.com".equals(tv.getCurrentPage()));
        tv.disable();
        check("powering off closes the browser", tv.getCurrentPage() == null);

        // --- SmartRemote + SmartTv ---
        SmartTv t2 = new SmartTv();
        SmartRemote r2 = new SmartRemote(t2);
        quiet(() -> r2.voiceControl("power on"));
        check("voice: power toggles the device on", t2.isEnabled());
        quiet(() -> r2.voiceControl("VOLUME UP"));
        check("voice: case-insensitive 'volume up' (30 -> 40)", t2.getVolume() == 40);
        quiet(() -> r2.voiceControl("volume 75"));
        check("voice: 'volume 75' sets absolute volume", t2.getVolume() == 75);
        quiet(() -> r2.voiceControl("volume 500"));
        check("voice: volume clamped by device to 100", t2.getVolume() == 100);
        quiet(() -> r2.voiceControl("channel 12"));
        check("voice: 'channel 12' sets channel", t2.getChannel() == 12);
        quiet(() -> r2.voiceControl("channel down"));
        check("voice: 'channel down' (12 -> 11)", t2.getChannel() == 11);
        quiet(() -> r2.voiceControl("mute"));
        check("voice: mute sets volume 0", t2.getVolume() == 0);
        quiet(() -> r2.connectToNetwork("HomeWiFi"));
        quiet(() -> r2.voiceControl("browse news.fi"));
        check("voice: browse delegates to SmartTv", "news.fi".equals(t2.getCurrentPage()));
        int vol = t2.getVolume();
        quiet(() -> r2.voiceControl("dance"));
        quiet(() -> r2.voiceControl(""));
        quiet(() -> r2.voiceControl(null));
        check("unknown/empty/null commands change nothing", t2.getVolume() == vol && t2.isEnabled());

        // --- Bridge property: same remote, other devices ---
        Radio radio = new Radio();
        SmartRemote r3 = new SmartRemote(radio);
        quiet(() -> r3.voiceControl("power on"));
        quiet(() -> r3.voiceControl("browse x.com"));
        check("SmartRemote controls a Radio", radio.isEnabled());
        check("Radio is not InternetCapable (no crash on browse)", !(radio instanceof InternetCapable));

        Tv plain = new Tv();
        SmartRemote r4 = new SmartRemote(plain);
        quiet(() -> r4.voiceControl("power on"));
        quiet(() -> r4.voiceControl("channel up"));
        check("SmartRemote controls a plain Tv", plain.isEnabled() && plain.getChannel() == 2);

        // --- Bridge property: old remotes with new device ---
        SmartTv t5 = new SmartTv();
        BasicRemote br = new BasicRemote(t5);
        AdvancedRemote ar = new AdvancedRemote(t5);
        quiet(() -> br.power());
        quiet(() -> br.volumeUp());
        quiet(() -> ar.mute());
        check("BasicRemote/AdvancedRemote work with SmartTv", t5.isEnabled() && t5.getVolume() == 0);

        System.out.println("\n" + passed + " passed, " + failed + " failed");
        if (failed > 0) System.exit(1);
    }
}

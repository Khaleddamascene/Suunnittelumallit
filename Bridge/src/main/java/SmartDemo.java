import devices.Radio;
import devices.SmartTv;
import devices.Tv;
import remotes.SmartRemote;

public class SmartDemo {
    public static void main(String[] args) {
        SmartTv tv = new SmartTv();
        SmartRemote remote = new SmartRemote(tv);

        remote.voiceControl("power on");
        remote.voiceControl("browse example.com");
        remote.connectToNetwork("HomeWiFi");
        remote.voiceControl("browse example.com");
        remote.voiceControl("volume 55");
        remote.voiceControl("channel 7");
        tv.printStatus();

        remote.voiceControl("mute");
        remote.voiceControl("make me a sandwich");
        tv.printStatus();

        System.out.println("=== Same SmartRemote, different devices ===");
        SmartRemote onRadio = new SmartRemote(new Radio());
        onRadio.voiceControl("power on");
        onRadio.voiceControl("browse example.com");

        SmartRemote onTv = new SmartRemote(new Tv());
        onTv.voiceControl("power on");
        onTv.voiceControl("volume up");
        onTv.voiceControl("browse example.com");
    }
}

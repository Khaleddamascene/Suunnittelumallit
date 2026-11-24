import javafx.application.Application;

/** Entry point for IDEs and command-line launches. */
public final class Game {
    private Game() {
    }

    public static void main(String[] args) {
        Application.launch(GameApplication.class, args);
    }
}

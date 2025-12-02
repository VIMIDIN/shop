package chain;

public class LevelTwoHandler extends SupportHandler {
    @Override
    public void handleRequest(int level) {
        if (level == 2) {
            System.out.println("Обработка запроса на уровне 2.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(level);
        }
    }
}

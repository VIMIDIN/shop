package chain;

public class LevelOneHandler extends SupportHandler {
    @Override
    public void handleRequest(int level) {
        if (level == 1) {
            System.out.println("Обработка запроса на уровне 1.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(level);
        }
    }
}

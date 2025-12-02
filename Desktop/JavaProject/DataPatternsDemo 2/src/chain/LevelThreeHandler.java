package chain;

public class LevelThreeHandler extends SupportHandler {
    @Override
    public void handleRequest(int level) {
        if (level == 3) {
            System.out.println("Обработка запроса на уровне 3.");
        } else {
            System.out.println("Запрос не может быть обработан.");
        }
    }
}

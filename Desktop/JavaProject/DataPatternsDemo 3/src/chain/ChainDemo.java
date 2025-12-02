package chain;

public class ChainDemo {
    public static void main(String[] args) {
        SupportHandler levelOne = new LevelOneHandler();
        SupportHandler levelTwo = new LevelTwoHandler();
        SupportHandler levelThree = new LevelThreeHandler();

        levelOne.setNextHandler(levelTwo);
        levelTwo.setNextHandler(levelThree);

        System.out.println("Запрос на уровень 1:");
        levelOne.handleRequest(1);
        System.out.println("\nЗапрос на уровень 2:");
        levelOne.handleRequest(2);
        System.out.println("\nЗапрос на уровень 3:");
        levelOne.handleRequest(3);
        System.out.println("\nЗапрос на уровень 4:");
        levelOne.handleRequest(4);
    }
}

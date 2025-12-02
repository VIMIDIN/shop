package mediator;

public class MediatorDemo {
    public static void main(String[] args) {
        Mediator chatMediator = new ChatMediator();

        User u1 = new ConcreteUser(chatMediator, "Пользователь 1");
        User u2 = new ConcreteUser(chatMediator, "Пользователь 2");
        User u3 = new ConcreteUser(chatMediator, "Пользователь 3");

        chatMediator.addUser(u1);
        chatMediator.addUser(u2);
        chatMediator.addUser(u3);

        u1.sendMessage("Привет всем!");
        u2.sendMessage("Привет, Пользователь 1!");
    }
}

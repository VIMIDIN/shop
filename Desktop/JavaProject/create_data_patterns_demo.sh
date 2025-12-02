#!/usr/bin/env bash
 create_data_patterns_demo.s
#!/usr/bin/env bash

# Скрипт для macOS / Linux — создаёт структуру проекта DataPatternsDemo

ROOT="DataPatternsDemo"
SRC="$ROOT/src"
UML="$ROOT/uml"

echo "Создаю структуру проекта..."

mkdir -p "$SRC/mediator"
mkdir -p "$SRC/chain"
mkdir -p "$UML"

cat > "$SRC/mediator/Mediator.java" << 'EOF'
package mediator;

public interface Mediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}
EOF

cat > "$SRC/mediator/ChatMediator.java" << 'EOF'
package mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatMediator implements Mediator {
    private List<User> users;

    public ChatMediator() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            if (u != user) {
                u.receiveMessage(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
EOF

cat > "$SRC/mediator/User.java" << 'EOF'
package mediator;

public abstract class User {
    protected Mediator mediator;
    protected String name;

    public User(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void sendMessage(String message);
    public abstract void receiveMessage(String message);
}
EOF

cat > "$SRC/mediator/ConcreteUser.java" << 'EOF'
package mediator;

public class ConcreteUser extends User {
    public ConcreteUser(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(name + " отправляет сообщение: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }
}
EOF

cat > "$SRC/mediator/MediatorDemo.java" << 'EOF'
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
EOF

cat > "$SRC/chain/SupportHandler.java" << 'EOF'
package chain;

public abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(int level);
}
EOF

cat > "$SRC/chain/LevelOneHandler.java" << 'EOF'
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
EOF

cat > "$SRC/chain/LevelTwoHandler.java" << 'EOF'
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
EOF

cat > "$SRC/chain/LevelThreeHandler.java" << 'EOF'
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
EOF

cat > "$SRC/chain/ChainDemo.java" << 'EOF'
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
EOF

cat > "$UML/MediatorClass.puml" << 'EOF'
@startuml
interface Mediator {
  +sendMessage(message, user)
  +addUser(user)
}

class ChatMediator {
  -users : List<User>
  +sendMessage(message, user)
  +addUser(user)
}

abstract class User {
  -mediator : Mediator
  -name : String
  +sendMessage(message)
  +receiveMessage(message)
}

class ConcreteUser

Mediator <|.. ChatMediator
User <|-- ConcreteUser
ChatMediator o-- User
@enduml
EOF

cat > "$UML/ChainClass.puml" << 'EOF'
@startuml
abstract class SupportHandler {
  -nextHandler : SupportHandler
  +setNextHandler(nextHandler)
  +handleRequest(level)
}

class LevelOneHandler
class LevelTwoHandler
class LevelThreeHandler

SupportHandler <|-- LevelOneHandler
SupportHandler <|-- LevelTwoHandler
SupportHandler <|-- LevelThreeHandler

LevelOneHandler --> LevelTwoHandler : nextHandler
LevelTwoHandler --> LevelThreeHandler : nextHandler
@enduml
EOF

echo "Исходники созданы."

# Создать ZIP
ZIPFILE="${ROOT}.zip"
if [ -f "$ZIPFILE" ]; then
    rm "$ZIPFILE"
fi
zip -r "$ZIPFILE" "$ROOT"
echo "Проект упакован в $ZIPFILE"

echo "Готово. Открой $ZIPFILE, разархивируй и открой в IntelliJ IDEA."
h


package exceptions;

/*
Реализовать интерфейс Terminal, c помощью которого можно:
•	Проверить состояние счета
•	Снять/ положить деньги
Доступ к терминалу предоставляется только после ввода корректного пин-кода (4 цифры).
При вводе нецифрового символа система должна выдать предупреждение.
При вводе 3 неправильных пин-кодов аккаунт блокируется на 10 сек
Класть и снимать деньги можно только, если сумма кратна 100.
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Main работает");

        TerminalServer server = new TerminalServer();
        PinValidator pinValidator = new PinValidator();
        ATM session = new ATM(server, pinValidator);
        try {
            session.work();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main завершил работу");
    }
}

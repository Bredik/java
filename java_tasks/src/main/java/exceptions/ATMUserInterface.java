package exceptions;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ATMUserInterface {
    private final Scanner in;
    private final PrintStream out;

    ATMUserInterface(InputStream inputStream, PrintStream out) {
        this.in = new Scanner(inputStream);
        this.out = out;
    }

    public String getAction() {
        out.println("Ожидается ввод...");
        return in.nextLine();
    }

    // Сообщения про ПИН
    public void showWrongPinNumberWarning() {
        out.println("Пин должен состоять из четырех (4) цифр");
    }

    public void showEmptyStringWarning() {
        out.println("Вы не ввели данные");
    }

    public void showWrongPinError() {
        out.println("ОШИБКА. Вы ввели пин не правильно 3 раза. Аккаунт блокируется на 10 секунд.");
    }

    // Сообщения про баланс
    public void showBillInfo(Integer balance) {
        out.println("Ваш баланс: " + balance);
    }

    //Сообщения про зачисление и снятие денег
    public void showWaitingPutMoneyMessage() {
        out.println("Введите сумму, которую хотите ПОЛОЖИТЬ");
    }

    public void showWaitingWithdrawMoneyMessage() {
        out.println("Введите сумму, которую вы хотите СНЯТЬ");
    }

    public void showDonePutMoneyMessage() {
        out.println("Ваши деньги зачислены!");
    }

    public void showDoneWithdrawMoneyMessage() {
        out.println("Вы успешно сняли деньги!");
    }

    public void showWrongPutMoneyWarning() {
        out.println("ОШИБКА. Сумма должна быть кратной 100 и не равна 0");
    }

    // Другое
    public void showExit() {
        out.println("До свидания! Спасибо, что вы с нами!");
    }
}

package exceptions;

public class TerminalServer implements Terminal {

    @Override
    public int getBillInfo() {
        System.out.println("Сервер получил запрос на информацию о счете - запрос баланса в БД");
        return 5632;
    }

    @Override
    public void putMoney(String money) {
        System.out.println("Сервер получил запрос, вы кладете деньги");
        System.out.println("Вы зачислили на счет: " + money);
    }

    @Override
    public void withdrawMoney(String money) {
        System.out.println("Сервер получил запрос, вы снимаете деньги");
        System.out.println("Вы сняли со счета: " + money);
    }
}

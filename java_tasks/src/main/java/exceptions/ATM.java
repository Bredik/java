package exceptions;

public class ATM implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private final ATMUserInterface session = new ATMUserInterface(System.in, System.out);

    public ATM(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    public void work() throws InterruptedException {

        boolean pinCorrect = false;
        int tryPin = 0;

        while (true) {
            // Открываем сессию, ждем данные
            String action = session.getAction();

            if (action.equals("exit") || action.equals("0")) {
                session.showExit();
                break;
            }

            if (!pinCorrect) {
                try
                {
                    if (action.isEmpty()) {
                        session.showEmptyStringWarning();
                    } else {
                        tryPin++;
                        pinCorrect = pinValidator.check(action, tryPin);
                    }
                } catch (ATMException.LotWrongPinAttempts e) {
                    session.showWrongPinError();
                    Thread.sleep(10000);
                }
                catch (ATMException.WrongCountPinException e)
                {
                    session.showWrongPinNumberWarning();
                }
            } else {
                // getInfo - "кнопка" для получения инфы о балансе счета
                if (action.contains("getInfo")) {
                    session.showBillInfo(getBillInfo());
                }

                // putMoney - "кнопка" для положить деньги на счет
                if (action.contains("putMoney")) {
                    session.showWaitingPutMoneyMessage();
                    try
                    {
                        putMoney(session.getAction());
                        session.showDonePutMoneyMessage();
                    } catch (ATMException.WrongMoneyException | ATMException.NullMoneyException e)
                    {
                        session.showWrongPutMoneyWarning();
                    }
                }

                // withdrawMoney - "кнопка" для снять деньги
                if (action.contains("withdrawMoney")) {
                    session.showWaitingWithdrawMoneyMessage();
                    try
                    {
                        withdrawMoney(session.getAction());
                        session.showDoneWithdrawMoneyMessage();
                    } catch (ATMException.WrongMoneyException | ATMException.NullMoneyException e)
                    {
                        session.showWrongPutMoneyWarning();
                    }
                }
            }
        }
    }

    public int getBillInfo() {
        System.out.println("Проверка счета - запрос на сервер");
        return server.getBillInfo();
    }

    public void putMoney(String money) {
        System.out.println("Положить деньги на счет");
        int sum = Integer.parseInt(money);
        if (sum % 100 != 0) {
            throw new ATMException.WrongMoneyException();
        }
        if (sum == 0) {
            throw new ATMException.NullMoneyException();
        }
        System.out.println("Запрос на сервер");
        server.putMoney(money);
    }

    public void withdrawMoney(String money) {
        System.out.println("Снять деньги со счета");
        int sum = Integer.parseInt(money);
        if (sum % 100 != 0) {
            throw new ATMException.WrongMoneyException();
        }
        if (sum == 0) {
            throw new ATMException.NullMoneyException();
        }
        System.out.println("Запрос на сервер");
        server.withdrawMoney(money);
    }
}

package exceptions;

public class ATMException extends Exception {
    public static class WrongCountPinException extends RuntimeException {
        WrongCountPinException() {
            super("Pin must have 4 numbers");
        }
    }

    public static class WrongMoneyException extends RuntimeException {
        WrongMoneyException() {
            super("Amount must be a multiple of 100");
        }
    }

    public static class NullMoneyException extends RuntimeException {
        NullMoneyException() {
            super("Put zero money");
        }
    }

    public static class LotWrongPinAttempts extends RuntimeException {
        LotWrongPinAttempts() {
            super("Account blocked");
        }
    }
}

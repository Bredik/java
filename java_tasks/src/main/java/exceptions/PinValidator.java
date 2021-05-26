package exceptions;

public class PinValidator {
    public boolean check(String pin, int tryPin) {
        try {
            int pinIsNumber = Integer.parseInt(pin);
            if (pin.length() != 4) {

                if (tryPin >= 3) {
                    throw new ATMException.LotWrongPinAttempts();
                }

                throw new ATMException.WrongCountPinException();
            }

            return true;
        } catch (NumberFormatException e) {
            if (tryPin >= 3) {
                throw new ATMException.LotWrongPinAttempts();
            }
            System.out.println("Ошибка. Пин должен быть числом");
            return false;
        }
    }
}

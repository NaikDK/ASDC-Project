package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.login.organizer.AccountCheckHandler;
import group11.EventFiesta.account.login.organizer.ILoginHandler;
import group11.EventFiesta.model.Account;

import java.util.Arrays;

public class GenerateOTP {

    IDBPersistence idbPersistence;
    public GenerateOTP(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }

    public IState generateOTP(Account account, Object [] params) {
        try {
            System.out.println("Inside generateOTP: ");
            System.out.println(account);
            System.out.println(Arrays.toString(params));
            ILoginHandler accounCheckHandler = new AccountCheckHandler(idbPersistence, params);
            ILoginHandler otpHandler = new OTPHandler(idbPersistence);
            accounCheckHandler.setNextHandler(otpHandler);
            return accounCheckHandler.execute(account);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}

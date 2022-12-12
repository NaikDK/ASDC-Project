package group11.EventFiesta.account.login.organizer;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.UserEventChecklist.EncryptPassword;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Account;

import java.util.ArrayList;
import java.util.HashMap;

public class VerifyPasswordHandler extends LoginHandler {

    IDBPersistence idbPersistence;

    VerifyPasswordHandler(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }

    @Override
    public IState execute(Account organizer) throws Exception {
        Object [] params = new Object[] {"OrganizerSensitive", "encrypted_password, private_key", "organizer_id", organizer.getAccountId()};
        ArrayList<HashMap<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        System.out.println(data);
        if (data.size() > 0) {
            HashMap<String, Object> row = data.get(0);
            String pwdFromDB = row.get("encrypted_password").toString();
            String saltFromDB = row.get("private_key").toString();
            String encPwd = EncryptPassword.getEncryptedPwd(organizer.getPassword(), saltFromDB);
            if (pwdFromDB.equals(encPwd)) {
                return nextHandler.execute(organizer);
            }
        }
        return new IncorrectPassword(organizer);
    }
}

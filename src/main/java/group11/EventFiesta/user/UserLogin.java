package group11.EventFiesta.user;

import group11.EventFiesta.ILogin;

public class UserLogin implements ILogin {
    @Override
    public Object login(String username, String pwd) {
        return null;
    }

    @Override
    public Boolean logout(Object object) {
        return null;
    }

    @Override
    public Boolean resetPassword(String emailId, String newPassword){
        return null;
    }
}

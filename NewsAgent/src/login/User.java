package login;

public class User {
    private int id;
    private String userName;
    private String password;

    public User(String userName, String password) throws UserExceptionHandler {
        try {
            validateUserName(userName);
            validateUserPassword(password);
        } catch (UserExceptionHandler userExceptionHandler) {
            throw userExceptionHandler;
        }
        this.id = 0;
        this.userName = userName;
        this.password = password;
    }


    //Agree Formating Rules on "User Name"
    //E.G. User Name String must be a minimum of 3 characters and a maximum of 20 characters
    public static void validateUserName(String userName) throws UserExceptionHandler {
        if (userName.isBlank() || userName.isEmpty())
            throw new UserExceptionHandler("user Name NOT specified");
        else if (userName.length() < 3)
            throw new UserExceptionHandler("user Name does not meet minimum length requirements");
        else if (userName.length() > 20)
            throw new UserExceptionHandler("user Name does not exceeds maximum length requirements");
    }


    //Agree Formating Rules on "password"
    //E.G. User Name String must be a minimum of 3 characters and a maximum of 20 characters
    public static void validateUserPassword(String password) throws UserExceptionHandler {
        if (password.isBlank() || password.isEmpty())
            throw new UserExceptionHandler("password NOT specified");
        else if (password.length() < 6)
            throw new UserExceptionHandler("password does not meet minimum length requirements");
        else if (password.length() > 20)
            throw new UserExceptionHandler("password does not exceeds maximum length requirements");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

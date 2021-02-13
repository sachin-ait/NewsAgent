package login;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testUser001() {

        try {
            User user = new User("tom", "a123456");
            // Use getters to check for object creation
            assertEquals(0, user.getId());
            assertEquals("tom", user.getUserName());
            assertEquals("a123456", user.getPassword());
        } catch (UserExceptionHandler userExceptionHandler) {
            fail("Exception not expected");
        }

    }

    //userName ----   3 characters and a maximum of 20 characters
    public void testValidateUserName001() {

        try {
            User.validateUserName("");
        } catch (UserExceptionHandler e) {
            assertEquals("user Name NOT specified", e.getMessage());
        }
        try {
            User.validateUserName("AJ");
        } catch (UserExceptionHandler e) {
            assertEquals("user Name does not meet minimum length requirements", e.getMessage());
        }
        try {
            User.validateUserName("JerrydJerrydJerrydJerrydJerrydJerrydJerryd");
        } catch (UserExceptionHandler e) {
            assertEquals("user Name does not exceeds maximum length requirements", e.getMessage());
        }
    }

    //password----   6 characters and a maximum of 20 characters
    public void testValidatePassword001() {

        try {
            User.validateUserPassword("");
        } catch (UserExceptionHandler e) {
            assertEquals("password NOT specified", e.getMessage());
        }
        try {
            User.validateUserPassword("abcde");
        } catch (UserExceptionHandler e) {
            assertEquals("password does not meet minimum length requirements", e.getMessage());
        }
        try {
            User.validateUserPassword("abcderdajdflskfjlshweirhwjkehrlwkjlwjirhtjklwek");
        } catch (UserExceptionHandler e) {
            assertEquals("password does not exceeds maximum length requirements", e.getMessage());
        }

    }
}






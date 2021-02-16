package login;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    //Test #: 1
    //Test Objective: To create a User Account
    //Inputs: DAName = "tom", password = "a123456"
    //Expected Output: User Object created with id = 0, "tom", DAArea = "a123456"
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

    //Test #: 2.1
    //Test Objective: To catch an incorrect lower boundary value Customer Name
    //Inputs: custName = ""
    //Expected Output: "user Name NOT specified"
    public void testValidateUserName001() {
        try {
            User.validateUserName("");
        } catch (UserExceptionHandler e) {
            assertEquals("user Name NOT specified", e.getMessage());
        }
    }

    //Test #: 2.2
    //Test Objective: To catch an incorrect lower boundary value Customer Name
    //Inputs: custName = "AJ"
    //Expected Output: "user Name does not meet minimum length requirements"
    public void testValidateUserName002() {

        try {
            User.validateUserName("AJ");
        } catch (UserExceptionHandler e) {
            assertEquals("user Name does not meet minimum length requirements", e.getMessage());
        }
    }

    //Test #: 2.3
    //Test Objective: To catch an incorrect higher boundary value Customer Name
    //Inputs: custName = "AJ"
    //Expected Output: "user Name does not exceeds maximum length requirements"
    public void testValidateUserName003() {
        try {
            User.validateUserName("JerrydJerrydJerrydJerrydJerrydJerrydJerryd");
        } catch (UserExceptionHandler e) {
            assertEquals("user Name does not exceeds maximum length requirements", e.getMessage());
        }
    }

    //Test #: 3.1
    //Test Objective: To catch an incorrect lower boundary value  password
    //Inputs: custName = "AJ"
    //Expected Output: "password NOT specified"
    public void testValidatePassword001() {

        try {
            User.validateUserPassword("");
        } catch (UserExceptionHandler e) {
            assertEquals("password NOT specified", e.getMessage());
        }

    }


    //Test #: 3.2
    //Test Objective: To catch an incorrect lower boundary value  password
    //Inputs: custName = "abcde"
    //Expected Output: "password does not meet minimum length requirements"
    public void testValidatePassword002() {
        try {
            User.validateUserPassword("abcde");
        } catch (UserExceptionHandler e) {
            assertEquals("password does not meet minimum length requirements", e.getMessage());
        }
    }



    //Test #: 3.3
    //Test Objective: To catch an incorrect higher boundary value  password
    //Inputs: custName = "abcderdajdflskfjlshweirhwjkehrlwkjlwjirhtjklwek"
    //Expected Output: "password does not exceeds maximum length requirements"
    public void testValidatePassword003() {
        try {
            User.validateUserPassword("abcderdajdflskfjlshweirhwjkehrlwkjlwjirhtjklwek");
        } catch (UserExceptionHandler e) {
            assertEquals("password does not exceeds maximum length requirements", e.getMessage());
        }

    }
}






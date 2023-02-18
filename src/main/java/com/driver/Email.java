package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        boolean containsUpperCase=false;
        boolean containsLowerCase=false;
        boolean containsADigit=false;
        boolean containsSpecialChar=false;
        if(oldPassword.equals(this.password)){

            if(newPassword.length()<8) return; //checks if it contains atleat 8 characters

            for(char c:newPassword.toCharArray()){
                if(c>='A' && c<='Z') containsUpperCase=true;

                if(c>='a' && c<='z') containsLowerCase=true;

                if(c>='0' && c<='9') containsADigit=true;

                if(!(c>='0' && c<='9') && !(c>='a' && c<='z') && !(c>='A' && c<='Z') ) containsSpecialChar=true;
            }

            //all criterials satisfied then change password
            if(containsUpperCase && containsLowerCase && containsADigit && containsSpecialChar){

                this.password=newPassword;
            }
            else return;

        }
    }
}

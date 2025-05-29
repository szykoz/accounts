package com.skoziol.accounts.constants;

public enum AccountsEnum {

    STATUS_200("200"),
    STATUS_201("201"),
    STATUS_500("500"),
    STATUS_417("417"),
    MESSAGE_200("Request processed successfully"),
    MESSAGE_201("Request processed successfully"),
//    MESSAGE_500("An error has occurred. Please try again or contact Dev team"),
    MESSAGE_417_UPDATE("Update operation failed. Please try again or contact Dev team"),
    MESSAGE_417_DELETE("Delete operation failed. Please try again or contact Dev team");

    public final String label;

    AccountsEnum(String label) {
        this.label = label;
    }

    public static AccountsEnum getByValue(String label) {
        for(AccountsEnum e: values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}

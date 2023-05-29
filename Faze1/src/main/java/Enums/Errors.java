package Enums;

public enum Errors {
    USERNAME_FORMAT_ERROR("Username contains\ninvalid characters!"),
    USERNAME_EXIST("Username already exists!")
    ;

    public String getErrorText() {
        return errorText;
    }

    private String errorText;

    Errors(String errorText) {
        this.errorText = errorText;
    }
}

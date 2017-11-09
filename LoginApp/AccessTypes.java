package LoginApp;

public enum AccessTypes {
    Admin, User;

    private AccessTypes() {}

    public String value() {
        return name();
    }

    public static AccessTypes fromValue(String v) {
        return valueOf(v);
    }
}

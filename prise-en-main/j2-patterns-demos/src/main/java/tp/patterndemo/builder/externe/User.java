package tp.patterndemo.builder.externe;

public class User {
    private final String username;
    private final String email;
    private final int age;
    private final String phone;
    private final boolean newsletter;
    private final boolean premium;

    public User(String username, String email, int age, String phone, boolean newsletter, boolean premium) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.newsletter = newsletter;
        this.premium = premium;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", newsletter=" + newsletter +
                ", premium=" + premium +
                '}';
    }
}

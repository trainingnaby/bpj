package tp.patterndemo.builder.externe;

public class UserBuilder {

    private final String username;
    private final String email;

    private int age = 0;
    private String phone = null;
    private boolean newsletter = false;
    private boolean premium = false;

    public UserBuilder(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserBuilder age(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder newsletter(boolean newsletter) {
        this.newsletter = newsletter;
        return this;
    }

    public UserBuilder premium(boolean premium) {
        this.premium = premium;
        return this;
    }

    public User build() {
        return new User(username, email, age, phone, newsletter, premium);
    }
}

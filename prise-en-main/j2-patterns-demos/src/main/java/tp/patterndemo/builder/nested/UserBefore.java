package tp.patterndemo.builder.nested;

public class UserBefore {
	private String username;
	private String email;
	private int age;
	private String phone;
	private boolean newsletter;
	private boolean premium;

	public UserBefore(String username, String email, int age, String phone, boolean newsletter, boolean premium) {
		this.username = username;
		this.email = email;
		this.age = age;
		this.phone = phone;
		this.newsletter = newsletter;
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "User{" + "username='" + username + '\'' + ", email='" + email + '\'' + ", age=" + age + ", phone='"
				+ phone + '\'' + ", newsletter=" + newsletter + ", premium=" + premium + '}';
	}
}

/////// example of usage ///////

//UserAvantBuilder user = new UserAvantBuilder(
//      "john",
//      "john@mail.com",
//      30,
//      null,
//      true,
//      false
//);

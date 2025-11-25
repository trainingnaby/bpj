package tp.patterndemo.builder.nested;

public class UserAfter {
	private String username;
	private String email;
	private int age;
	private String phone;
	private boolean newsletter;
	private boolean premium;

	private UserAfter(Builder builder) {
		this.username = builder.username;
		this.email = builder.email;
		this.age = builder.age;
		this.phone = builder.phone;
		this.newsletter = builder.newsletter;
		this.premium = builder.premium;
	}

	public static class Builder {
		private final String username;
		private final String email;

		private int age = 0;
		private String phone = null;
		private boolean newsletter = false;
		private boolean premium = false;

		public Builder(String username, String email) {
			this.username = username;
			this.email = email;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder newsletter(boolean newsletter) {
			this.newsletter = newsletter;
			return this;
		}

		public Builder premium(boolean premium) {
			this.premium = premium;
			return this;
		}

		public UserAfter build() {
			return new UserAfter(this);
		}
	}

	@Override
	public String toString() {
		return "User{" + "username='" + username + '\'' + ", email='" + email + '\'' + ", age=" + age + ", phone='"
				+ phone + '\'' + ", newsletter=" + newsletter + ", premium=" + premium + '}';
	}
}


///// Exemple d'utilisation /////
/*
 * UserApresBuilder user = new UserAvantBuilder.Builder("john", "john@mail.com") .age(30)
 * .newsletter(true) .build();
 * 
 * System.out.println(user);
 */

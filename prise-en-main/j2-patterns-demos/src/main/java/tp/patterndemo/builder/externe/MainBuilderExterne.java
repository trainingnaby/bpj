package tp.patterndemo.builder.externe;

public class MainBuilderExterne {
	public static void main(String[] args) {
		User user = new UserBuilder("john", "john@mail.com").age(30).newsletter(true).build();

		System.out.println(user);
	}
}

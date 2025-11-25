package tp.patterndemo.builder.nested;

public class MainBuilderNested {
	
	public static void main(String[] args) {
		UserAfter user = new UserAfter.Builder("john", "john@mail.com")
		        .age(30)
		        .newsletter(true)
		        .build();

		System.out.println(user);

	}

}

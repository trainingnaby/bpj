package tp.patterndemo.singleton;

/**
 * Exemples de Singletons plus corrects.
 */
public class GoodSingletonDemo {

	// Singleton via enum (thread-safe, simple)
	public enum AppConfig {
		INSTANCE;

		private String environment = "DEV";

		public String getEnvironment() {
			return environment;
		}

		public void setEnvironment(String environment) {
			this.environment = environment;
		}
	}

	// Singleton classique avec getInstance()
	public static class ClassicSingleton {
		private static final ClassicSingleton INSTANCE = new ClassicSingleton();

		private ClassicSingleton() {
		}

		public static ClassicSingleton getInstance() {
			return INSTANCE;
		}

		public void doSomething() {
			System.out.println("ClassicSingleton doing something...");
		}
	}

	public static void main(String[] args) {
		System.out.println("AppConfig env = " + AppConfig.INSTANCE.getEnvironment());
		AppConfig.INSTANCE.setEnvironment("PROD");
		System.out.println("AppConfig env modifié = " + AppConfig.INSTANCE.getEnvironment());

		ClassicSingleton s1 = ClassicSingleton.getInstance();
		ClassicSingleton s2 = ClassicSingleton.getInstance();
		System.out.println("s1 == s2 ? " + (s1 == s2));
		s1.doSomething();

		System.out.println("""
				Idées clés :
				- enum Singleton garantit une seule instance, thread-safe.
				- ClassicSingleton contrôle la création via un constructeur privé.
				- Cela reste un global, à utiliser avec parcimonie, idéalement injecté.
				""");
	}
}

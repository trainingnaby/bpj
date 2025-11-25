package tp.patterndemo.factory;

import java.util.List;

/**
 * Version avec une simple Factory centralisée.
 */
public class SimpleFactoryDemo {

	interface Notification {
		void send(String message);
	}

	static class EmailNotification implements Notification {
		@Override
		public void send(String message) {
			System.out.println("[EMAIL] " + message);
		}
	}

	static class SmsNotification implements Notification {
		@Override
		public void send(String message) {
			System.out.println("[SMS] " + message);
		}
	}

	static class NotificationFactory {
		static Notification create(String channel) {
			return switch (channel) {
			case "EMAIL" -> new EmailNotification();
			case "SMS" -> new SmsNotification();
			default -> throw new IllegalArgumentException("Type inconnu : " + channel);
			};
		}
	}

	public static void main(String[] args) {
		List<String> channels = List.of("EMAIL", "SMS", "EMAIL");

		for (String channel : channels) {
			Notification notif = NotificationFactory.create(channel);
			notif.send("Bienvenue sur Room4U (factory) !");
		}

		System.out.println("""
				Bénéfices :
				- La logique de création est centralisée dans NotificationFactory.
				- Le code appelant ne connaît que l'interface Notification.
				- Pour ajouter PUSH, on modifie la factory et on crée une nouvelle classe.
				""");
	}
}

package tp.patterndemo.factory;

import java.util.List;

/**
 * Anti-pattern : création d'objets dispersée dans le code, avec if/else sur des
 * chaînes.
 */
public class NoFactoryAntiPatternDemo {

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

	public static void main(String[] args) {
		// Imaginons qu'on lise ces "types" depuis une config
		List<String> channels = List.of("EMAIL", "SMS", "EMAIL");

		for (String channel : channels) {
			Notification notif;
			// Choix du type concret dispersé ici
			if ("EMAIL".equals(channel)) {
				notif = new EmailNotification();
			} else if ("SMS".equals(channel)) {
				notif = new SmsNotification();
			} else {
				throw new IllegalArgumentException("Type inconnu : " + channel);
			}

			notif.send("Bienvenue sur Room4U !");
		}

		System.out.println("""
				Problèmes :
				- Si on ajoute PUSH, il faut modifier toutes les boucles de ce genre.
				- Le new EmailNotification() est dupliqué partout.
				- Difficulté à tester/enrichir.
				""");
	}
}

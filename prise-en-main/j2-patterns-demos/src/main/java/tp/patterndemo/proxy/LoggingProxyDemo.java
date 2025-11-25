package tp.patterndemo.proxy;

/**
 * Exemple de Proxy pour ajouter du logging autour d'un service.
 */
public class LoggingProxyDemo {

	interface DocumentService {
		void saveDocument(String content);
	}

	static class RealDocumentService implements DocumentService {
		@Override
		public void saveDocument(String content) {
			System.out.println("Sauvegarde du document : " + content);
		}
	}

	static class LoggingDocumentServiceProxy implements DocumentService {
		private final DocumentService delegate;

		LoggingDocumentServiceProxy(DocumentService delegate) {
			this.delegate = delegate;
		}

		@Override
		public void saveDocument(String content) {
			System.out.println("[LOG] Avant saveDocument");
			delegate.saveDocument(content);
			System.out.println("[LOG] Après saveDocument");
		}
	}

	public static void main(String[] args) {
		DocumentService real = new RealDocumentService();
		DocumentService proxy = new LoggingDocumentServiceProxy(real);

		proxy.saveDocument("Contrat Room4U via Proxy");

		System.out.println("""
				Idée :
				- On ajoute du logging sans modifier RealDocumentService.
				- On pourrait faire un SecurityProxy, CachingProxy, etc.
				""");
	}
}

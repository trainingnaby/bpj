package tp.patterndemo.proxy;

/**
 * Service appelé directement, sans comportement transverse.
 */
public class DirectServiceDemo {

    interface DocumentService {
        void saveDocument(String content);
    }

    static class RealDocumentService implements DocumentService {
        @Override
        public void saveDocument(String content) {
            System.out.println("Sauvegarde du document : " + content);
        }
    }

    public static void main(String[] args) {
        DocumentService service = new RealDocumentService();
        service.saveDocument("Contrat Room4U");
    }
}

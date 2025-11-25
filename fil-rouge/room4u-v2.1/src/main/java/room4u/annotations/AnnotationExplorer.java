package room4u.annotations;

import java.lang.reflect.Method;

/**
 * Utilitaire pour explorer les annotations définies sur les services et repositories.
 */
public final class AnnotationExplorer {

    private AnnotationExplorer() {
    }

    public static void printUseCases(Class<?>... serviceTypes) {
        System.out.println("Use cases exposés :");
        for (Class<?> type : serviceTypes) {
            for (Method method : type.getMethods()) {
                UseCase useCase = method.getAnnotation(UseCase.class);
                if (useCase != null) {
                    System.out.println("- " + type.getName() + "." + method.getName()
                            + " : " + useCase.value());
                }
            }
        }
        System.out.println();
    }

    public static void printRepositories(Class<?>... repositoryTypes) {
        System.out.println("Repositories détectés :");
        for (Class<?> type : repositoryTypes) {
            RepositoryComponent comp = type.getAnnotation(RepositoryComponent.class);
            if (comp != null) {
                System.out.println("- " + type.getName() + " [" + comp.value() + "]");
            }
        }
        System.out.println();
    }
}


# TP: De Threads à CompletableFuture (Java)

Projet Maven importable Eclipse. Cette version met l'accent sur **un Executor dédié** dans la démo CFBasics.

## Import
Eclipse → File → Import → Existing Maven Projects → sélectionner le dossier.

## Lancer
Chaque classe possède un `main(...)`. Lancez-les dans l'ordre du package.

## Points clés
- Threads bruts → synchronized / wait-notify
- ExecutorService → Future → invokeAll/invokeAny → Scheduled
- CompletableFuture (avec **Executor dédié** dans `CFBasics`), composition, combinaison, erreurs

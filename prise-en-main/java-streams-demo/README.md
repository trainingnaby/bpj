# Java Streams Demo

Projet **Maven** importable dans **Eclipse** avec des exemples clairs et complets sur les **Streams Java** : opérations intermédiaires (`map`, `flatMap`, `filter`, `distinct`, `sorted`, `peek`), opérations terminales (`collect`, `reduce`, `forEach`) et **collecteurs** (`groupingBy`, `partitioningBy`, `toMap`, `joining`, `summarizingInt`, etc.).

## Prérequis
- JDK 17 (ou supérieur)
- Maven 3.8+
- Eclipse (Import > Existing Maven Projects)

## Import dans Eclipse
1. `File` → `Import...` → `Existing Maven Projects`.
2. Sélectionnez le dossier `java-streams-demo` (racine du projet).
3. Finir l’assistant. Eclipse résout automatiquement les dépendances.

## Exécution
Plusieurs classes **avec `main`** démontrent chaque concept. Vous pouvez :
- Lancer depuis Eclipse (clic droit → Run As → Java Application) la classe **`App`** OU une des classes `demo.*`.
- Ou via Maven :
```bash
mvn -q -DskipTests exec:java
```
(la classe par défaut est `com.example.streams.App`).

## Contenu
- `model/Person.java` — Modèle simple utilisé dans les exemples.
- `data/SampleData.java` — Jeu de données de démonstration.
- `App.java` — Orchestrateur qui appelle plusieurs démos.
- `demo/IntermediateOpsDemo.java` — `map`, `filter`, `flatMap`, `distinct`, `sorted`, `peek`.
- `demo/CollectorsDemo.java` — `collect`, `toList`, `toSet`, `toMap`, `groupingBy`, `partitioningBy`, `joining`, `summarizingInt`.
- `demo/ReduceDemo.java` — `reduce` (somme, max, fusion d’objets).
- `demo/PrimitiveStreamsDemo.java` — `IntStream`, `mapToInt`, `range`, `average`, `sum`.
- `demo/ParallelStreamsDemo.java` — Exemple simple et remarques de bonnes pratiques.

Chaque classe affiche sur la console ce qu’elle fait, avec des commentaires en français.

## Notes pédagogiques
- Les **opérations intermédiaires** sont **paresseuses** (lazy) : rien n’est exécuté tant qu’une opération **terminale** n’est pas appelée.
- Préférez les **collecteurs** pour regrouper et agréger plutôt que de muter des structures partagées.
- Les **parallel streams** peuvent accélérer des calculs CPU-intensifs, mais attention aux **mutations** et à l'**ordre**.

Bonne démo !

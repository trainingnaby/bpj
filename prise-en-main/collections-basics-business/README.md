# Collections Basics – Domaines métiers simples (Java 17 + Maven)

Ce projet montre des **cas ultra concrets** de tous les jours, en se concentrant sur :
- **List** : ordre conservé, doublons permis (liste d’articles, to‑do, parcours).
- **Set** : unicité, pas de doublons (emails uniques, présences).
- **Deque** avec `ArrayDeque` : 
  - **FIFO** (file d’attente) : `offerLast` / `pollFirst`.
  - **LIFO** (pile / undo) : `push` / `pop` ou `addFirst` / `removeFirst`.

## Lancer
```bash
mvn -q test
mvn -q -DskipTests package
java -cp target/collections-basics-business-1.0.0.jar com.example.basics.Main
```

## Scénarios inclus

`ShoppingCart` | `ArrayList` | Panier d’articles où l’ordre d’ajout compte, doublons possibles

`TodoList` | `ArrayList` | Liste priorisée, réordonnable, itérations fréquentes 
 
`NewsletterRecipients` | `HashSet` | Supprimer les doublons d’emails avant envoi

`Attendance` | `HashSet` | Savoir rapidement si une personne est présente 

`BackNavigation` | `ArrayDeque` (LIFO) | Navigation “retour en arrière” type navigateur

`PrintQueue` | `ArrayDeque` (FIFO) | File d’impressions : premier arrivé, premier servi

`RecentlyViewed` | `ArrayDeque` (Deque bornée) | Derniers produits consultés (ajout en tête, coupe en queue)

`DeliveryRoute` | `ArrayDeque` (Deque) | Ajouter/retirer des arrêts en tête/queue facilement

> Les classes sont simples, 100% commentées et testées.

## Rappels pratiques
- `ArrayList` : itérations rapides, `get(i)` en O(1), insertions au milieu plus coûteuses.
- `HashSet` : pas d’ordre, test d’appartenance très rapide.
- `ArrayDeque` : plus rapide que `Stack`/`LinkedList` pour pile/file, n’accepte pas `null`.

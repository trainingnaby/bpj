# TP démo – Optional avant / après (Version impérative + Version fluent)

Ce projet sert de support à un TP en 2 temps :

1. Partir du code **legacy** (null, if/else) pour réécrire une version
   **avec Optional mais en style impératif** (sans `map` / `flatMap`).
2. Refactorer ensuite cette version impérative vers une version
   **"fluent"** utilisant `map` / `flatMap` / `ifPresentOrElse` pour
   montrer l'intérêt du style déclaratif.

## Packages

- `tp.optional.legacy`
  - Code "avant Optional" : `null` pour dire "pas trouvé" ou "ville inconnue".
- `tp.optional.modern.imperative`
  - Version corrigée *avec Optional* mais **sans** `map` / `flatMap` :
    on utilise `isPresent()`, `isEmpty()`, `get()`, etc.
- `tp.optional.modern.fluent`
  - Version corrigée *avec Optional* en style **fluent** :
    `map`, `flatMap`, `orElse`, `ifPresentOrElse`, etc.
- `tp.optional.showcase`
  - Petite classe `OptionalShowcase` démontrant l'API Optional.

## Consigne d'exercice

### Version 1 – Réécrire les méthodes sans map / flatMap

1. Étudiez le code de `tp.optional.legacy` :
   - `LegacyUser`
   - `LegacyUserRepository`
   - `LegacyUserService`
   - `LegacyOptionalDemoMain`

2. Identifiez les problèmes :
   - Retour de `null` pour signaler "pas trouvé".
   - Champ `city` qui peut être `null`.
   - Ambiguïté de la méthode `getUserCityUppercase` :
     - `null` = utilisateur inexistant ?
     - `"INCONNUE"` = ville non renseignée ?
   - Multiplication des `if (x != null)`.

3. Dans le package `tp.optional.modern.imperative` :
   - Reprenez les classes `User`, `UserRepository`, `UserService`.
   - Réécrivez les méthodes de `UserService` pour :
     - ne plus exposer de `null`,
     - utiliser `Optional` pour exprimer l'absence,
     - **sans utiliser `map` / `flatMap`** :
       - utilisez plutôt `isPresent()`, `isEmpty()`, `get()`, etc.
   - Faites en sorte que `ImperativeOptionalDemoMain` affiche un
     comportement fonctionnel équivalent, mais **sans `null` en sortie**.

4. Vérifiez :
   - Les signatures expriment-elles clairement l'absence possible ?
   - Les `null` ont-ils disparu des signatures publiques ?
   - Avez-vous encore des cas ambigus pour l'appelant ?

### Version 2 – Refactorer en style fluent avec map / flatMap

1. Dans le package `tp.optional.modern.fluent` :
   - Prenez la même logique métier que dans la version impérative.
   - Réécrivez les méthodes de `UserService` en style *fluent* :
     - `map`, `flatMap`, `filter`, `orElse`, `orElseThrow`,
       `ifPresentOrElse`, etc.
   - Conservez les mêmes signatures publiques pour comparer les
     implémentations.

2. Comparez avec les stagiaires :
   - Qu'est-ce qui est plus lisible ?
   - Quelles parties du code gagnent à être écrites en style `map/flatMap` ?
   - Quels sont les endroits où le style impératif reste plus lisible ?

3. Servez-vous de `OptionalShowcase` (package `tp.optional.showcase`)
   pour illustrer les différentes méthodes sur des exemples très simples.

## Classes à lancer

- Avant Optional :
  - `tp.optional.legacy.LegacyOptionalDemoMain`
- Version impérative :
  - `tp.optional.modern.imperative.ImperativeOptionalDemoMain`
- Version fluent :
  - `tp.optional.modern.fluent.FluentOptionalDemoMain`
- API Optional :
  - `tp.optional.showcase.OptionalShowcase`

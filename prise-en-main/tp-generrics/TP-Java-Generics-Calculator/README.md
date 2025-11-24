# TP — Généricité en Java : Calculatrice générique

## Objectifs
- Manipuler les **génériques** (types paramétrés) en Java.
- Concevoir une **API générique** qui sépare les opérations numériques du type de données.
- Implémenter une **calculatrice générique** `Calculator<T>` réutilisable pour `Integer`, `Double`, `BigDecimal`, etc.

## Import / Build
- Eclipse : *File → Import… → Existing Maven Projects*.
- CLI : `mvn -q -DskipTests package` puis exécutez les `main` des classes.

## Étapes du TP
1) Compléter l'interface `NumericOps<T>` (opérations arithmétiques).
2) Implémenter `GenericCalculator<T>` en s'appuyant sur `NumericOps<T>`.
3) Fournir au moins deux implémentations : `IntegerOps` et `BigDecimalOps`.
4) Tester dans `MainTP` avec plusieurs types.
5) (Bonus) Ajouter `DoubleOps`, gestion des divisions par zéro, et une méthode `pow`.


# TP Démos – Jour 2 (Matin) – Patterns

Ce projet regroupe des **mini-démos** pour introduire les patterns suivants
avant le TP fil rouge Room4U :

- Factory
- Singleton
- Strategy
- Proxy
- Prototype

Chaque demo est dans `tp.patterndemo.<pattern>` avec une classe `main` à lancer
depuis Eclipse pour illustrer le problème puis la solution.

## 1. Factory – `tp.patterndemo.factory`

### `NoFactoryAntiPatternDemo`

Montre un code où le choix du type concret (`EmailNotification`, `SmsNotification`)
est dispersé partout dans le code, avec des `if/else` sur des chaînes.

**Problèmes** :

- duplication du `new` et des `if`,
- difficile d'ajouter un nouveau type,
- couplage fort au concret.

### `SimpleFactoryDemo`

Introduit :

- une interface `Notification`,
- des implémentations concrètes,
- une `NotificationFactory` centralise la création.

**Idées clés** :

- meilleure lisibilité,
- évolution plus simple (ajout de `PushNotification`),
- isolement de la logique de création.

---

## 2. Singleton – `tp.patterndemo.singleton`

### `BadSingletonDemo`

Montre un "singleton" naïf :

- champ `static` public,
- mutable,
- pas thread-safe.

**Objectif** : montrer que global = difficile à tester, à remplacer, et dangereux en multi-thread.

### `GoodSingletonDemo`

Montre :

- un singleton via `enum` (`AppConfig.INSTANCE`),
- ou via `private constructor + static getInstance()`.

**Idées clés** :

- contrôle de l'instance,
- thread-safety,
- plus facile à injecter/mock dans des tests qu'un champ global exposé.

---

## 3. Strategy – `tp.patterndemo.strategy`

### `IfElsePricingDemo`

Implémente des règles de prix différentes avec un `if/else` géant sur un type de client :

- `STANDARD`, `PREMIUM`, `VIP`…

**Problèmes** :

- méthode longue et fragile,
- difficile à tester et faire évoluer (nouveau type = modifier ce gros `if`).

### `StrategyPricingDemo`

Introduit :

- une interface `PricingStrategy`,
- des implémentations (Standard, Premium, Vip),
- un contexte `PriceCalculator` qui délègue à la stratégie.

**Idées clés** :

- chaque règle dans sa classe,
- remplacement ou ajout de stratégies sans toucher aux autres,
- meilleur support des tests unitaires.

---

## 4. Proxy – `tp.patterndemo.proxy`

### `DirectServiceDemo`

Un service `DocumentService` qui enregistre des documents :

- accès direct,
- pas de logging, pas de contrôle d'accès.

### `LoggingProxyDemo`

Introduit :

- une interface `DocumentService`,
- une implémentation réelle `RealDocumentService`,
- un `LoggingDocumentServiceProxy` qui :
  - loggue avant/après l'appel,
  - délègue à la vraie implémentation.

**Idées clés** :

- ajouter des comportements transverses (logging, sécurité, cache)
  **sans modifier** le service réel.

---

## 5. Prototype – `tp.patterndemo.prototype`

### `ShallowCopyDemo`

Montre qu'une copie "superficielle" (ou un clone naïf) d'un objet contenant
une liste mutable partage cette même liste :

- modification de la liste dans la copie affecte l'original.

### `DeepCopyDemo`

Montre une copie profonde :

- nouvel objet,
- nouvelle liste copiée,
- modification de la copie n'affecte plus l'original.

**Idées clés** :

- comprendre ce que signifie "cloner" un objet,
- quand le pattern Prototype est utile (dupliquer des templates d'objets, etc.).

---

## Utilisation en formation

1. Importer le projet dans Eclipse :
   - `File` → `Import...` → `Existing Maven Projects` → sélectionner ce dossier.
2. Ouvrir les classes `...Demo` dans :
   - `tp.patterndemo.factory`
   - `tp.patterndemo.singleton`
   - `tp.patterndemo.strategy`
   - `tp.patterndemo.proxy`
   - `tp.patterndemo.prototype`
3. Pour chaque demo :
   - demander aux stagiaires de repérer les problèmes dans la version "Bad",
   - exécuter le `main`,
   - comparer avec la version "Good" en discutant des bénéfices.
4. Enchaîner ensuite avec le TP Room4U correspondant à chaque pattern.

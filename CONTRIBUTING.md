# Charte de développement - BlumBat

Merci beaucoup de vouloir contribuer à BlumBat !

Veuillez suivre cette charte de développement afin de pouvoir produire un code plus consistant et conforme aux exigences du projet. Cela permettra aux mainteneurs de passer moins de temps à refaire votre code, et ça leur donnera plus de temps pour comprendre votre problème.

## Règles du projet

- Soyez respectueux envers les autres contributeurs.
- Créez de petites fonctionnalités (ex : _créer un locataire_ plutôt que _gérer les locataires_)
- N'ajoutez pas de nouveaux modèles ou de nouvelles tables si cela n'est pas nécessaire.

## Cycle de développement d'une fonctionnalité

- Créez une branche pour chaque fonctionnalité que vous développez. Cela permettra de séparer les différentes fonctionnalités et de faciliter la fusion des branches.
- Écrivez des tests dans `[NomDuModèle]Test.java` pour chaque nouvelle fonctionnalité que vous ajoutez (et testez-les).
- Une fois votre code pour cette fonctionnalité terminée, créez une merge request pour demander la fusion de votre branche dans la branche principale.
  - La merge request doit comprendre un nom qui explique clairement la fonctionnalité ajoutée, ainsi qu'une description qui comprend un résumé des changements techniques (ajout de contrôleurs et de vues, changement du modèle, etc.)
  - Tous les tests, y compris ceux que vous n'avez pas écrit, doivent passer.
  - La merge request doit être approuvée par au moins un autre contributeur avant d'être fusionnée.

## Conventions de nommage

Le code doit être écrit en français dès que possible.

### Java

La plupart des exemples ici sont dérivés des [Java Code Conventions](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf).

- Les noms de classes doivent être en PascalCase.
- Les noms de méthodes et de variables doivent être en camelCase.
- Les noms de constantes doivent être en MAJUSCULES (ex : `DATABASE_URL`).

### Git

- Les noms de branches doivent être en `kebab-case` (ex : `creer-locataire`).
- Les commits doivent suivre les [Conventional commits](https://www.conventionalcommits.org/en/v1.0.0/) (ex : `feat: ajout d'une fonctionallité`, `fix: correction d'un bug`).
- Les messages de commit doivent être en français.

# Nexus

Nexus est l'API **officielle** du studio de jeux vidéos 
**Legendary Games Studio**.
Vous n'avez pas besoin de connexion ou de clef pour y accéder.

Toutes les requêtes sont fait en `GET` (les arguments sont dans l'url).

Les résultats des requêtes sont au format `JSON`.

**Chemin de base de l'API:**  [https://lgs-nexus.000webhostapp.com/](https://lgs-nexus.000webhostapp.com/)

**Documentation officielle:**  [https://lgsnexus.docs.apiary.io/](https://lgsnexus.docs.apiary.io/)

**Version de l'API:** `version 0.1 (alpha)`

# Information

## Fréquence des requêtes et limites
Limite journalière: **Nombre infini de requêtes**

- **Nombre infini de requêtes** / minute
- **Nombre infini de requêtes** / second

Note: merci d'attendre 3 secondes entre deux requêtes.

## Cache

Un cache, est une sauvegarde du résultat la requête.

Pour l'instant, aucune requête n'est gardée en cache.

# Codes de réponse

Après l'exécution d'une requête, le serveur va vous retourner l'un
des codes HTML suivants :

- 200 `OK` - Requête réussie.
- 400 `Bad Request` - La requête est invalide.
- 404 `Not Found` - La ressource n'a pas étée trouvée.
- 405 `Method Not Allowed` - Si cette action ne peux pas être réalisée sur votre ressource.
- 429 `Too Many Requests` - Trop de requêtes
- 500 `Internal Server Error` - Problème au niveau du serveur

# Format des messages d'erreurs

```json
{
    "status": 404,
    "type": "Not Found",
    "message": "La ressource n'existe pas.",
    "help": "For usage, please see: ..."
}
```

Property        | Remarks
|-------------- | -------- |
| `status`                            | Code de réponse du serveur |
| `type`                              | Valeur du code de réponse |
|`message`                           | Message d'erreur |
 |`help`                              | Une aide possible |
 
# Fonctionnement général

L'API permet l'accès dans un premier temps aux jeux, puis pour chaque jeux,
il est possible d'accéder à des catégories.

Games        | Catégorie(s)
|-------------- | -------- |
| `editor`   | news |


## Enigma

Enigma est l'un des jeux de Legendary Games Studio. Il s'agit d'un escape
game, dans lequel un ou plusieurs joueurs cherchent à s'échapper un lieu
en résolvant des énigmes.

**Endpoint:** `/enigma/{category}/`

| Category | Request | Description |
| ------------- | ------------- | ------------- |
| `aucune` | /enigma/ |Informations générales sur le jeu |
| `news` | /enigma/news/ | Permet de consulter les actualités du jeu |
            
## I. Aucune catégorie

Affiche les informations de base du jeu énigma

**Endpoint:** `/enigma` ou `/enigma/`

```json
{
    "name": "Enigma",
    "id": "1",
    "version": "1.0"
}
```

## II. News

Affiche les actualités du jeu Enigma.

**Endpoint:** `/enigma/news(?request)`

Request prends le format d'une requête GET typique : `?parameter1=valeur&parameter2=valeur&...`

| Parameter | Type | Description |
| ------------- | ------------- |------------- |
| `id` | int | ID d'une news |
| `offset` | int | Offset dans le tableau des news supplémentaires |
| `limit` | int | Nombre de news supplémentaires |

### Format de la réponse

```jsmin
{
   "result":  [
        { /* news */},
        ...
   ],
   "offset": "",
   "limit": "",
   "total": ""
}
```

| Parameter | Type | Description |
| ------------- | ------------- |------------- |
| `result` | array | Tableau des actualités, des plus récéntes aux plus anciennes |
| `offset` | int | Le décalage actuel |
| `limit` | int | La limite actuelle |
| `total` | int | Le nombre total de news |

### Une News

Il est possible d'explorer une news, si l'on connait son id.

**Endpoint:** `/enigma/news/{id}`

| Parameter | Type | Description |
| ------------- | ------------- |------------- |
| `title` | String | Titre de la news |
| `sub-title` | String | Sous-titre de la news |
| `id` | int | ID de la news |
| `game` | String | le jeu concerné par la news |
| `img` | String | URL de l'image de la news |
| `release` | Date | Date de publication |
| `content` | String | Contenu de la news, syntaxe MARKDOWN |
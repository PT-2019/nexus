# **NEXUS WRAPPER**


Wrapper java **officiel** de l'api **Nexus** du studio de jeux vidéos **Legendary Game Studio**.

_**Documentation :**_ [docs](docs/allclasses-index.html)

_**Version de l'api :**_ _1.0_


# **NEXUS**


Nexus est l'API **officielle** du studio de jeux vidéos **Legendary Games Studio**. Vous n'avez pas besoin de connexion ou de clef pour y accéder.

Toutes les requêtes sont à faire en `GET` (les arguments sont dans l'url).

Les résultats des requêtes sont au format `JSON`.

**_Chemin de base de l'API:_** https://lgs-nexus.000webhostapp.com/

_**Documentation**_ : https://lgsnexus.docs.apiary.io/

**_Version de l'API:_** version 0.1 (alpha)

# **STRUCTURE**

**La classe Nexus** est le controlleur **principal** du wrapper. 
Elle est parente de 3 objets (UrlBuilder, NexusRequest, Parser) qui permettent de communiqué avec l'api rest Nexus et de parser la réponse obtenue directement 
en objet Java. Cette classe met à disposition les méthodes principales pour récupérer les données de l'api rest. Vous pouvez néanmoins créé vos 
propres méthodes grâce à 3 objets :


###### **Construire son URL**

L'objet UrlBuilder permet de construire des URL facilement. 
Il permet de rajouter des paramétres au chemin de base de l'api (https://lgs-nexus.000webhostapp.com/). 

_Exemple d'utilisation :_

    public static void main(String[] args){
    
        Urlbuilder myurl = new UrlBuilder();
        System.out.println(myurl.search("enigma-editor");
        /* => Output expected : https://lgs-nexus.000webhostapp.com/enigma-editor */
        
        Hashmap<String,String> parameter = new Hashmap<>();
        paramater.put("id,"31");
        System.out.println(myurl.addParamater(myurl.search("enigma-editor/news"),parameter));
        /* => Output expected : https://lgs-nexus.000webhostapp.com/enigma-editor/news?id=31 */
        
    }
    
    
    
    
###### **Préparer une requête**

L'objet NexusRequest permet de communiquer directement avec l'api rest Nexus.
 Pour préparer une requête `GET`, il ne faut que mettre l'url cible en argument.
 Pour récupérer la réponse du serveur, il faut invoqué la méthode `readResponse`.

_Exemple d'utilisation :_

    public static void main(String[] args){
    
        UrlBuilder myurl = new UrlBuilder();
        NexusRequest r = new NexusRequest();
        
        r.prepareGetRequest(myurl.search("enigma-editor"));
        String response = request.readResponse();
        System.out.println(response);
        
        /* => Output expected : {"result":[{"name":"enigma-editor","id":2,"version":"v1.0"}]} */
        
    }
       
        
        
###### **Parser sa requête**

    
  L'objet Parser contient 2 méthodes que vous pouvez appeler directement `Parser.method()` :
  
  `getListCategory(String response, Class<T> cat)`
  `getInformationCategory(String response, T cat)`
  
  _Exemple d'utilisation :_
  
    public static void main(String[] args){
        
            UrlBuilder myurl = new UrlBuilder();
            NexusRequest r = new NexusRequest();
            r.prepareGetRequest(myurl.search("enigma-editor/news"));
            String response = request.readResponse();
            News myNew = Parser.getInformationCategory(r, New News());
                   
    }
           
## configuration du plugin tomcat7-maven-plugin
TODO ajouter la config du tomcat
il faut ajouter dans le fichier ~/.m2/settings.xml la balise suivante : 
```
<servers>
    <server>
      <id>TomcatServer</id>
      <username>user1</username> <!-- le nom de l'utilisateur qui a les droits manager -->
      <password>pass</password> <!-- le mot de passe -->
    </server>
</servers>
```
## configuration du plugin cargo-maven3-plugin
il faut ajouter dans le fichier ~/.m2/settings.xml la balise suivante : 
```
  <pluginGroups>
    <!-- ... -->
    <pluginGroup>org.codehaus.cargo</pluginGroup>
    <!-- ... -->
  </pluginGroups>
```

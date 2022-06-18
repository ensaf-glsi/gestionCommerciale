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

## Démarrer sonarqube
```bash
docker-compose -f src/main/docker/sonar/docker-compose.yml up
```

## Démarrer mysql
```bash
docker-compose -f src/main/docker/sonar/docker-compose.yml up
```

## scan project
```
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=gestion-commerciale \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=b20d3ac972a24024562d1ca8525aab662f8720cb
```

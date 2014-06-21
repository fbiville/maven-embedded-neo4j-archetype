# Maven embedded Neo4j archetype

This archetype will allow you to start right away using [Neo4j](http://www.neo4j.org) in embedded mode
for your Java projects.

## From Maven Central

```
 $ mvn archetype:generate -Dfilter=maven-embedded-neo4j-archetype
```

## From the sources

```
 $ (maven-embedded-neo4j-archetype) > mvn install
 $ (maven-embedded-neo4j-archetype) > mkdir /path/to/new/project
 $ (project) > mvn archetype:generate -DarchetypeCatalog=local
[INFO] Scanning for projects...
[INFO] 
[INFO] (...) your display may slightly vary (...)
[INFO]
[INFO] Generating project in Interactive mode
[INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
Choose archetype:
1: local -> net.biville.florent:maven-embedded-neo4j-archetype-archetype (maven-embedded-neo4j-archetype-archetype)
[INFO] (...) type 1 and go on (...)
```

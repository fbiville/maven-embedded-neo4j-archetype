#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.io.IOException;

import static java.nio.file.Files.createTempDirectory;
import static org.neo4j.graphdb.RelationshipType.withName;
import static org.neo4j.graphdb.PathExpanders.forTypeAndDirection;
import static org.neo4j.graphdb.traversal.Evaluators.toDepth;

public class Neo4jEmbeddedExample {

    private final GraphDatabaseService graphDatabaseService;

    public Neo4jEmbeddedExample(GraphDatabaseService graphDatabaseService) {
        this.graphDatabaseService = graphDatabaseService;
        dumpData();
    }

    public String helloWorldTraversal() {
        StringBuilder values = new StringBuilder();
        try (Transaction tx = graphDatabaseService.beginTx()) {
            Node startNode = graphDatabaseService.findNode(Label.label("Word"), "value", "Hello");
            for (Node node : graphDatabaseService.traversalDescription()
                    .depthFirst()
                    .expand(forTypeAndDirection(withName("IS_FOLLOWED_BY"), Direction.OUTGOING))
                    .evaluator(toDepth(2))
                    .traverse(startNode)
                    .nodes()) {

                values.append(String.valueOf(node.getProperty("value")));
                values.append(" ");
            }
            tx.success();
        }
        return values.substring(0, values.length() - 1);
    }

    private final void dumpData() {
        try (Transaction tx = graphDatabaseService.beginTx()) {
            String cql = "CREATE (word:Word {value:'Hello'})-[:IS_FOLLOWED_BY]->(word2:Word {value:'world'})";
            graphDatabaseService.execute(cql);
            tx.success();
        }
    }

    public static void main(String[] args) throws IOException {
        File neo4jPath = createTempDirectory("neo").toFile();

        System.out.printf("--- Starting embedded neo4j in %s%n", neo4jPath.getPath());

        GraphDatabaseService graphDB = new GraphDatabaseFactory()
                .newEmbeddedDatabaseBuilder(neo4jPath)
                .newGraphDatabase();

        Neo4jEmbeddedExample embeddedNeoExampleRepository = new Neo4jEmbeddedExample(graphDB);

        System.out.printf("%s :)%n", embeddedNeoExampleRepository.helloWorldTraversal());

    }
}

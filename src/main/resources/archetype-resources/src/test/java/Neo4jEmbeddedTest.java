#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.test.EmbeddedDatabaseRule;

import static org.assertj.core.api.Assertions.assertThat;

public class Neo4jEmbeddedTest {

    @Rule
    public EmbeddedDatabaseRule embeddedDatabaseRule = new EmbeddedDatabaseRule();

    private Neo4jEmbeddedExample neo4jEmbeddedExample;

    @Before
    public void retrieves_service() {
        neo4jEmbeddedExample = new Neo4jEmbeddedExample(embeddedDatabaseRule.getGraphDatabaseService());
    }

    @Test
    public void says_hello_world() {
        String helloWorld = neo4jEmbeddedExample.helloWorldTraversal();

        assertThat(helloWorld).isEqualTo("Hello world");
    }
}

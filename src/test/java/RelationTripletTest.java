import extract.RelationTriplets;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * Created by user on 13/7/2016.
 */
public class RelationTripletTest {

    @Test
    public void iterateWithoutFailing() {
        MatcherAssert.assertThat("can get a non-empty iterator",
                new RelationTriplets("Obama was born in Hawaii. He is our president.").iterator().next(),
                Matchers.anything()
        );
    }

    @Test
    public void getSubject() {
        MatcherAssert.assertThat("can get subject of first item",
                new RelationTriplets("Obama was born in Hawaii. He is our president.").iterator().next().subjectLemmaGloss(),
                Matchers.equalTo("Obama")
        );
    }

    @Test
    public void getObject() {
        MatcherAssert.assertThat("can get object of first item",
                new RelationTriplets("Obama was born in Hawaii. He is our president.").iterator().next().objectLemmaGloss(),
                Matchers.equalTo("Hawaii")
        );
    }

    @Test
    public void getRelation() {
        MatcherAssert.assertThat("can get relation of first item",
                new RelationTriplets("Obama was born in Hawaii. He is our president.").iterator().next().relationLemmaGloss(),
                Matchers.containsString("bear")
        );
    }
}

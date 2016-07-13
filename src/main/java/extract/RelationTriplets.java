package extract;

import java.util.*;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.naturalli.OpenIE;
import edu.stanford.nlp.naturalli.SentenceFragment;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;


/**
 * Created by user on 9/7/2016.
 */
public class RelationTriplets implements Iterable<RelationTriple > {

    private final String text;
    public RelationTriplets(String txt) {
        text = txt;
    }

    public Iterator<RelationTriple> iterator() {
        // Create the Stanford CoreNLP pipeline
        Properties props = PropertiesUtils.asProperties(
                "annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie"
                // , "depparse.model", "edu/stanford/nlp/models/parser/nndep/english_SD.gz"
                // "annotators", "tokenize,ssplit,pos,lemma,parse,natlog,openie"
                // , "parse.originalDependencies", "true"
        );
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);

        List<RelationTriple> triples = new ArrayList<>();
        for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) {
            triples.addAll(sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class));
        }

        return triples.iterator();
    }
}

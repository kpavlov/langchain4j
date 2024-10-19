package dev.langchain4j.rag.content;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.rag.content.aggregator.ContentAggregator;
import dev.langchain4j.rag.content.injector.ContentInjector;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;

import static dev.langchain4j.internal.ValidationUtils.ensureNotNull;

/**
 * Represents content relevant to a user {@link Query} with the potential to enhance and ground the LLM's response.
 * <br>
 * Currently, it is limited to text content (i.e., {@link TextSegment}),
 * but future expansions may include support for other modalities (e.g., images, audio, video, etc.).
 *
 * @see ContentRetriever
 * @see ContentAggregator
 * @see ContentInjector
 */
public interface Content {

    TextSegment textSegment();

    static Content from(String text) {
        return new TextContent(text);
    }

    static Content from(TextSegment textSegment) {
        return new TextContent(textSegment);
    }

    record TextContent(
            TextSegment textSegment
    ) implements Content {

        public TextContent(TextSegment textSegment) {
            this.textSegment = ensureNotNull(textSegment, "textSegment");
        }

        public TextContent(String text) {
            this(TextSegment.from(text));
        }

        @Override
        public String toString() {
            return "Content {" +
                    " textSegment = " + textSegment +
                    " }";
        }
    }
}

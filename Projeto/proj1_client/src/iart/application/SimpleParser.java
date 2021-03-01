package iart.application;

import javax.swing.JTextArea;

public class SimpleParser extends PrologParser {
    private final String queryRequest;
    private final String queryResponse;

    public SimpleParser(final String queryMessage, final String paramResponse) {
        queryRequest = queryMessage;
        queryResponse = paramResponse;
    }

    @Override
    public void writeOutput(final JTextArea textArea) {
        textArea.append(String.format(getFormat(), queryRequest, queryResponse));
    }
}
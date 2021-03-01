package iart.application;

import iart.model.AutorTable;

import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class AuthorsList extends PrologParser {
    private final String queryRequest;
    private final ArrayList<String> queryResponse;

    public AuthorsList(final String queryMessage, final ArrayList<String> paramResponse) {
        queryRequest = queryMessage;
        queryResponse = paramResponse;
    }

    @Override
    public void writeOutput(final JTextArea textArea) {
        textArea.append(String.format(getFormat(), queryRequest, "Lista apresentada em nova janela."));
    }

    @Override
    public void showDialog(final Frame paramParent) {
        new TableWindow(paramParent, new AutorTable(queryResponse)).setVisible(true);
    }
}
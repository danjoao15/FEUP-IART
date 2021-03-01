package iart.application;

import iart.BookWorm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.Normalizer;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import se.sics.jasper.ConversionFailedException;
import se.sics.jasper.IllegalTermException;
import se.sics.jasper.Query;
import se.sics.jasper.SICStus;
import se.sics.jasper.SPTerm;

public class GUIMain extends javax.swing.JFrame {
	private final DefaultListModel<String> defaultListModel = new DefaultListModel<>();

	public GUIMain() {
		final JToolBar guiToolbar = new JToolBar();
		final JButton buttonQuery = new JButton();
		final JSplitPane guiSplit = new JSplitPane();
		final JPanel guiQuery = new JPanel();
		final JLabel labelQuery = new JLabel();
		final JScrollPane tabExamples = new JScrollPane();
		final JScrollPane tabHistory = new JScrollPane();
		final JScrollPane tabOutput = new JScrollPane();
        final JScrollPane tabInformation = new JScrollPane();
        final JTextPane tabInfo = new JTextPane();

        tabInfo.setEditable(false);
        tabInfo.setContentType("text/html");
        tabInfo.setText("<html>\n<body>\n<h2 style=\"margin-top:0;text-align:center;\">/ BOOKWORM \\</h2>\n<br>\n<p style=\"margin-top:0\">&nbsp;&nbsp;&nbsp;&nbsp;Daniela João (<a href=\"mailto:up201505982@fe.up.pt\">up201505982@fe.up.pt</a>)</p>\n<p style=\"margin-top:0\">&nbsp;&nbsp;&nbsp;&nbsp;João Monteiro (<a href=\"mailto:up201506130@fe.up.pt\">up201506130@fe.up.pt</a>)</p>\n<p style=\"margin-top:0\">&nbsp;&nbsp;&nbsp;&nbsp;Nelson Costa (<a href=\"mailto:up201403128@fe.up.pt\">up201403128@fe.up.pt</a>)</p>\n<br>\n<br>\n<b>Instruções:</b>\n<br>\n<ul style=\"margin-left:16px\">\n \n<br><p style=\"margin: 0 0 12px; text-align: justify;\">O BOOKWORM interpreta e responde a frases escritas em linguagem natural, usando DCGs. O programa deve incluir informação sobre escritores e respetivas obras literárias (pode incluir outra informação que considere relevante).</p>\n<p style=\"margin: 0 0 12px; text-align: justify;\">Para questionar sobre obras, certifique-se de que escreve os seus títulos entre plicas. <br> Para questionar sobre autores, certifique-se de que escreve os seus nomes começados com letra maiúscula. <br> Para questões, certifique-se de que as termina com '?'. <br> Não termine frases declarativas com pontos finais. <br>Exemplos de como questionar, na pestana 'Exemplos'. </body>\n</html>");
        tabInfo.setHighlighter(null);
        tabInfo.setKeymap(null);
		GridBagConstraints gridBagConstraints;
		inputQuery = new JTextField();
		guiTabs = new JTabbedPane();
		listExamples = new JList<>();
		listHistory = new JList<>();
		textOutput = new JTextArea();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle(BookWorm.strProgramName);
		getContentPane().add(guiToolbar, BorderLayout.PAGE_START);
		guiSplit.setBorder(null);
		guiSplit.setDividerSize(0);
		guiSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
		guiQuery.setLayout(new GridBagLayout());
		labelQuery.setText("Pergunte-me algo:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.insets = new Insets(8, 8, 8, 8);
		guiQuery.add(labelQuery, gridBagConstraints);
		inputQuery.setMinimumSize(new Dimension(80, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(8, 0, 8, 0);
		guiQuery.add(inputQuery, gridBagConstraints);
		buttonQuery.setText("Pesquisar");
		buttonQuery.addActionListener(this::buttonQueryActionPerformed);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(8, 8, 8, 8);
		guiQuery.add(buttonQuery, gridBagConstraints);
		guiSplit.setLeftComponent(guiQuery);
		guiTabs.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));

		listExamples.setModel(new AbstractListModel<String>() {
			private final String[] comandosExemplo =
					{
							"quem escreveu 'A Reliquia'?",
							"quando nasceu Camilo?",
							"quais os autores portugueses?",
							"quem nasceu no seculo XX?",
							"quem nasceu em 1917?",
							"'A Morgadinha dos Canaviais' foi escrita em portugues",
                            "quem esta vivo?",
                            "quem nasceu antes do seculo XX?",
                            "que livros escreveu Pessoa?",
                            "que contos escreveu Mia?"
					};

			@Override
			public int getSize() {
				return comandosExemplo.length;
			}

			@Override
			public String getElementAt(int i) {
				return comandosExemplo[i];
			}
		});

		listExamples.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent evt) {
				listExamplesMouseClicked(evt);
			}
		});

		listExamples.addListSelectionListener(this::listExamplesValueChanged);
		listExamples.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabExamples.setViewportView(listExamples);
		guiTabs.addTab("Exemplos", tabExamples);
		listHistory.setModel(defaultListModel);

		listHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent evt)
			{
				listHistoryMouseClicked(evt);
			}
		});

		listHistory.addListSelectionListener(this::listHistoryValueChanged);
		listHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabHistory.setViewportView(listHistory);
		guiTabs.addTab("Histórico", tabHistory);
		textOutput.setEditable(false);
		textOutput.setColumns(40);
		textOutput.setFont(textOutput.getFont().deriveFont((float) 12));
		textOutput.setLineWrap(true);
		textOutput.setRows(8);
		textOutput.setTabSize(4);
		tabOutput.setViewportView(textOutput);
		guiTabs.addTab("Respostas", tabOutput);
        tabInformation.setViewportView(tabInfo);
        guiTabs.addTab("Informações", tabInformation);
		guiSplit.setBottomComponent(guiTabs);
		getContentPane().add(guiSplit, BorderLayout.CENTER);
		setSize(new Dimension(800, 340));
		setLocationRelativeTo(null);
	}

	private final int OUTPUT_TAB = 2;

	private void buttonQueryActionPerformed(final ActionEvent evt) {
		search(inputQuery.getText());
	}

	private void search(final String queryMessage) {
		if (queryMessage == null || queryMessage.isEmpty()) {
			BookWorm.showWarning(this, "Pergunte-me algo antes!");
		}
		else {
			inputQuery.setText(queryMessage);
			listHistory.clearSelection();
			listExamples.clearSelection();

			if (defaultListModel.contains(queryMessage)) {
				defaultListModel.removeElement(queryMessage);
			}

			defaultListModel.addElement(queryMessage);
			guiTabs.setSelectedIndex(OUTPUT_TAB);
			query(queryMessage);
		}
	}

	private void listHistoryMouseClicked(final MouseEvent evt) {
		searchList(evt);
	}

	private final String msgFormat = "\nQ: %s\nA: %s\n";

	public final PrologParser parseAnswer(final String userRequest) throws Exception{
		final HashMap queryResults = new HashMap();
		final ArrayList<String> queryResult = new ArrayList<>();
		final SICStus sp = SICStus.getInitializedSICStus();

		final String generatedRequest = generateRequest(userRequest);
		final Query query = sp.openPrologQuery(generatedRequest, queryResults);

		PrologParser myResponse = null;
		String functorName = null;

		try {
			if (query.nextSolution()) {
				SPTerm spTerm = (SPTerm) queryResults.get("Lista");

				if (spTerm != null && spTerm.isList()) {
					SPTerm spTerm1 = new SPTerm(sp);
					spTerm.getList(spTerm1, spTerm);
					functorName = spTerm1.getFunctorName();

					while (spTerm1.isValid())
					{
						queryResult.add(spTerm1.toString());
						spTerm.getList(spTerm1, spTerm);
					}
				}
				else {
					if (spTerm != null)
					{
						myResponse = new SimpleParser(userRequest, spTerm.toString());
					}
				}
			}
			else {
				myResponse = new SimpleParser(userRequest, "Não entendi a sua pergunta.");

			}
		}
		catch (final IllegalTermException | ConversionFailedException ex) {
		}
		finally {
			query.close();
		}

		if (functorName == null) {
			return myResponse;
		}

		if (functorName.equals("autor")) {
			myResponse = new AuthorsList(userRequest, queryResult);
		}

		if (functorName.equals("livro")) {
			myResponse = new BookList(userRequest, queryResult);
		}

		return myResponse;
	}

	private void query(final String queryMessage) {
		try {
			final PrologParser prologResponse = parseAnswer(queryMessage);

			if (prologResponse == null)
			{
				textOutput.append(String.format(msgFormat, queryMessage, "Erro!"));
			}
			else
			{
				prologResponse.writeOutput(textOutput);
				prologResponse.showDialog(this);
			}

			guiTabs.setSelectedIndex(OUTPUT_TAB);
		}
		catch (final Exception ex) {
			BookWorm.showError(this, ex);
		}
	}

	private void searchList(final MouseEvent evt) {
		final JList<?> list = (JList<?>) evt.getSource();

		if (evt.getClickCount() == 2) {
			final String selectedValue = (String) list.getSelectedValue();

			if (selectedValue != null)
			{
				search(selectedValue);
			}
		}
	}

	private void listExamplesMouseClicked(final MouseEvent evt) {
		searchList(evt);
	}

	private void listExamplesValueChanged(final ListSelectionEvent evt) {
		inputQuery.setText(listExamples.getSelectedValue());
	}

	private void listHistoryValueChanged(final ListSelectionEvent evt) {
		inputQuery.setText(listHistory.getSelectedValue());
	}

	public static String generateRequest(String originalString) {
		boolean questionForm = originalString.endsWith("?");

		originalString = Normalizer.normalize(originalString, Normalizer.Form.NFD);
		originalString = originalString.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

		final String[] words = originalString.split("\\s+");
		final StringBuilder sb = new StringBuilder();

		sb.append("query(Lista, [");

		for (int i = 0; i < words.length; i++) {
			String currentWord = words[i];
			char firstCharacter = currentWord.charAt(0);
			char last_character = currentWord.charAt(currentWord.length()-1);
			if (firstCharacter == '\'' && last_character  != '\'' && last_character != '?'){
				currentWord += " ";
				String rest = originalString.substring(originalString.indexOf(words[i+1]),originalString.length());
				currentWord += rest.substring(0,rest.indexOf("'")+1);
				String[] s = currentWord.split("\\s+");
				i += s.length-1;
			}
			currentWord.replaceAll("[^\\w]", "");

			if (Character.isUpperCase(firstCharacter)) {
				sb.append("'").append(currentWord).append("'");
			}
			else {
				sb.append(currentWord);
			}

			if (i < words.length - 1) {
				sb.append(", ");
			}
		}

		if (questionForm) {
			if(sb.indexOf("?") != -1)
				sb.replace(sb.indexOf("?"),sb.indexOf("?")+1,"");
			sb.append(", '?'");
		}

		sb.append("],[]).");

		return sb.toString();
	}


	private JList<String> listExamples;
	private JList<String> listHistory;
	private JTabbedPane guiTabs;
	private JTextArea textOutput;
	private JTextField inputQuery;
}
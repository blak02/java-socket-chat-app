import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChatApplicationGUI extends JFrame {

	private JList<String> chatArea;
	private DefaultListModel<String> chatModel;
	private JTextField messageField;
	private JButton sendButton;
	private JList<String> userList;
	private DefaultListModel<String> model;
	private JButton refreshButton;
	private JButton searchButton;
	private JButton getMessagesButton;

	private ChatClient chatClient;

	public ChatApplicationGUI() {
		initComponents();
		setupListeners();
		String name = JOptionPane.showInputDialog("Enter name: ");
		setTitle(name);
		chatClient = new ChatClient(name);
	}

	private void initComponents() {
		setTitle("Chat Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		chatArea = new JList<String>();
		chatModel = new DefaultListModel<>();
		chatArea.setModel(chatModel);

		JScrollPane scrollPane = new JScrollPane(chatArea);
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel(new BorderLayout());
		messageField = new JTextField();
		inputPanel.add(messageField, BorderLayout.CENTER);

		sendButton = new JButton("Send");
		inputPanel.add(sendButton, BorderLayout.EAST);

		panel.add(inputPanel, BorderLayout.SOUTH);

		JPanel controlPanel = new JPanel(new BorderLayout());

		userList = new JList<>();
		model = new DefaultListModel<String>();
		userList.setModel(model);
		JScrollPane sp = new JScrollPane(userList);

		panel.add(sp, BorderLayout.WEST);

		refreshButton = new JButton("Refresh");
		controlPanel.add(refreshButton, BorderLayout.WEST);

		getMessagesButton = new JButton("Get Messages");
		controlPanel.add(getMessagesButton, BorderLayout.EAST);

		searchButton = new JButton("Search");
		inputPanel.add(searchButton, BorderLayout.SOUTH);

		panel.add(controlPanel, BorderLayout.NORTH);

		add(panel);
		setSize(500, 500);
	}

	private void setupListeners() {
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (userList.getSelectedValue() != null) {
					String recipient = userList.getSelectedValue().split(":")[0];
					String message = messageField.getText();
					chatClient.sendMessage(recipient, message);
					messageField.setText("");
				}
			}
		});

		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> userList = chatClient.getConnectedUsers();
				updateUserList(userList);
			}
		});

		getMessagesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (userList.getSelectedValue() != null) {
					String sender = userList.getSelectedValue().split(":")[0];
					List<String> userList = chatClient.getMessages(sender);
					updateMessagesList(userList);
				}
			}

		});

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchText = JOptionPane.showInputDialog("Enter search text:");
				List<String> searchResults = chatClient.searchMessages(searchText);
				updateMessagesList(searchResults);
			}
		});
	}

	private void updateUserList(List<String> list) {
		model.clear();
		for (String user : list) {
			model.addElement(user);
		}

	}

	private void updateMessagesList(List<String> list) {
		chatModel.clear();
		for (String user : list) {
			chatModel.addElement(user);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ChatApplicationGUI chatGUI = new ChatApplicationGUI();
				chatGUI.setVisible(true);
			}
		});
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatClient {

	private String clientId;
	private String serverHost;
	private int serverPort;
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;

	public ChatClient(String name) {
		this.clientId = name;
		serverHost = "localhost"; // Change to the actual server IP
		serverPort = 9999; // Change to the actual server port
		try {
			socket = new Socket(serverHost, serverPort);
			writer = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer.println(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String recipient, String text) {
		writer.println("send;" + recipient + ";" + text);
	}

	public List<String> getConnectedUsers() {
		List<String> userList = new ArrayList<>();
		try {

			writer.println("get_users_list");

			System.out.println("reading users");
			String user;
			while ((user = reader.readLine()) != null) {
				System.out.println("Found: " + user);
				if (user == null || user.equals("null")) {
					return userList;
				}
				userList.add(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public List<String> searchMessages(String searchText) {
		List<String> searchResults = new ArrayList<>();
		try {

			writer.println("find_message;"+searchText);

			String result;
			while ((result = reader.readLine()) != null) {
				if (result == null || result.equals("null")) {
					return searchResults;
				}
				searchResults.add(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchResults;
	}

	public List<String> getMessages(String sender) {
		List<String> userList = new ArrayList<>();
		try {

			writer.println("get_message_history;" + sender);

			System.out.println("reading messages");
			String result;
			while ((result = reader.readLine()) != null) {
				if (result == null || result.equals("null")) {
					return userList;
				}
				userList.add(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}
}

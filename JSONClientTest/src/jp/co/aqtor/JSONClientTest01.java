package jp.co.aqtor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import net.arnx.jsonic.JSON;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONClientTest01 {

	private JFrame frame;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JSONClientTest01 window = new JSONClientTest01();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JSONClientTest01() {
		initialize();
	}

	public void PushButton() {
		// HTTP�ʐM��JSON��������Q�b�g���Ă���B
		String jstr = ConnectHttpServer();
		textArea.setText(jstr + "\n");

		// �i�r�n�m�����񂩂�N���X�̃C���X�^���X�����B
		ConvJSON(jstr);
	}

	// �g�s�s�o�T�[�o�[�ɐڑ�����JSON��������Q�b�g���Ă���B
	public String ConnectHttpServer() {
		String str = "http://localhost:8080/JSONServerTest/JSONServer01";
		String res = "";

		try {
			URL url = new URL(str);

			HttpURLConnection httpoc = (HttpURLConnection)url.openConnection();
			httpoc.connect();

			BufferedReader bstr = new BufferedReader(new InputStreamReader(httpoc.getInputStream(), "UTF-8"));

			do {
				String line = bstr.readLine();
				if(line == null) break;
				res += line;
			} while(true);

			httpoc.disconnect();

		} catch(IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
			textArea.append("��O�G���[�I\n");
		}

		return res;
	}

	// �i�r�n�m������̉��
	public void ConvJSON(String s) {
		// JSONIC�ŕ�������I�u�W�F�N�g�ɂ���B�����ŁB
		HogeCls[] re = JSON.decode(s, HogeCls[].class);

		// ���ʂ�\��
		for(int i = 0; i < re.length; i++) {
			textArea.append("���O�́A" + re[i].str + "�ł��BHP�́A" + re[i].hoge + "�ł��B\n");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 410, 370);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PushButton();
			}
		});
		btnNewButton.setBounds(12, 449, 91, 21);
		frame.getContentPane().add(btnNewButton);
	}
}

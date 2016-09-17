package jp.co.aqtor;

// JSON�͂�������_�E�����[�h��������
// http://sourceforge.jp/projects/sfnet_json-simple.mirror/releases/

// JSON���`�T�[�r�X
// http://www.ctrlshift.net/jsonprettyprinter/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSon01 {

	private JFrame frame;
	private JTextArea textArea;
	private JScrollPane scrollPane_1;
	private JTextArea textArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JSon01 window = new JSon01();
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
	public JSon01() {
		initialize();
	}

	public void PushButton() {

		// �e�L�X�g�t�B�[���h���Q�b�g���Ă��肩���Ă��B
		String str = textArea.getText();

		JSONParser p = new JSONParser();
		JSONObject obj;

		try {
			obj = (JSONObject)p.parse(str);
			textArea_1.setText("�l�́A" + obj.size() + "�ł����B");

			for(int i = 0; i < obj.size(); i++) {
				String ss = obj.toString();
				textArea_1.append(ss + "\n");
			}
		} catch (ParseException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}

/*		try {
			JSONArray jsonArray = new JSONArray();
			for(int i = 0; i < p. jsonArray.g  .length(); i++) {
			}
			jsonArray.
			//JSON Array�̃T�C�Y��\��
			System.out.println("Number of entries " + jsonArray.length());
			//JSON Object���쐬����
			for (int i = 0; i < jsonArray.length(); i++) {
				//getJSONObject��JSON Array�Ɋi�[���ꂽ�v�f��JSON Object�Ƃ��Ď擾�ł���
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				//JSON Object���p�[�X����
				//�\������ۂ�getString("�ق��ق�")��"�ق��ق�"���L�[�Ƃ���l���擾�ł���
				//user�̂悤�ɓ���q�ɂȂ��Ă���Ƃ��́AgetJSONObject()���g���ĊK�w�������Ă���
                                System.out.println(i);
				System.out.println("���e���F"+jsonObject.getString("created_at"));
				System.out.println("�c�C�[�g���e�F"+jsonObject.getString("text"));
				System.out.println("���[�U�[���ȏЉ�F"+jsonObject.getJSONObject("user").getString("description"));
				System.out.println();//���s
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public void PushHogeButton() {
		JSONObject json = new JSONObject();

		json.put("name", "object name");
		json.put("name", "object name2");
		json.put("name", "object name3");

		json.put("Integer", new Integer(10));
		json.put("Double", new Double(10.5));
		json.put("Boolean", new Boolean(true));
		json.put("Null", null);
		textArea_1.setText(json.toString());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 502, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PushButton();
			}
		});
		btnNewButton.setBounds(12, 497, 91, 21);
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 462, 199);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 229, 462, 161);
		frame.getContentPane().add(scrollPane_1);

		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);

		JButton btnNewButton_1 = new JButton("\u30AA\u30D6\u30B8\u30A7\u3092\u6587\u5B57\u5217\u5316\u3059\u308B");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PushHogeButton();
			}
		});
		btnNewButton_1.setBounds(129, 400, 192, 21);
		frame.getContentPane().add(btnNewButton_1);
	}
}

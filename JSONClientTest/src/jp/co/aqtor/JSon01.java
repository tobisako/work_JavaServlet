package jp.co.aqtor;

// JSONはここからダウンロードしたった
// http://sourceforge.jp/projects/sfnet_json-simple.mirror/releases/

// JSON整形サービス
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

		// テキストフィールドをゲットしてつくりかえてやる。
		String str = textArea.getText();

		JSONParser p = new JSONParser();
		JSONObject obj;

		try {
			obj = (JSONObject)p.parse(str);
			textArea_1.setText("値は、" + obj.size() + "ですた。");

			for(int i = 0; i < obj.size(); i++) {
				String ss = obj.toString();
				textArea_1.append(ss + "\n");
			}
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

/*		try {
			JSONArray jsonArray = new JSONArray();
			for(int i = 0; i < p. jsonArray.g  .length(); i++) {
			}
			jsonArray.
			//JSON Arrayのサイズを表示
			System.out.println("Number of entries " + jsonArray.length());
			//JSON Objectを作成する
			for (int i = 0; i < jsonArray.length(); i++) {
				//getJSONObjectでJSON Arrayに格納された要素をJSON Objectとして取得できる
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				//JSON Objectをパースする
				//表示する際はgetString("ほげほげ")で"ほげほげ"をキーとする値を取得できる
				//userのように入れ子になっているときは、getJSONObject()を使って階層を下っていく
                                System.out.println(i);
				System.out.println("投稿日："+jsonObject.getString("created_at"));
				System.out.println("ツイート内容："+jsonObject.getString("text"));
				System.out.println("ユーザー自己紹介："+jsonObject.getJSONObject("user").getString("description"));
				System.out.println();//改行
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

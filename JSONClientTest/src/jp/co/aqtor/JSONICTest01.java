package jp.co.aqtor;

// JSONICを使うテスト
// http://jsonic.sourceforge.jp/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.arnx.jsonic.JSON;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jp.co.aqtor.HogeCls;

public class JSONICTest01 {

	private JFrame frame;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JSONICTest01 window = new JSONICTest01();
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
	public JSONICTest01() {
		initialize();
	}

	// JSONICでJSON変換テスト
	public void PushButton() {
		// オブジェクト生成
		HogeCls obj = new HogeCls();
		obj.setHoge(12);
		obj.setPiyo(22);
		obj.setStr("まじすか");
		obj.setXyz(345);

		// JSONICでオブジェクトを文字列に変換。
		String text = JSON.encode(obj, true);

		// デバッグ表示
		textArea.setText(text + "\n");

		// JSONICで文字列をオブジェクトに変換。
		HogeCls res = null;
		res = JSON.decode(text, HogeCls.class);
		if(res != null) {
			// 結果を文字列表示。
			textArea.append("ほげは" + res.hoge + ", strは" + res.str + "です。");
		}
		textArea.append("あかんやん");
		return;
	}

	// JSONICで複数オブジェクトの変換テスト
	public void PushButton2() {
		// 複数オブジェクト生成？
		HogeCls[] hai = new HogeCls[3];
		hai[0] = new HogeCls();
		hai[0].hoge = 10;
		hai[0].setStr("どらえもん");
		hai[1] = new HogeCls();
		hai[1].hoge = 20;
		hai[1].setStr("のびたくん");
		hai[2] = new HogeCls();
		hai[2].hoge = 33;
		hai[2].setStr("しづかちゃん");

		// JSONICでオブジェクトを文字列
		String text = JSON.encode(hai, true);
		textArea.append(text + "\n");

		// JSONICで文字列をオブジェクトにする。複数版。
		HogeCls[] re = JSON.decode(text, HogeCls[].class);

		// 結果を表示
		for(int i = 0; i < re.length; i++) {
			textArea.append("名前は、" + re[i].str + "です。\n");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 470, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PushButton();
			}
		});
		btnNewButton.setBounds(27, 492, 91, 21);
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 430, 312);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton btnNewButton_1 = new JButton("\u3075\u304F\u3059\u3046");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PushButton2();
			}
		});
		btnNewButton_1.setBounds(212, 492, 91, 21);
		frame.getContentPane().add(btnNewButton_1);
	}
}

package jp.co.aqtor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SoapClientSwing01 {

	private JFrame frame;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SoapClientSwing01 window = new SoapClientSwing01();
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
	public SoapClientSwing01() {
		initialize();
	}

	// ボタン押下
	private void PushButton() {
		try {
			// スタブを生成
			OreServiceStub stub =
				new OreServiceStub("http://localhost:8080/WebTest/services/OreService/");

			// サーブレット・メソッド「HelloOre」

			// パラメータの設定
			OreServiceStub.HelloOre item1 = new OreServiceStub.HelloOre();
			item1.setName("あいうえお");

			// 結果を取得
			OreServiceStub.HelloOreResponse res1 = stub.helloOre(item1);
			String hoge = res1.get_return();

			textArea.append("メソッドHelloOre()の結果は、[" + hoge + "]でした。\n");


			// サーブレット・メソッド「getOreBean」

			// パラメータの設定
			OreServiceStub.GetOreBean item2 = new OreServiceStub.GetOreBean();
			item2.setHiki01(1);		// 第一引数。
			item2.setParam2(111);	// 第二引数。
			item2.setHogepara3(9);	// 第三引数。

			// 結果を取得
			OreServiceStub.GetOreBeanResponse res2 = stub.getOreBean(item2);
			OreServiceStub.OreBean bean = res2.get_return();

			// もちろん「OreBean」使い放題。
			String name = bean.getName();
			int age = bean.getAge();
			int hp = bean.getHitPoint();

			textArea.append("名前は、" + name + "。　年齢は、" + age +
							"。　ＨＰは、" + hp + "です。\n");

		} catch (AxisFault e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 463, 298);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("ACCESS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PushButton();
			}
		});
		btnNewButton.setBounds(48, 220, 91, 21);
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 422, 200);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
}

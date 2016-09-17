package jp.co.aqtor;

// JSONIC���g���e�X�g
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

	// JSONIC��JSON�ϊ��e�X�g
	public void PushButton() {
		// �I�u�W�F�N�g����
		HogeCls obj = new HogeCls();
		obj.setHoge(12);
		obj.setPiyo(22);
		obj.setStr("�܂�����");
		obj.setXyz(345);

		// JSONIC�ŃI�u�W�F�N�g�𕶎���ɕϊ��B
		String text = JSON.encode(obj, true);

		// �f�o�b�O�\��
		textArea.setText(text + "\n");

		// JSONIC�ŕ�������I�u�W�F�N�g�ɕϊ��B
		HogeCls res = null;
		res = JSON.decode(text, HogeCls.class);
		if(res != null) {
			// ���ʂ𕶎���\���B
			textArea.append("�ق���" + res.hoge + ", str��" + res.str + "�ł��B");
		}
		textArea.append("��������");
		return;
	}

	// JSONIC�ŕ����I�u�W�F�N�g�̕ϊ��e�X�g
	public void PushButton2() {
		// �����I�u�W�F�N�g�����H
		HogeCls[] hai = new HogeCls[3];
		hai[0] = new HogeCls();
		hai[0].hoge = 10;
		hai[0].setStr("�ǂ炦����");
		hai[1] = new HogeCls();
		hai[1].hoge = 20;
		hai[1].setStr("�̂т�����");
		hai[2] = new HogeCls();
		hai[2].hoge = 33;
		hai[2].setStr("���Â������");

		// JSONIC�ŃI�u�W�F�N�g�𕶎���
		String text = JSON.encode(hai, true);
		textArea.append(text + "\n");

		// JSONIC�ŕ�������I�u�W�F�N�g�ɂ���B�����ŁB
		HogeCls[] re = JSON.decode(text, HogeCls[].class);

		// ���ʂ�\��
		for(int i = 0; i < re.length; i++) {
			textArea.append("���O�́A" + re[i].str + "�ł��B\n");
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

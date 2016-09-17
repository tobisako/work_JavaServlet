package jp.co.aqtor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

// JSONIC���g�����e�X�g�`�B
@WebServlet("/JSONServer01")
public class JSONServer01 extends HttpServlet {
	private String encoding="UTF-8";
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		// �����R�[�h�w��
		req.setCharacterEncoding(encoding);
		res.setContentType("text/html; charset=" + encoding);

		// �o��
		PrintWriter out = res.getWriter();
		out.println(getJSONString());
//		out.println("hogehogepiyopiyo");
	}

	// JSONIC���g����JSON��������쐬����
	public String getJSONString() {
		String str;

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

		//String text = "�����҂�[��";

		return text;
	}
}

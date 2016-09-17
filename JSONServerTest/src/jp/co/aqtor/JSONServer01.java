package jp.co.aqtor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

// JSONICを使ったテスト～。
@WebServlet("/JSONServer01")
public class JSONServer01 extends HttpServlet {
	private String encoding="UTF-8";
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		// 文字コード指定
		req.setCharacterEncoding(encoding);
		res.setContentType("text/html; charset=" + encoding);

		// 出力
		PrintWriter out = res.getWriter();
		out.println(getJSONString());
//		out.println("hogehogepiyopiyo");
	}

	// JSONICを使ってJSON文字列を作成する
	public String getJSONString() {
		String str;

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

		//String text = "うそぴょーん";

		return text;
	}
}

// 参考ＵＲＬ：
// http://morado106.blog106.fc2.com/blog-entry-34.html
// こっちも。
// http://www.ibm.com/developerworks/jp/webservices/library/ws-devaxis2part1/http://www.ibm.com/developerworks/jp/webservices/library/ws-devaxis2part1/

package jp.co.aqtor;

public class MyService {
	// 普通に文字列を返すだけ
	public String HelloAxis2(String name)
	{
		return "Hello, " + name +"!";
	}

  // データベースから値を取得
  public MyItemBean getMyItem(int id)
  {
    MyItemBean item = new MyItemBean();
    item.setAge(12);

    return item;
  }
}


// その1：素で動くテスト（スタブクラスの作成が不要版）
//
// ・Eclipse + Tomcat + Axis2 でWebサービス作成
// http://cylog.hatenablog.jp/entry/20110509/1304948217
//
// http://localhost:8080/SOAPServerTest/
//
// http://localhost:8080/SOAPServerTest/services/SOAPServerTest01/getEchoTest?echo=Hello
//
// 注意：
// Tomcat7サーバーに登録してあるのは本プロジェクトのみにしないと動かないらしい。

package jp.co.aqtor;

public class SOAPServerTest01 {

	// SOAPのメソッド
	public String getEchoTest(String echo) {
		return echo + "ですねん";
	}
}


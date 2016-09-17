// 勉強会用に作成（By R.Tobisako）
// OAuthテスト（ライブラリ「scribe-java」と「twitter4j」を使用）
// OAuth認証を行いアクセストークンを取得…scrive-java
// アクセストークンを使いTwitterAPIにアクセス…twitter4j

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import org.scribe.builder.api.TwitterApi;
import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.model.Verb;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.exceptions.OAuthException;

import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.Configuration;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;
import twitter4j.User;
import twitter4j.TwitterException;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;

// サーブレット・クラス
public class OAuthTest extends HttpServlet {

	private String sOAuthToken = null;
	private String sOAuthVerifier = null;
	private boolean bGetAccessToken = false;	// セッション管理出来ていないので注意！
	String sAccessToken = null;					// セッション管理出来ていないので注意！
	String sAccessTokenSecret = null;			// セッション管理出来ていないので注意！
	private OAuthService svTmp = null;			// セッション管理出来ていないので注意！
	private Token requestTokenTmp = null;		// セッション管理出来ていないので注意！

	///////////////////////////////////////////////////////////
	// ツイッター・アクセストークン取得
	public void GetAccesToken() {
		if( bGetAccessToken == true ) return;	// 取得済み。

		// トークン情報から「アクセストークン」を取得
		Verifier verifier = new Verifier(sOAuthVerifier);
		Token accessToken = svTmp.getAccessToken(requestTokenTmp, verifier);
		sAccessToken = accessToken.getToken();
		sAccessTokenSecret = accessToken.getSecret();
	}

	///////////////////////////////////////////////////////////
	// ツイッター・インスタンス取得
	public Twitter getTwitterInstance() {
		// 「twitter4j」起動
		ConfigurationBuilder cb = new ConfigurationBuilder()
			.setDebugEnabled(true)
			.setOAuthConsumerKey("BuZEKZwf3GZ8AZv2GJBpw")
			.setOAuthConsumerSecret("0uKD8lMG8JYiPunuZDfyaLanTS71QoVWIuMDVKYX2Y")
			.setOAuthAccessToken(sAccessToken)
			.setOAuthAccessTokenSecret(sAccessTokenSecret);

		Configuration conf = cb.build();
		TwitterFactory tf = new TwitterFactory( conf );

		return tf.getInstance();
	}

	///////////////////////////////////////////////////////////
	// GETメソッド処理
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
	    response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 判定１：oauthトークンが来た？
		String sTmp = request.getParameter( "oauth_token" );
		if( sTmp != null && sTmp.length() != 0 ) {
			// パラメーターが入っている＝レスポンスが来たと判断。
			GetOAuthToken(request, response);
			GetAccesToken();
			// アクセストークン取得後は、クライアント側にリダイレクト要求
			response.sendRedirect("http://localhost:8080/servlet-samples/OAuthTest?TOBIparam=mainmenu");
			return;
		}

		// 判定２：TOBIparamが来た？
		sTmp = request.getParameter( "TOBIparam" );
		if( sTmp != null && sTmp.length() != 0 ) {
			// パラメーターが入っている
			MenuJump(request, response);
			return;
		}

		// 一番最初の画面を表示する。
		bGetAccessToken = false;
		FirstView(response);
	}

	///////////////////////////////////////////////////////////
	// OAuthトークンを取得した後の処理。
	public void GetOAuthToken( HttpServletRequest request, HttpServletResponse response )
		throws IOException {
		// リクエストパラメーターからトークン情報を取得
		sOAuthToken = request.getParameter( "oauth_token" );
		sOAuthVerifier = request.getParameter( "oauth_verifier" );
	}

	///////////////////////////////////////////////////////////
	// 認証後メニュージャンプ処理
	public void MenuJump( HttpServletRequest request, HttpServletResponse response )
		throws IOException {

		// どのメニューにジャンプするか？
		String sTmp = request.getParameter( "TOBIparam" );
		if( sTmp != null && sTmp.length() != 0 ) {
			if( sTmp.equals("listup") ) {
				ViewListUp(response);		// リストアップする画面へ。
			} else if( sTmp.equals("tweet") ) {
				ViewTweetWrite(request, response);		// ツイートする画面へ。
			} else {
				// どれにも当てはまらない場合はメインメニューへ。
				ViewMainMenu(request, response);
			}
		} else {
			// どれにも当てはまらない場合はメインメニューへ
			ViewMainMenu(request, response);	// ※異常遷移。
		}
	}

	///////////////////////////////////////////////////////////
	// 最初の表示処理
	public void FirstView( HttpServletResponse response ) throws IOException {
		PrintWriter out = response.getWriter();

		try {
			// OAuthService オブジェクトを生成
			OAuthService sv = new ServiceBuilder()
				.provider(TwitterApi.class)
				.apiKey("BuZEKZwf3GZ8AZv2GJBpw")
				.apiSecret("0uKD8lMG8JYiPunuZDfyaLanTS71QoVWIuMDVKYX2Y")
				.callback("http://localhost:8080/servlet-samples/OAuthTest")
				.build();

			// リクエスト・トークン（request token）を取得
			Token requestToken = sv.getRequestToken();

			// レスポンス処理用にテンポラリ保存（美しくない）
			svTmp = sv;
			requestTokenTmp = requestToken;

			// ユーザに認証してもらう
			String authUrl = sv.getAuthorizationUrl(requestToken);

			// ＨＴＭＬ出力作成
		    out.println("<html><head>");
			out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		    out.println("<title>TOBI World.</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<h1>Hello TOBI 2 World!</h1><br><br>");
			//	out.println(Oresponse.getBody());

			out.println("この URL に ACCESS してね！<br>");
		    out.println("<a href=\"" + authUrl + "\">" + authUrl + "</a><br>");
			// out.println("<br>PIN:<br>");

			out.println("</body>");
			out.println("</html>");

		} catch(OAuthException e) {
			// エラー発生
			out.println("<html><body>damedakorya [" + e + "].</body></html>");
		}
	}

	///////////////////////////////////////////////////////////
	// メインメニュー画面の表示s
	public void ViewMainMenu( HttpServletRequest request, HttpServletResponse response )
		throws IOException {
		String sName = null;
		String sScreenName = null;
		int iFriendsCount = 0;
		int iFollowersCount = 0;

		Twitter twitter = getTwitterInstance();
		PrintWriter out = response.getWriter();

		// ツイッターのユーザー情報を取得
		try {
			User user = twitter.verifyCredentials();
			sName = user.getName();
			sScreenName = user.getScreenName();
			iFriendsCount = user.getFriendsCount();
			iFollowersCount = user.getFollowersCount();
		} catch (TwitterException e) {
			// エラー発生
			out.println("<html><body>AccessTokenGetAfter [" + e + "].</body></html>");
		}

		// ＨＴＭＬ出力作成
	    out.println("<html><head>");
		out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		out.println("<title>TOBI OAuth Test.</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>[OAUTH] request token getだぜ!!!!</h1><br><br>");

		out.println("oauth_token [" + sOAuthToken + "] desu.<br>");
		out.println("oauth_verifier [" + sOAuthVerifier + "] desu.<br><br>");
		out.println("Access Token [" + sAccessToken + "] desu.<br>");
		out.println("Access Token Secret [" + sAccessTokenSecret + "] desu.<br><br>");

		out.println("　なまえ　　　：" + sName + "<br>");
		out.println("　ひょうじ名　：" + sScreenName + "<br>");
		out.println("　ふぉろー数　：" + iFriendsCount + "<br>");
		out.println("　ふぉろわー数：" + iFollowersCount + "<br><br>");

		// リンクで処理する。
		out.println("<a href=\"/servlet-samples/OAuthTest?TOBIparam=listup\">・ツイッター読み込みテスト</a><br>");
		out.println("<a href=\"/servlet-samples/OAuthTest?TOBIparam=tweet\">・ツイッター書き込みテスト</a><br>");

		// メニュー＆リンク
		out.println("</body></html>");
	}

	///////////////////////////////////////////////////////////
	// ツイートのリストアップ表示画面
	public void ViewListUp(HttpServletResponse response) throws IOException {

		Twitter twitter = getTwitterInstance();
		PrintWriter out = response.getWriter();

		// ＨＴＭＬ出力作成
	    out.println("<html><head>");
		out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		out.println("<title>TOBI OAuth Test.</title>");
		out.println("</head><body>");
		out.println("<br><br><br>つぶやきを見てみよう。<br>---------------------<br>");

		try {
			// 特定ユーザーのツイートを取得
			List<Status> statuses2 = twitter.getUserTimeline();
			for(Status status : statuses2)
			{
				out.println(status.getUser().getName() + " : " + status.getText() + "<br>");
			}
		} catch (TwitterException e) {
			// エラー発生
			out.println("<html><body>PushButtonRead [" + e + "].</body></html>");
		}

		out.println("---------------------<br><br>");
		out.println("<a href=\"/servlet-samples/OAuthTest?TOBIparam=mainmenu\">・戻る。</a><br>");
		out.println("</body></html>");
	}

	///////////////////////////////////////////////////////////
	// ツイート書込み用画面。
	public void ViewTweetWrite(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();

		// ＨＴＭＬ出力作成
	    out.println("<html><head>");
		out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		out.println("<title>TOBI OAuth Test.</title>");
		out.println("</head><body>");
		out.println("<br><br><br>");

		out.println("<form action=\"/servlet-samples/OAuthTest\" method=\"post\">");
		out.println("<input type=\"text\" name=\"tweetmessage\" />");
		out.println("<input type=\"submit\" value=\"ツイッター投稿する！！\">");
		out.println("</form>");

		out.println("hoge<br>");
		out.println("</body></html>");
	}

	///////////////////////////////////////////////////////////
	// POSTメソッド処理
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

	    response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		// ＨＴＭＬ出力作成
	    out.println("<html><head>");
		out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		out.println("<title>TOBI OAuth Test.</title>");
		out.println("</head><body>");
		out.println("<br><br><br>");

		// 入力があった場合のみツイートする。
		String sTmp = request.getParameter( "tweetmessage" );
		if( sTmp != null && sTmp.length() != 0 ) {
			try {
				// 「twitter4j」で、ツイート処理を実行。
				Twitter twitter = getTwitterInstance();
				Status status = twitter.updateStatus(sTmp);

				out.println("「" + sTmp + "」をツイートしますた(*´д｀*)<br><br>");

				} catch (TwitterException e) {
				// エラー発生
				out.println("<html><body>PushButtonRead [" + e + "].</body></html>");
			}
		}

		out.println("<a href=\"/servlet-samples/OAuthTest?TOBIparam=mainmenu\">・戻る。</a><br>");
		out.println("</body></html>");
	}

}

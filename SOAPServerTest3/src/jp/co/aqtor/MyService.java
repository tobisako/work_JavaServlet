// �Q�l�t�q�k�F
// http://morado106.blog106.fc2.com/blog-entry-34.html
// ���������B
// http://www.ibm.com/developerworks/jp/webservices/library/ws-devaxis2part1/http://www.ibm.com/developerworks/jp/webservices/library/ws-devaxis2part1/

package jp.co.aqtor;

public class MyService {
	// ���ʂɕ������Ԃ�����
	public String HelloAxis2(String name)
	{
		return "Hello, " + name +"!";
	}

  // �f�[�^�x�[�X����l���擾
  public MyItemBean getMyItem(int id)
  {
    MyItemBean item = new MyItemBean();
    item.setAge(12);

    return item;
  }
}


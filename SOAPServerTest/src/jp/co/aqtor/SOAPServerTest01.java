// ����1�F�f�œ����e�X�g�i�X�^�u�N���X�̍쐬���s�v�Łj
//
// �EEclipse + Tomcat + Axis2 ��Web�T�[�r�X�쐬
// http://cylog.hatenablog.jp/entry/20110509/1304948217
//
// http://localhost:8080/SOAPServerTest/
//
// http://localhost:8080/SOAPServerTest/services/SOAPServerTest01/getEchoTest?echo=Hello
//
// ���ӁF
// Tomcat7�T�[�o�[�ɓo�^���Ă���͖̂{�v���W�F�N�g�݂̂ɂ��Ȃ��Ɠ����Ȃ��炵���B

package jp.co.aqtor;

public class SOAPServerTest01 {

	// SOAP�̃��\�b�h
	public String getEchoTest(String echo) {
		return echo + "�ł��˂�";
	}
}


package jspstudy.dbcone;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconn {

	// ���� ����
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "System";
	String password = "1111";

	public Connection getConnection() {
		Connection conn = null;
		try {
			// �ش� ��Ű���� �ִ� Ŭ������ �����´�.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ���������� Ȱ���ؼ� ���� ��ü�� �����.
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}

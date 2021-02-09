
public class Main {

	public static void main(String[] args) {
		// TODO this will be main class
		// Call login from here
		System.out.println(MysqlJDBC.getConnection());
		MysqlJDBC.closeConnection();
		System.out.println(MysqlJDBC.getConnection());
	}

}

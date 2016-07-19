
// For convenience, always static import your generated tables and jOOQ functions to decrease verbosity:
import static test.generated.Tables.AUTHOR;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class Main {
	public static void main(String[] args) {
		String userName = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/library";

		// Connection is the only JDBC resource that we need
		// PreparedStatement and ResultSet are handled by jOOQ, internally
		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			// ...
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			Result<Record> result = create.select().from(AUTHOR).fetch();
			System.out.println("HelloWorld!");
		}
		// For the sake of this tutorial, let's keep exception handling simple
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
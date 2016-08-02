
// For convenience, always static import your generated tables and jOOQ functions to decrease verbosity:
import static test.generated.Tables.AUTHOR;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.TableField;
import org.jooq.impl.DSL;

import test.generated.tables.records.AuthorRecord;

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
			Result<Record> result = create.select()
					.from(AUTHOR)
					.where(AUTHOR.FIRST_NAME.equal("ocean"))
					.orderBy(AUTHOR.FIRST_NAME.desc())
					.fetch();
			System.out.println("HelloWorld!");
			for (Record r : result) {
			    Integer id = r.getValue(AUTHOR.ID);
			    String firstName = r.getValue(AUTHOR.FIRST_NAME);
			    String lastName = r.getValue(AUTHOR.LAST_NAME);

			    System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
			}
			
			create.insertInto(AUTHOR,
			        AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
			      .values(100, "Hermann", "Hesse")
			      .values(101, "Alfred", "Döblin")
			      .execute();
		}
		// For the sake of this tutorial, let's keep exception handling simple
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
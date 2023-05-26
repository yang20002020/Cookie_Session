package model;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.JDBCUtils;
import java.sql.SQLException;

/**
 * 处理数据的JavaBean
 * 
 * @author jt
 *
 */
public class UserModel {

	/**
	 * 处理用户登录的方法：
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User login(User user) throws SQLException {
		// 连接数据库：通过传入的用户名和密码去数据库中进行查询
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		User existUser = queryRunner.query("select * from user where username = ? and password = ?",
				new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());
		return existUser;
	}

}

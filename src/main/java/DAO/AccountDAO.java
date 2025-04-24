package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO implements AccountDAOInt {
    /**
     * This method creates a new account, given a valid username and password.
     * @param username - must not be blank, be at least one character long.
     * @param password - must not be blank, be at least four characters long.
     * @return information of newly created account.
     */
    public Account createAccount(String username, String password) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account(username, password) VALUES(?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int account_id = (int) rs.getInt(1);
                return new Account(account_id, username, password);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * This method validates a login attempt.
     * @param username - must not be blank, be at least one character long.
     * @param password - must not be blank, be at least four characters long.
     * @return information of logged in account.
     */
    public Account loginAccount(String username, String password) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE account.username = ? AND account.password = ?;" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                Account account = new Account(rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password"));

                return account;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * This method finds an account with a given id.
     * @param id - id of an account.
     * @return information of an account.
     */
    public static Account findAccountById(int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE account_id = ?;" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                Account account = new Account(rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password"));

                return account;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * This method finds an account with a given username.
     * @param username - username of an account.
     * @return information of an account.
     */
    public static Account findAccountByUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ?;" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                Account account = new Account(rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password"));

                return account;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

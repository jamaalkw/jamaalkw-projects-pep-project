package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO implements MessageDAOInt {
    /**
     * This method creates a new message with given information.
     * @param posted_by - id of account that posted the message.
     * @param message_text - contents of message.
     * @param time_posted_epoch - the time at which the message was posted.
     * @return newly created message.
     */
    public Message createMessage(int posted_by, String message_text, long time_posted_epoch) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES(?, ?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, posted_by);
            preparedStatement.setString(2, message_text);
            preparedStatement.setLong(3, time_posted_epoch);
            
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                int message_id = (int) rs.getInt(1);
                return new Message(message_id, posted_by, message_text, time_posted_epoch);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * This method retrieves a message with a specific id.
     * @param id - given id of a message.
     * @return message of a specific given id.
     */
    public Message getMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"),
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));

                return message;
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        // if error retrieving data, return empty message
        return null;
    }


    /**
     * This method updates the text of a message with a given id.
     * @param id - id of message.
     * @param updated_message_text - contents to override message with.
     * @return updated message.
     */
    public Message updateMessageById(int id, String updated_message_text) {
        Message oldMessage = getMessageById(id);
        Connection connection = ConnectionUtil.getConnection();
        try {
        
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, updated_message_text);
            preparedStatement.setInt(2, id);
            
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                return new Message(id, oldMessage.getPosted_by(), updated_message_text, oldMessage.getTime_posted_epoch());
            }
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * This method retrieves messages from a specific account.
     * @param account_id - id of an existing account
     * @return list of messages from a specific account id.
     */
    public List<Message> getAllMessagesByAccountId(int account_id){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message WHERE posted_by = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        // all messages from account returned as a list, even if empty
        return messages;
    }


    /**
     * This method retrieves all messages.
     * @return all messages.
     */
    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        // all messages returned as a list, even if empty
        return messages;
    }


    /**
     * This method deletes a message with a specific id.
     * @param id - id of message.
     */
    public Message deleteMessageById(int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            Message deleted_message = getMessageById(id);

            String sql = "DELETE FROM message WHERE message_id = ?;" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);
            
            preparedStatement.executeUpdate();

            return deleted_message;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

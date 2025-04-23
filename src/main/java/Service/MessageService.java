package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService implements MessageServiceInt {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /**
     * This method creates a new message.
     * @param message - a message object.
     * @return newly created message.
     */
    public Message createMessage(Message message) {
        return messageDAO.createMessage(message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
    }

    /**
     * This method retrieves a message with a specific id.
     * @param id - id of message.
     * @return existing message.
     */
    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    /**
     * This method updates a message.
     * @param message_id - id of message.
     * @param updated_message_text - text to overwrite message with.
     * @return existing message.
     */
    public Message updateMessageById(int message_id, String updated_message_text) {
        return messageDAO.updateMessageById(message_id, updated_message_text);
    }

    /**
     * This method deletes a message.
     * @param id - id of message.
     * @return newly deleted message.
     */
    public Message deleteMessageById(int id) {
        return messageDAO.deleteMessageById(id);
    }

    /**
     * This method retrieves all messages.
     * @return list of all messages ever posted.
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * This method retrieves all messages posted by a given account.
     * @param account_id - id of existing account.
     * @return list of messages posted by given account.
     */
    public List<Message> getAllMessagesByAccountId(int account_id) {
        return messageDAO.getAllMessagesByAccountId(account_id);
    }
}

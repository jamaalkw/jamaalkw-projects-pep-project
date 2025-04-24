package Service;

import java.util.*;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService implements MessageServiceInt {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /**
     * This method checks if a string is completely filled with whitespace.
     * @param string - a String object.
     * @return true if string is whitespace, false otherwise.
     */
    private boolean whiteSpaceChecker(String str) {
        for (char ch : str.toCharArray()) {
            if (!Character.isWhitespace(ch)) return false;
        }
        return true;
    }

    /**
     * This method creates a new message.
     * @param message - a message object.
     * @return newly created message.
     */
    public Message createMessage(Message message) {
        // message text must not be longer than 255 characters and cannot be empty, and account must exist
        if ((message.getMessage_text().length() <= 255) && (!whiteSpaceChecker(message.getMessage_text())) && (AccountDAO.findAccountById(message.getPosted_by()) != null)) {
            return messageDAO.createMessage(message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
        }
        return null;
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
        // updated text must not be longer than 255 characters and cannot be empty
        if ((updated_message_text.length() <= 255) && (!whiteSpaceChecker(updated_message_text))) {
            return messageDAO.updateMessageById(message_id, updated_message_text);
        }
        return null;
    }

    /**
     * This method deletes a message.
     * @param id - id of message.
     * @return newly deleted message.
     */
    public Message deleteMessageById(int id) {
        // message must exist
        if (messageDAO.getMessageById(id) != null) {
            return messageDAO.deleteMessageById(id);
        }
        return null;
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

package DAO;

import Model.Message;
import java.util.*;

public interface MessageDAOInt {
    public Message createMessage(int posted_by, String message_text, long time_posted_epoch);
    public Message updateMessageById(int id, String updated_message_text);

    public Message getMessageById(int id);
    public List<Message> getAllMessagesByAccountId(int account_id);
    public List<Message> getAllMessages();

    public Message deleteMessageById(int id);
}

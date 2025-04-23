package Service;

import java.util.List;

import Model.Message;

public interface MessageServiceInt {
    public Message createMessage(Message message);
    public Message getMessageById(int id);
    public Message updateMessageById(int message_id, String updated_message_text);
    public Message deleteMessageById(int id);
    public List<Message> getAllMessages();
    public List<Message> getAllMessagesByAccountId(int account_id);
}

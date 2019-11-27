import org.telegram.telegrambots.meta.api.objects.Message;

public class Subscriber {
    private Integer id;
    private Long chatId;
    private String location;

    public Subscriber(Message message) {
        this.id = message.getFrom().getId();
        this.chatId = message.getChatId();
        this.location = Bot.locationToString(message.getLocation());
    }

    public Subscriber(Integer id, Long chatId, String location) {
        this.id = id;
        this.chatId = chatId;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public long getChatId() {
        return chatId;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return id + " " + chatId + " " + location;
    }
}
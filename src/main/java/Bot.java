import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private static Bot bot;
    private boolean subscribing = false;
    public static Map<Integer, Subscriber> subscriberMap = SubscribersDB.getSubscriberMap();

    private Bot(DefaultBotOptions botOptions) {
        super(botOptions);
        BotMessage.sendMsgToAllSub();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            BotMessage botMessage = new BotMessage(message);
            if (message.hasLocation()) {
                if (subscribing) {
                    Subscriber subscriber = new Subscriber(message);
                    subscriberMap.put(subscriber.getId(), subscriber);
                    SubscribersDB.resetSubDB(subscriberMap);
                    System.out.println("User ID - " + update.getMessage().getChatId() + " subscribed");
                    botMessage.sendMsg("You will get \n weather forecast \n at 8:00 AM daily");
                    subscribing = false;
                    return;
                }
                try {
                    Weather weather = new Weather(message.getLocation());
                    botMessage.sendMsg(weather.getTodayWeather());
                    botMessage.sendMsg(weather.getTomorrowWeather());
                    return;
                } catch (IOException e) {
                    System.out.println("Sending message by location exeption");
                    ;
                }
            }
            switch (message.getText()) {
                case "/instruction":
                    botMessage.sendMsg(
                            "To get current weather \n" +
                                    "by your location press: \n" +
                                    "weather\n" +
                                    "to subscribe daily weather \n" +
                                    "forecast at 8:00 AM press: \n" +
                                    "subscribe\n" +
                                    "to unsubscribe \n" +
                                    "press relevant button");
                    break;
                case "/subscribe":
                    if (!subscriberMap.containsKey(message.getFrom().getId())) {
                        subscribing = true;
                        botMessage.sendMsg("To sub send your location by pressing weather");
                    } else botMessage.sendMsg("You are already subscribed");
                    break;
                case "/unsubscribe":
                    if (subscriberMap.containsKey(message.getFrom().getId())) {
                        subscriberMap.remove(message.getFrom().getId());
                        SubscribersDB.resetSubDB(subscriberMap);
                        botMessage.sendMsg("Unsubscribed successfully");
                        System.out.println("User ID - " + update.getMessage().getChatId() + " unsubscribed");
                    } else {
                        botMessage.sendMsg("You are not subscribed");
                    }
                    break;
                default:
                    botMessage.sendMsg("Read the instruction");
            }
        }
    }

    public static Bot getInstance() {
        if (bot == null) {
            synchronized (Bot.class) {
                if (bot == null)
                    bot = new Bot(Main.botOptions);
            }
        }
        return bot;
    }

    public static String locationToString(Location location) {
        String string = String.format("lat=%.2f&lon=%.2f", location.getLatitude(), location.getLongitude());
        return string;
    }

    @Override
    public String getBotUsername() {
        return Main.BOT_PROPERTY_NAME;
    }

    @Override
    public String getBotToken() {
        return Main.BOT_TOKEN_PROPERTY_NAME;
    }
}
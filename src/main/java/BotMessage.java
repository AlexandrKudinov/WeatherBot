import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BotMessage {
    private Bot bot;
    private Long messageChatId;

    public BotMessage(Message message) {
        this.messageChatId = message.getChatId();
        this.bot = Bot.getInstance();
    }

    public BotMessage(Subscriber subscriber) {
        this.messageChatId = subscriber.getChatId();
        this.bot = Bot.getInstance();
    }

    public synchronized void sendMsg(String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(messageChatId);
        sendMessage.setText(text);
        try {
            setKeyboard(sendMessage);
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Sending message exception");
            ;
        }
    }

    public static void sendMsgToSub(Subscriber subscriber) {
        Thread thread = new Thread(() -> {
            Weather weather = new Weather(subscriber.getLocation());
            BotMessage botMessage = new BotMessage(subscriber);
            try {
                botMessage.sendMsg(weather.getTodayWeather());
            } catch (IOException e) {
                System.out.println("Sending message to subscriber exception");
            }
        });
        thread.start();
    }

    public static void sendMsgToAllSub() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (Subscriber subscriber : Bot.subscriberMap.values()) {
                    sendMsgToSub(subscriber);
                }
            }
        }, getFirstForecastDate(), 86400000);
    }

    public static Date getFirstForecastDate() {
        Date currentDate = new Date(System.currentTimeMillis());
        String baseForecastTime = "2019.11.26 AD at 08:00:00 MSK";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Date forecastDate = null;
        try {
            forecastDate = simpleDateFormat.parse(baseForecastTime);
        } catch (ParseException e) {
            System.out.println("Parsing data exception");
        }
        while (forecastDate.before(currentDate)) {
            forecastDate = new Date(forecastDate.getTime() + 86400000);
        }
        return forecastDate;
    }

    public void setKeyboard(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("/instruction"));
        keyboardRow1.add(new KeyboardButton("/weather").setRequestLocation(true));
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("/subscribe"));
        keyboardRow2.add(new KeyboardButton("/unsubscribe"));
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }
}
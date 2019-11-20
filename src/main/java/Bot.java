import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;


import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;


public class Bot extends AbilityBot {

    private static String BOT_NAME = "---------";
    private static String BOT_TOKEN = "------------------";
    private static String PROXY_HOST = "------------" /* proxy host */;
    private static Integer PROXY_PORT = 0000000000000 /* proxy port */;


    private static HashMap<Integer, String> cache = new HashMap<>();


    protected Bot(String botToken, String botUsername, DefaultBotOptions botOptions) {
        super(botToken, botUsername, botOptions);

    }

    public int creatorId() {
        return 0;
    }

    public Ability pingPong() {
        return Ability
                .builder()
                .name("ping")
                .info("ping pong")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> silent.send("pong", ctx.chatId()))
                .build();
    }

    public static void main(String[] args) {


        try {
            ApiContextInitializer.init();//инициализируем Api
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(); //создаем объект Api

            //задаем прокси для бота
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            // выбираем тип прокси: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);


            // Register your newly created AbilityBot
            Bot bot = new Bot(BOT_TOKEN, BOT_NAME, botOptions);

            //регистрируем бота
            telegramBotsApi.registerBot(bot);

        } catch (Exception e) {
            BotLogger.error("BotEngine : There is an exception occurred during Bot initialization.", e);

        }
    }

    @Override
    public void onUpdateReceived(Update update) {//метод для приема сообщений(для получения обновлений через long pull(очередь ожидающих запросов))
        WeatherFormat weatherFormat = new WeatherFormat();
        Message message = update.getMessage(); //получаем текст сообщения из объекта update
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "hello":
                    sendMsg(message, "Hello, i am your personal weatherman!");
                    break;
                case "Weather":

                    if (message.hasLocation()){
                        float latitude = message.getLocation().getLatitude();
                        float longitude = message.getLocation().getLongitude();

                        String msg = " Your location:" +
                                        "\n latitude is  " + latitude +
                                        "\n longitude is " + longitude;

                        SendMessage Msg = new SendMessage()
                                .setChatId(message.getChatId().toString())
                                .setText(msg);
                        System.out.println("координаты: "+ latitude+"   "+longitude);
                        try {
                            execute(Msg);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    }


                    System.out.println("Пользователь с ID - " + update.getMessage().getChatId() + " запросил погоду");
                    break;


                case "Subscribe":
                    sendMsg(message, "Вы подписались на рассылку прогноза погоды!");
                    System.out.println("Пользователь с ID - " + update.getMessage().getChatId() + " подписался на прогноз погоды");
                    break;


                case "Unsubscribe":
                    sendMsg(message, "Вы отписались от рассылки прогноза погоды!");
                    System.out.println("Пользователь с ID - " + update.getMessage().getChatId() + " отписался от прогноза погоды");
                    break;

                default:
                    try {
                        sendMsg(message, Weather.getWeather(message.getText(), weatherFormat));
                    } catch (IOException e) {
                        sendMsg(message, " this city is not found ");
                    }

            }
        }


    }


    public String locationToSring(Location location) {
        return String.format("lat=%.2f&lon=%.2f", location.getLatitude(), location.getLongitude());
    }


    private void location(Message message) {
        Location location = message.getLocation();
        String formattedLocation = String.format("%.6f,%.6f", location.getLatitude(), location.getLongitude());
        System.out.println(location.toString());
    }

    public boolean hadLocation(Message message) {
        Integer id = message.getFrom().getId();
        return cache.containsKey(id);
    }


    public void sendMsg(Message message, String text) { //метод для отправки сообщений
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true); // включаем возможность разнизки??????????????
        sendMessage.setChatId(message.getChatId().toString()); // присваиваем ID чата для отправки сообщения
        sendMessage.setReplyToMessageId(message.getMessageId());//определяем ID сообщения на которое будем отвечать
        sendMessage.setText(text);

        try {  //устанавливаем непосредственно саму отправку сообщения
            setBottons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void setBottons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();//создаем клавиатуру
        sendMessage.setReplyMarkup(replyKeyboardMarkup);//устанавливаем разметку для клавиатуры и связываем сообщение с клавиатурой
        replyKeyboardMarkup.setSelective(true);//выводим клавиатуру всем пользователям
        replyKeyboardMarkup.setResizeKeyboard(true);//задаем автоматическую подгонку под количество кнопок
        replyKeyboardMarkup.setOneTimeKeyboard(false);//не скрываем клавиатуру после использования
        List<KeyboardRow> keyboardRows = new ArrayList<>(); //создаем лист кнопок
        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("hello"));
        keyboardFirstRow.add(new KeyboardButton("Weather"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("Subscribe");
        keyboardSecondRow.add("Unsubscribe");


        keyboardRows.add(keyboardFirstRow);
        keyboardRows.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);


    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}

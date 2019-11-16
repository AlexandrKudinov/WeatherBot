import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class Bot extends AbilityBot {

    private static String BOT_NAME = "-------";
    private static String BOT_TOKEN = "------------------------------";

    private static String PROXY_HOST = "-----------" /* proxy host */;
    private static Integer PROXY_PORT = 0 /* proxy port */;


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
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS4);


            // Register your newly created AbilityBot
            Bot bot = new Bot(BOT_TOKEN, BOT_NAME, botOptions);

            //регистрируем бота
            telegramBotsApi.registerBot(bot);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdateReceived(Update update) {  //метод для приема сообщений(для получения обновлений через long pull(очередь ожидающих запросов))
        Message message = update.getMessage(); //получаем текст сообщения из объекта update
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/hello":
                    sendMsg(message, "Hello, i am your personal weatherman!");
                    break;
                default:

            }
        }
    }


    public void sendMsg(Message message, String text) { //метод для отправки сообщений
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true); // включаем возможность разнизки??????????????
        sendMessage.setChatId(message.getChatId().toString()); // присваиваем ID чата для отправки сообщения
        sendMessage.setReplyToMessageId(message.getMessageId());//определяем ID сообщения на которое будем отвечать
        sendMessage.setText(text);

        try {  //устанавливаем непосредственно саму отправку сообщения
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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

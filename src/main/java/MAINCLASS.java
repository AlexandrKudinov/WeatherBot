import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.logging.BotLogger;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;



public class MAINCLASS {

    public static String BOT_NAME = "Weatherman";
    public static String BOT_TOKEN_PROPERTY_NAME = "BOT_TOKEN";
    public static String PROXY_HOST_PROPERTY_NAME = "PROXY_HOST" /* proxy host */;
    public static String PROXY_PORT_PROPERTY_NAME = "PROXY_PORT";

    private static class PropertyManager{
        private int getProxyPort() throws IOException {
            return Integer.parseInt(readProperty("PROXY_PORT"));
        }

        private String getProxyHost() throws IOException {
            return readProperty("PROXY_HOST");
        }

        private String getBotToken() throws IOException {
            return readProperty("BOT_TOKEN");
        }

        private String readProperty(String name) throws IOException {
            Properties properties = new Properties();
            InputStream inputStream =
                    this.getClass().getClassLoader().getResourceAsStream("local.properties");
            properties.load(inputStream);
            return properties.getProperty(name);
        }
    }


    public static void main(String[] args) {
        PropertyManager PropertyManager = new PropertyManager();

        try {
            ApiContextInitializer.init();//инициализируем Api
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(); //создаем объект Api

            //задаем прокси для бота
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost(PropertyManager.getProxyHost());
            botOptions.setProxyPort(PropertyManager.getProxyPort());
            // выбираем тип прокси: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);


            // Register your newly created AbilityBot
            Bot bot = new Bot(PropertyManager.getBotToken(), BOT_NAME, botOptions);

            //регистрируем бота
            telegramBotsApi.registerBot(bot);

        } catch (Exception e) {
            BotLogger.error("BotEngine : There is an exception occurred during Bot initialization.", e);

        }
    }
}

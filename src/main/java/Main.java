import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {
    public static String BOT_PROPERTY_NAME;
    public static String BOT_TOKEN_PROPERTY_NAME;
    public static String API_KEY_PROPERTY_NAME;
    public static String DB_PATH_PROPERTY_NAME;
    public static DefaultBotOptions botOptions;

    private static class PropertyManager {
        private int getProxyPort() throws IOException {
            return Integer.parseInt(readProperty("PROXY_PORT"));
        }

        private String getProxyHost() throws IOException {
            return readProperty("PROXY_HOST");
        }

        private String getBotToken() throws IOException {
            return readProperty("BOT_TOKEN");
        }

        private String getBotName() throws IOException {
            return readProperty("BOT_NAME");
        }

        private String getApiKey() throws IOException {
            return readProperty("API_KEY");
        }

        private String getDBPath() throws IOException {
            Path path = Paths.get(readProperty("DB_PATH"));
            return path.toString();
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
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost(PropertyManager.getProxyHost());
            botOptions.setProxyPort(PropertyManager.getProxyPort());
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
            BOT_TOKEN_PROPERTY_NAME = PropertyManager.getBotToken();
            BOT_PROPERTY_NAME = PropertyManager.getBotName();
            API_KEY_PROPERTY_NAME = PropertyManager.getApiKey();
            DB_PATH_PROPERTY_NAME = PropertyManager.getDBPath();
            telegramBotsApi.registerBot(Bot.getInstance());
        } catch (Exception e) {
            System.out.println("Initialisation Bot exception");
        }
    }
}
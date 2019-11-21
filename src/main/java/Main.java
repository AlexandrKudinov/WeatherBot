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


public class Main {

    public static String BOT_NAME = "-----------";
    public static String BOT_TOKEN = "---------------------";
    public static String PROXY_HOST = "----------" /* proxy host */;
    public static Integer PROXY_PORT = 000000 /* proxy port */;


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

}

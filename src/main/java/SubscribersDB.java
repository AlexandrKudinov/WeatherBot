import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SubscribersDB {

    public static void resetSubDB(Map<Integer, Subscriber> subscriberMap) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(new File(Main.DB_PATH_PROPERTY_NAME)))) {
            for (Subscriber subscriber : subscriberMap.values()) {
                br.write(subscriber.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Reset DB exception");
        }
    }

    public static Map<Integer, Subscriber> getSubscriberMap() {
        Map<Integer, Subscriber> subscriberMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(Main.DB_PATH_PROPERTY_NAME)))) {
            String line;
            while ((line = br.readLine()) != null) {
                subscriberMap.put(fromString(line).getId(), fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Reading from DB exception");
        }
        return subscriberMap;
    }

    public static Subscriber fromString(String string) {
        String[] subscriberToString = string.split(" ");
        Integer id = Integer.parseInt(subscriberToString[0]);
        Long chatId = Long.parseLong(subscriberToString[1]);
        String location = subscriberToString[2];
        return new Subscriber(id, chatId, location);
    }
}
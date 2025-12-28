import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZonesAndZonedDateTime {
    public static void main(String[] args) {
        
        ZonedDateTime now =ZonedDateTime.now();
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        System.out.println("GMT: "+now.withZoneSameInstant(ZoneId.of("GMT")).format(formatter));
        System.out.println("IST: "+now.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).format(formatter));
        System.out.println("PST: "+now.withZoneSameInstant(ZoneId.of("America/Los_Angeles")).format(formatter));
    }
}
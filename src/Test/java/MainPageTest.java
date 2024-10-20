import org.example.Factory;
import org.example.MainPage;
import org.junit.jupiter.api.Test;

public class MainPageTest  extends Factory{


    @Test
    void findCurrentCalendarForSchoolYear(){
        MainPage mainpage = new MainPage(page);

        mainpage.findCalendar();

        System.out.println(mainpage.calendarIsSuccessfullyFound());
    }


}

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryOrderTest {

    private String generateData(int addDays) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void CardDeliveryOrderTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Пермь");
        String currentData = generateData(7);
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id = 'date'] input").sendKeys(currentData);
        $("[data-test-id = 'name'] input").setValue("Петрова Анна Сергеевна");
        $("[data-test-id = 'phone'] input").setValue("+79096745634");
        $("[data-test-id = 'agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentData));
    }

}

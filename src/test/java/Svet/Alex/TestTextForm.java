package Svet.Alex;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class TestTextForm {

    @BeforeAll
    static void beforAll()  {
        Configuration.startMaximized = true;
    }

    @Test
    void fillForm() {

        // Открываем целевую страницу
        open("https://demoqa.com/automation-practice-form");

        // заполнение блока имя фамилия
        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Иванов");

        // Заполнить емал адрес
        $("#userEmail").setValue("test@mail.com");

        // Выбрать пол
        $(byText("Male")).click();

        // Заполнить номер телефона
        $("#userNumber").setValue("7751666666");

        // Блок дата рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("4");
        $(".react-datepicker__year-select").selectOptionByValue("1990");
        $(".react-datepicker__day.react-datepicker__day--013").click();

        // Блок Subject
        $("#subjectsInput").click();
        $("#subjectsInput").sendKeys("Comp");
        $(byText("Computer Science")).click();

        // Выбор хобби
        $(byText("Reading")).click();

        // загрузка картинки
        $("#uploadPicture").uploadFile(new File("resources//t8i0r1.jpg"));

        // Ввод адреса
        $("#currentAddress").setValue("New Yourk, Wall Street 211 B");

        // Выбор штата
        $("#state").click();
        $(byText("Haryana")).click();

        //  Выбор города
        $("#city").click();
        $(byText("Panipat")).click();

        // Сабмитим
        $("#submit").click();

        // Проверка заполнения
        verifyData("Student Name","Иван Иванов");
        verifyData("Student Email","test@mail.com");
        verifyData("Gender","Male");
        verifyData("Mobile","7751666666");
        verifyData("Date of Birth","13 May,1990");
        verifyData("Subjects","Computer Science");
        verifyData("Hobbies","Reading");
        verifyData("Picture","t8i0r1.jpg");
        verifyData("Address","New Yourk, Wall Street 211 B");
        verifyData("State and City","Haryana Panipat");
    }

    void verifyData(String label, String value) {
        $(".table-responsive").$(byText(label)).parent().shouldHave(text(value));

    }



}

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;

import java.util.Scanner;

import java.awt.Toolkit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // 학번과 비밀번호 입력 받기
        Scanner scan = new Scanner(System.in);
        System.out.print("ID를 입력하세요 ");
        String input_id = scan.nextLine();
        System.out.print("PW를 입력하세요 ");
        String input_pw = scan.nextLine();

        // 날짜 입력 받기
        System.out.println("날짜를 입력하세요 ex)2022년 7월 8");
        String input_line = scan.nextLine();
        String input_year_month = input_line.split(" ")[0] + " " + input_line.split(" ")[1];
        String input_day = input_line.split(" ")[2];

        // ChromeDriver 이름과 설치경로
        String WEB_DRIVER_ID = "chromedriver.exe";
        String WEB_DRIVER_PATH = "C:/Sejong-University-Notification-Bot";

        // ChromeDriver 경로 시스템 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 크롬옵션 만들어 주기
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // option 넣고 driver 변수 만들기
        ChromeDriver driver = new ChromeDriver(options);

        // driver 변수 설정
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // URL 접속
        String URL = "http://classic.sejong.ac.kr/userLoginPage.do";
        driver.get(URL);

        // ID PW 로그인 버튼 클릭
        driver.findElement(By.xpath("/html/body/section/div/div[1]/form/input[1]")).sendKeys(input_id);
        driver.findElement(By.xpath("/html/body/section/div/div[1]/form/input[2]")).sendKeys(input_pw);
        driver.findElement(By.xpath("/html/body/section/div/div[1]/form/button")).click();

        // 예약 페이지로 넘어가기
        driver.get("http://classic.sejong.ac.kr/schedulePageList.do?menuInfoId=MAIN_02_04");

        // 달력 클릭
        driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div[2]/form/div[1]/p[1]/input")).click();

        // 년도 월수 일치 하는지 확인 (일치 안하면 1달 뒤로 가기)
        String now_year_month = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/table/thead/tr[1]/th[2]")).getText();
        if (!now_year_month.equals(input_year_month)) {
            driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/table/thead/tr[1]/th[3]")).click();
        }

        // 일치하는 날짜로 이동
        List<WebElement> elements = driver.findElements(By.className("available"));
        for (int index = 0; index < elements.size(); index++) {
            if (input_day.equals(elements.get(index).getText())) {
                elements.get(index).click();
                break;
            }
        }

        // 검사하여 계속 새로고침 하거나 신청하기
        while (true) {
            try {
                elements = driver.findElements(By.className("btn-warning"));
                elements.get(0).click();
                break;
            } catch (Exception e) {
                driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div[2]/form/div[1]/p[1]/span/button")).click();
                System.out.println("시도중");
                continue;
            }
        }

        // 영역명 combo box 클릭
        driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div[2]/form/ul/li[6]/dl/dd/select")).click();
        driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div[2]/form/ul/li[6]/dl/dd/select/option[2]")).click();


        // 도서명 combo box 클릭
        driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div[2]/form/ul/li[7]/dl/dd/span/span[1]/span")).click();

        // 책 갯수 만큼 elements 따오기
        elements = driver.findElements(By.className("select2-results__option"));

        for(int index = 0 ; index<elements.size();index++) {
            // 책 클릭
            elements.get(index).click();
            // 책이 클릭 되었다면 멈추기
            if(!driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div[2]/form/ul/li[7]/dl/dd/span/span[1]/span/span[1]")).getText().isEmpty())
                break;
        }

        // 신청 버튼 클릭
        driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div[2]/form/div/button[1]")).click();
        Thread.sleep(1000);

        // 응시예약을 진행하시겠습니까?
        driver.switchTo().alert().accept();
        Thread.sleep(1000);

        // 예약 신청이 정상적~~
        driver.switchTo().alert().accept();

        // beep 음 10초간 재생
        for(int num =0 ; num <10 ; num++)
        {
            toolkit.beep();
            Thread.sleep(1000);
        }

        // 카톡 알람 가기

    }
}
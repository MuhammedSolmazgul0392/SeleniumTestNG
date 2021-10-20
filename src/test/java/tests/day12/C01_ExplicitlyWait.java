package  tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

public class C01_ExplicitlyWait extends TestBase {
     @Test
    public void implicitlyWaitTest() throws InterruptedException {
         //3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.

         driver.get("https://the-internet.herokuapp.com/dynamic_controls");
         Thread.sleep(2000);
         //4. Remove butonuna basin.

        driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();
         //driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();
         Thread.sleep(2000);
         //5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
         SoftAssert softAssert=new SoftAssert();
         WebElement sonucYazisi= driver.findElement(By.xpath("//p[@id='message']"));
         softAssert.assertTrue(sonucYazisi.isDisplayed());
         softAssert.assertAll();


         //6. Add buttonuna basin
         driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();
         //7. It’s back mesajinin gorundugunu test edin
         SoftAssert softAssert1=new SoftAssert();
         WebElement mesajYazisi= driver.findElement(By.id("message"));
         softAssert1.assertTrue(mesajYazisi.isDisplayed(),"isler yolunda gozukmuyor");
         softAssert1.assertAll();
     }

     @Test
    public void explicitlyWaitTest() {
         //3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.

         driver.get("https://the-internet.herokuapp.com/dynamic_controls");

         //4. Remove butonuna basin.

         driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();
         //driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();

         //5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
         WebDriverWait wait=new WebDriverWait(driver,22);
         //explicitly wait i istersek locate islemi ile birlikte tek satirda yapabilriiz

         WebElement yaziLocateIleBirlikte=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message'])")));
         Assert.assertTrue((yaziLocateIleBirlikte.isDisplayed()));
         // veya once locate edip uygun method kullanarak beklmeyi yaptirabilirz
         WebElement sonucYazisi= driver.findElement(By.xpath("//p[@id='message']"));
         wait.until(ExpectedConditions.visibilityOf(sonucYazisi));
         Assert.assertTrue(sonucYazisi.isDisplayed());


     }

}
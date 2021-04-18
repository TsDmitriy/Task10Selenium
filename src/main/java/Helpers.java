import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {
    /**
     * Метод проверяет видим ли элемент в DOM, если виден то вводит в элемент текст
     * @param by путь до элемента в DOM
     * @param text текст для ввода
     */
    public static void sendKeys(By by, String text) {
        presenceOfElementLocatedAmdFindElement(by).sendKeys(text);
    }

    /**
     * Метод проверяет кликабелен ли элемент, если да, то кликает по нему
     * @param by путь до элемента в DOM
     */
    public static void click(By by) {
        new WebDriverWait(Driver.getInstance(), 30)
                .withMessage("Элемент"+by+"некликабелен")
                .until(ExpectedConditions.elementToBeClickable(by))
                .click();
    }

    /**
     * Метод проверет виден ли элемент в дом, если виден возвращает элемент
     * @param by путь до элемента в DOM
     * @return найденный WebElement
     */
    public static WebElement presenceOfElementLocatedAmdFindElement(By by) {
        new WebDriverWait(Driver.getInstance(), 15)
                .withMessage("Элемент"+by+"не виден в DOM")
                .until(ExpectedConditions.presenceOfElementLocated(by));
        return Driver.getInstance().findElement(by);
    }

    /**
     * Метод проверет виден ли элемент в дом, если виден возвращает элемент
     * @return найденный WebElement
     */
    public static void waitLoadPage() {
        new WebDriverWait(Driver.getInstance(), 30)
                .until(ExpectedConditions
                        .jsReturnsValue("return document.readyState==\"complete\";"));

    }

    /**
     * Метод проверет виден ли элемент в дом, если виден возвращает элемент
     * @param by путь до элемента в DOM
     * @return найденный WebElement
     */
    public static WebElement presenceOfElementLocated(By by) {
        new WebDriverWait(Driver.getInstance(), 15)
                .withMessage("Элемент"+by+"не виден в DOM")
                .until(ExpectedConditions.presenceOfElementLocated(by));
        return Driver.getInstance().findElement(by);
    }
    public static void clickJs(By by) {
        WebElement webElement= Helpers.presenceOfElementLocated(by);
        JavascriptExecutor executor = (JavascriptExecutor) Driver.getInstance();
        executor.executeScript("arguments[0].click();", webElement);
    }

}




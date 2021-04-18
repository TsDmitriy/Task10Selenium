import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductPage extends MainPage{
    private By nameProduct = By.xpath("//*[@id=\"box-product\"]//*[@class=\"title\"]");
    private By priseSale = By.className("campaign-price");
    private By prise = By.xpath("//*[@class=\"regular-price\"]");

    /**
     * Метод проверяет, что на главной странице и на странице товара совпадает текст названия товара
     * @return текущую страницу
     */
    public ProductPage checkNameProduct(){
        Assert.assertEquals("Имена на страницах отличаются",Stash.getValue("name"), Helpers.presenceOfElementLocated(nameProduct).getText());
    return this;
    }
    /**
     * Метод проверяет, что на главной странице и на странице товара совпадает обычная цены
     * @return текущую страницу
     */
    public ProductPage checkPrise(){
        Assert.assertEquals("Обычная цена на страницах отличаются",Stash.getValue("prise"), Helpers.presenceOfElementLocated(prise).getText());
        return this;
    }
    /**
     * Метод проверяет, что на главной странице и на странице товара совпадает акционная цена
     * @return текущую страницу
     */
    public ProductPage checkPriseSale(){
        Assert.assertEquals("Скидочная цена на страницах отличаются",Stash.getValue("priseSale"), Helpers.presenceOfElementLocated(priseSale).getText());
        return this;
    }

    /**
     * Метод проверяет, что обычная цена зачёркнутая и серая
     * @return текущую страницу
     * @throws Exception в случае если обычная цена незачёркнутая или несерая
     */
    public ProductPage checkColorPriseOneProductInProductPage() throws Exception {
        String colorPrise = Helpers.presenceOfElementLocated(prise).getCssValue("color").replaceAll("[^\\d,]", "");
        String lineThrough="";
        if (Helpers.presenceOfElementLocated(prise).getCssValue("text-decoration-line").isEmpty()) {
            lineThrough = Helpers.presenceOfElementLocated(prise).getCssValue("text-decoration");
        }
        else {
            lineThrough = Helpers.presenceOfElementLocated(prise).getCssValue("text-decoration-line");
        }
        String delimeter = ",";
        ArrayList<String> list = new ArrayList((Arrays.asList(colorPrise.split(delimeter))));
        if ((!list.get(0).equals(list.get(1)) | !list.get(0).equals(list.get(2))) | !lineThrough.equals("line-through")) {
            throw new Exception("На странице"+Driver.getInstance().getCurrentUrl()+"Обычная цена не зачёркнутая или не серая");
        }
        return this;
    }
    /**
     * Метод проверяет, что акционная цена жирная и красная
     * @return текущую страницу
     * @throws Exception в случае если акционная цена нежирная и некрасная
     */
    public ProductPage checkColorPriseSaleOneProductInProductPage() throws Exception {
        String colorPriseSale = Helpers.presenceOfElementLocated(priseSale).getCssValue("color").replaceAll("[^\\d,]", "");;;
        String lineThrough2 = Helpers.presenceOfElementLocated(priseSale).getCssValue("font-weight");
        String delimeter = ",";
        ArrayList<String> list = new ArrayList((Arrays.asList(colorPriseSale.split(delimeter))));
        if ((!list.get(1).equals("0") | !list.get(2).equals("0"))| !lineThrough2.equals("700") ) {
            throw new Exception("На странице " + Driver.getInstance().getCurrentUrl() + " акционная цена не жирная или некрасная");
        }
        return this;
    }
    /**
     * Метод проверяет, что акционная цена крупнее, чем обычная
     * @return текущую страницу
     * @throws Exception в случае если обычная цена крупнее, чем акционная
     */
    public ProductPage sizePriseSaleVSPriseInProductPage() throws Exception {
        String sizePrise = Helpers.presenceOfElementLocated(prise).getCssValue("font-size").replaceAll("[^\\d.]", "");
        String sizePriseSale = Helpers.presenceOfElementLocated(priseSale).getCssValue("font-size").replaceAll("[^\\d.]", "");;
        if (Double.parseDouble(sizePriseSale)<Double.parseDouble(sizePrise)) {
            throw new Exception("На странице " + Driver.getInstance().getCurrentUrl() + "  обычная цена крупнее, чем акционная");
        }
        return this;
    }
}

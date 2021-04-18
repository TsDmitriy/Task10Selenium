import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;

public class MainPage {
    public Integer startIndex = 5;
    private static By nameProduct = By.xpath("//*[@id=\"box-campaigns\"]//*[@class=\"name\"][1]");
    private By priseSale = By.xpath("//*[@id=\"box-campaigns\"]//*[@class=\"campaign-price\"]");
    private By prise = By.xpath("//*[@id=\"box-campaigns\"]//*[@class=\"regular-price\"]");

    /**
     * Метод получает текст - имени продукта и сохраняет его в Stash
     * @return текущую страницу
     */
    public MainPage getNameOneProductInCampaigns() {
        String name2 = Helpers.presenceOfElementLocated(nameProduct).getText();
        Stash.put("name", name2);
        return this;
    }

    /**
     * Метод получает обычную цену и сохраняет его в Stash
     * @return текущую страницу
     */
    public MainPage getPriseOneProductInCampaigns() {
        String prise2 = Helpers.presenceOfElementLocated(prise).getText();
        Stash.put("prise", prise2);
        return this;
    }

    /**
     * Метод получает акционную цену и сохраняет его в Stash
     * @return текущую страницу
     */
    public MainPage getPriseSaleOneProductInCampaigns() {
        String priseSale2 = Helpers.presenceOfElementLocated(priseSale).getText();
        Stash.put("priseSale", priseSale2);
        return this;
    }

    /**
     * Метод проверяет, что обычная цена зачёркнутая и серая
     * @return текущую страницу
     * @throws Exception в случае если обычная цена незачёркнутая или несерая
     */
    public MainPage checkColorPriseOneProductInCampaigns() throws Exception {
        String colorPrise = Helpers.presenceOfElementLocated(prise).getCssValue("color").replaceAll("[^\\d,]", "");;
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
            throw new Exception("На странице " + Driver.getInstance().getCurrentUrl() + " обычная цена не зачёркнутая или несерая");
        }
        return this;
    }

    /**
     * Метод проверяет, что акционная цена жирная и красная
     * @return текущую страницу
     * @throws Exception в случае если акционная цена нежирная и некрасная
     */
    public MainPage checkColorPriseSaleOneProductInCampaigns() throws Exception {
        String colorPriseSale = Helpers.presenceOfElementLocated(priseSale).getCssValue("color").replaceAll("[^\\d,]", "");
        String lineThrough2 = Helpers.presenceOfElementLocated(priseSale).getCssValue("font-weight");
        String delimeter = ",";
        ArrayList<String> list = new ArrayList((Arrays.asList(colorPriseSale.split(delimeter))));
        if ((!list.get(1).equals("0") | !list.get(2).equals("0"))| (!lineThrough2.equals("700")&&!lineThrough2.equals("900")) ) {
            throw new Exception("На странице " + Driver.getInstance().getCurrentUrl() + " акционная цена не жирная или некрасная");
        }
        return this;
    }

    /**
     * Метод проверяет, что акционная цена крупнее, чем обычная
     * @return текущую страницу
     * @throws Exception в случае если обычная цена крупнее, чем акционная
     */
    public MainPage sizePriseSaleVSPrise() throws Exception {
        String sizePrise = Helpers.presenceOfElementLocated(prise).getCssValue("font-size").replaceAll("[^\\d.]", "");
        String sizePriseSale = Helpers.presenceOfElementLocated(priseSale).getCssValue("font-size").replaceAll("[^\\d.]", "");;
        if (Double.parseDouble(sizePriseSale)<Double.parseDouble(sizePrise)) {
            throw new Exception("На странице " + Driver.getInstance().getCurrentUrl() + "  обычная цена крупнее, чем акционная");
        }
        return this;
    }

    public ProductPage goToProductPage() {
        Helpers.clickJs(nameProduct);
        return new ProductPage();
    }
}

import org.junit.Test;


public class TestTask10 {


    @Test
    public void test() throws Exception {
        new GoToMainPage()
                .goToMainPage()
                .getNameOneProductInCampaigns()
                .getPriseOneProductInCampaigns()
                .getPriseSaleOneProductInCampaigns()
                .checkColorPriseOneProductInCampaigns()
                .checkColorPriseSaleOneProductInCampaigns()
                .sizePriseSaleVSPrise()
                .goToProductPage()
                .checkNameProduct()
                .checkPrise()
                .checkPriseSale()
                .checkColorPriseOneProductInProductPage()
                .checkColorPriseSaleOneProductInProductPage()
                .sizePriseSaleVSPriseInProductPage();






    }


}
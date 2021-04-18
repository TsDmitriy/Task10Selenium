public class GoToMainPage {
    public MainPage goToMainPage(){
        Driver.getInstance().get("http://localhost/litecart/en/");
        return new MainPage();
    }
}

package stepDefinitions;

import io.cucumber.java.en.Then;

import org.testng.Assert;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import pageObjects.PageObjectManager;
import utils.TestContextSetup;

public class OfferPageStepDefinition {
    public String offerPageProductName;
    TestContextSetup testContextSetup;
    PageObjectManager pageObjectManager;

    // Single responsibility Principle
    // Loosely coupled
    // Factory Design Pattern
    public OfferPageStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @Then("^user searched for (.+) shortname in offers page$")
    public void user_searched_for_same_shortname_in_offers_page(String shortName) {
        try {
            // Offer page -> enter -> grab text
            switchToOffersPage();
            OffersPage offersPage = testContextSetup.pageObjectManager.OffersPage();
            offersPage.searchItem(shortName);
            Thread.sleep(2000);
            offerPageProductName = offersPage.getProductName();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchToOffersPage() {
        // If switched to offer page -> skip below part
        LandingPage landingPage = testContextSetup.pageObjectManager.getLandingPage();
        landingPage.selectTopDealsPage();
        testContextSetup.genericUtils.SwitchWindowToChild();
        // Explicit wait, parse string
    }

    @Then("validate product name in offers page matches with Landing Page")
    public void validate_product_name_in_Offers_page() {
        Assert.assertEquals(offerPageProductName, testContextSetup.landingPageProductName);
    }
}

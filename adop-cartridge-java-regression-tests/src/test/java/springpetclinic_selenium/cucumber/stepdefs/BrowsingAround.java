package springpetclinic_selenium.cucumber.stepdefs;

import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.PendingException;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import springpetclinic_selenium.utils.Configure;
import springpetclinic_selenium.utils.DriverFactory;

public class BrowsingAround {

	Configure config = DriverFactory.getConfig();
	HtmlUnitDriver driver = DriverFactory.getDriver();

	@Given("^I am on the home page$")
	public void i_am_on_the_home_page() throws Throwable {
		if (config.getZapEnabled()) {
			driver.setProxy(config.getZapIp(), config.getZapPort());
		}
		driver.get(config.getPetClinicUrl());
	}

	@Then("^I should see \"(.*?)\"$")
	public void i_should_see(String arg1) throws Throwable {
		assertTrue(driver.getPageSource().contains(arg1));
	}

	@Given("^I follow \"(.*?)\"$")
	public void i_follow(String arg1) throws Throwable {
		driver.get(config.getPetClinicUrl() + arg1);
		assertTrue(driver.getCurrentUrl().equals(config.getPetClinicUrl() + arg1));
	}

	@Then("^I should be on the vets, \"(.*?)\", page$")
	public void i_should_be_on_the_vets_page(String arg1) throws Throwable {
		assertTrue(driver.findElementByXPath("/html/body/div/h2").getText().equals(arg1));
	}

	@Then("^I should see \"(.*?)\" within h2$")
	public void i_should_see_within(String arg1) throws Throwable {
		assertTrue(driver.findElementByXPath("/html/body/div/h2").getText().equals(arg1));
	}

	@When("^I fill in \"(.*?)\" with \"(.*?)\"$")
	public void i_fill_in_with(String arg1, String arg2) throws Throwable {
		driver.findElementByXPath("//input[@id='" + arg1 + "']").sendKeys(arg2);
		assertTrue(driver.findElementByXPath("//input[@id='" + arg1 + "']").getAttribute("id").equals(arg1));
	}

	@When("^I press \"(.*?)\"$")
	public void i_press(String arg1) throws Throwable {
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		for (WebElement webElement : buttons) {
			webElement.submit();
			assertTrue(true);
		}
	}

	@Given("^I login to \"(.*)\" application$")
	public void i_login_and_land_on_the_home_page(String applicationName) throws Throwable {
		if (StringUtils.isNotBlank(applicationName)) {
			if (config.getZapEnabled()) {
				driver.setProxy(config.getZapIp(), config.getZapPort());
			}
			driver.get(config.getPetClinicUrl());
		}
	}

	@And("^I land on the \"(.*)\" home page")
	public void i_verify_string_element_is_visible(String applicationName) {
		if (StringUtils.isNotBlank(applicationName)) {
			assertTrue(driver.getCurrentUrl().equalsIgnoreCase(new Configure().getPetClinicUrl() + "/"));
			assertTrue(driver.getTitle().equalsIgnoreCase("PetClinic :: a Spring Framework demonstration"));
		}
	}

	@Then("^I search for \"(.*)\"$")
	public void i_search_for_sring(String elementName) {
		if (StringUtils.isNotBlank(elementName)) {
			driver.get(config.getPetClinicUrl() + "/vets.html");
		}
		assertTrue(driver.getCurrentUrl().equals(config.getPetClinicUrl() + "/vets.html"));
		assertTrue(driver.findElement(By.xpath("/html/body/div/h2")).getText().equalsIgnoreCase(elementName));
	}



	@And("^I get a list of \"(.*)\"$")
	public void i_get_a_list_of_string(List<String> elementsList) {
		if (!elementsList.isEmpty()) {
			List<WebElement> veterinariansl=driver.findElements(By.xpath("//*[@id=\"vets\"]"));
			assertTrue(!veterinariansl.isEmpty());
		}
	}

	@And("^I search owner \"(.*)\"$")
	public void i_search_owner_string(String ownerName) throws Throwable {
		driver.get(config.getPetClinicUrl() + "/owners/find.html");
		assertTrue(driver.getCurrentUrl().equals(config.getPetClinicUrl() + "/owners/find.html"));
		WebElement search = driver.findElement(By.xpath("//*[@name='lastName']"));
		System.out.println("Element is :" + search.getText());
		search.sendKeys(ownerName);
		driver.findElement(By.xpath("//*[@id=\"search-owner-form\"]/fieldset/div[2]/button")).click();
	}

	@And("^I get owner \"(.*)\" Informations")
	public void i_get_owner_string_informations(String arg0) throws Throwable {
		String ownersName = driver.findElement(By.xpath("/html/body/div/table[1]/tbody/tr[1]/td/b")).getText();
		System.out.println("Owners name is: " + ownersName);
		assertTrue(ownersName.contains(arg0));
	}


}
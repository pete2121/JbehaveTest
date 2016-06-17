package FunctionLibrary;

//import com.publicsite.tools.ObjectMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ObjectRepository.BOX1Objects;

//FIRST STEP IS TO CREATE A SUPERCLASS THAT CONTAINS THE COMMON METHODS
public class Library implements BOX1Objects  { // The superClass (or in general the abstact class)

	protected WebDriver driver; // Visible to the package and all subclasses
								// (this means protected san public ok). Note
								// that we have also: Visible to the class only
								// (private). Visible to the world (public).

	// Constructor of PageBase
	public Library(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	// All the CSS
	@FindBy(css = "BODY")
	WebElement page_source;

	public String getPageSource() {
		return page_source.getText();
	}

	// Page Object design pattern.
	// This pattern uses page objects that represent a web page (or a form
	// within a page, if applicable) to separate test code (validations and test
	// flow logic, for example) from page specific code.
	// It does so by making all actions that can be performed on a page
	// available as methods of the page object representing that page.

	public void CheckLabel(String labelExpected, String id) {

		Assert.assertEquals(labelExpected, driver.findElement(By.id(id))
				.getText());
		// System.out.println("Actual text is :"+ ActualText);
	}

	public void TearDown() {
		driver.close();
		driver.quit();
	}

	public void logout() throws Exception {

		if (driver.findElement(By.id("logoutLink")).isDisplayed())

		{
			WebElement Logout_button = driver.findElement(By.id("logoutLink"));
			Logout_button.click();
			Thread.sleep(1000);

		}
	}

	public void ClickButton(String buttonIdentifier) {

		WebElement button = driver.findElement(By.id(buttonIdentifier));
		button.click();
	}

	public void ClickButtonbyText(String text) {

		driver.findElement(
				By.xpath("//button[contains(text(),'" + text + "')]")).click();

	}

	public void ClickModalButtonSave() {

		driver.findElement(
				By.cssSelector("#decisionForm > div.modal-footer > button.btn.btn-primary"))
				.click();

	}

	public void ClickModalButtonCancel() {

		driver.findElement(
				By.cssSelector("div.modal-footer > button.btn.btn-default"))
				.click();
	}

	public void buttonrows() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElement(By.xpath("(//button[@type='button'])[18]")).click();

	}

	public void ClickLink(String LinkIdentifier) {

		WebElement Link = driver.findElement(By.linkText(LinkIdentifier));
		Link.click();

	}

	public void ClickLink2(String LinkIdentifier) throws InterruptedException {

		// WebElement Link = driver.findElement((
		// By.className(LinkIdentifier)));
		// Link.click();
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		for (WebElement link : allLinks) {
			String links = link.getText();
			System.out.println(links);
			if (links == "European Commission")
				;
			{
				System.out.println("mia zwh aek");
				WebElement LinktoClick = driver.findElement(By.linkText(links));
				LinktoClick.click();
				break;

				// Thread.sleep(1000);
			}

		}

	}

	// Linkarray.add(links );

	// }
	// for (String linkToTest : Linkarray){
	// driver.findElement(By.linkText(linkToTest)).click();

	public boolean IsButtonEnabled(String buttonIdentifier) {

		WebElement button = driver.findElement(By.id(buttonIdentifier));

		if (button.getAttribute("class").contains("disabled"))

		{
			return false;

		} else {
			return true;

		}

	}

	public void CheckButtonState(String buttonIdentifier,
			Boolean buttonEnabledExpected) {

		Boolean buttonEnabled = IsButtonEnabled(buttonIdentifier);

		Assert.assertEquals(buttonEnabled, buttonEnabledExpected);

		// if (buttonEnabled == buttonEnabledExpected) {
		// System.out.println("Valid Button State for button :"
		// + buttonIdentifier + "is" + buttonEnabledExpected
		// + "as expected");

		// }

		// else {
		// System.out.println("Invalid Button State for button:"
		// + buttonIdentifier + "expected as" + buttonEnabledExpected
		// + "instead is" + buttonEnabled);
		// }

	}

	public int GetResultsTableRowsNo(String tableIdentifier) {
		int rows;

		if (tableIdentifier == null) {
			tableIdentifier = "abstractTable";
		}
		WebElement table = driver.findElement(By.id(tableIdentifier));
		List<WebElement> tr_collection = table.findElements(By.tagName("tr"));
		// System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
		rows = tr_collection.size();

		return rows;

	}

	public void CheckResultsTableRowsNo(String tableIdentifier,
			int rowsNoExpected) {
		if (tableIdentifier == null) {
			tableIdentifier = "abstractTable";
		}

		int rowscount = GetResultsTableRowsNo(tableIdentifier);

		Assert.assertEquals(rowscount, rowsNoExpected);

		// if (rowscount == rowsNoExpected) {

		// System.out.println("Valid numbers of Rows" + rowsNoExpected
		// + "as expected");
		// }

		// else {

		// System.out.println("Invalid numbers of Rows. Expected:"
		// + rowsNoExpected + "instead is" + rowscount);
		// }

	}

	public void SelecTableRow(String tableIdentifier, int RowNumber,
			boolean doubleclick) throws Exception {

		if (tableIdentifier == null) {
			tableIdentifier = "abstractTable";
		}
		WebElement table = driver.findElement(By.className(tableIdentifier));
		// get all rows
		List<WebElement> allRows = table.findElements(By.tagName("tr"));

		WebElement Row = allRows.get(RowNumber);
		System.out.println(Row);

		if (doubleclick = true) {

			Actions action = new Actions(driver);
			// Mouse over
			action.moveToElement(Row);
			action.perform();
			// Double click
			action.doubleClick(Row);
			action.perform();

		} else {
			Row.click();

		}
	}
	
	
	
	public void SelecTableRowbyID(String tableIdentifier, int RowNumber,
			boolean doubleclick) throws Exception {

		if (tableIdentifier == null) {
			tableIdentifier = "abstractTable";
		}
		WebElement table = driver.findElement(By.id(tableIdentifier));
		// get all rows
		List<WebElement> allRows = table.findElements(By.tagName("tr"));

		WebElement Row = allRows.get(RowNumber);
		System.out.println(Row);

		if (doubleclick = true) {

			Actions action = new Actions(driver);
			// Mouse over
			action.moveToElement(Row);
			action.perform();
			// Double click
			action.doubleClick(Row);
			action.perform();

		} else {
			Row.click();

		}
	}
	
	

	public void CheckDetailAsText(String detailIdentifier,
			String contentExpected) {

		WebElement content = driver.findElement(By.id(detailIdentifier));
		String actualcontent = content.getText();

		Assert.assertEquals(contentExpected, actualcontent.trim());
	}

	public void SetDetailAsCheckBox(String detailIdentifier, boolean toCheck) {

		WebElement checkbox = driver.findElement(By.id(detailIdentifier));

		if (toCheck != checkbox.isSelected()) {
			checkbox.click();
		}

	}

	public void SelectCheckBox(String name, String value) {

		List<WebElement> oCheckBox = driver.findElements(By.name(name));
		int iSize = oCheckBox.size();

		// Start the loop from first check-box to last check-box
		for (int i = 0; i < iSize; i++) {
			// Store the check-box name to the string variable, using 'Value'
			// attribute
			String sValue = oCheckBox.get(i).getAttribute("value");
			System.out.println(sValue);

			// Select the check-box it the value of the check-box is same what
			// you are looking for

			if ((sValue.trim()).equalsIgnoreCase(value.trim())) {

				oCheckBox.get(i).click();
				break;
			}
		}
	}

	public void Type(String detailIdentifier, String text) {

		driver.findElement(By.name(detailIdentifier)).clear();
		WebElement editbox = driver.findElement(By.name(detailIdentifier));
		editbox.sendKeys(text);

	}

	public void TypebyText(String detailIdentifier, String text) {

		driver.findElement(By.id(detailIdentifier)).clear();
		WebElement editbox = driver.findElement(By.id(detailIdentifier));
		editbox.sendKeys(text);

	}

	public void WaitUntilElement(String detailIdentifier, int timeout) {

		WebDriverWait wait = (new WebDriverWait(driver, timeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.id(detailIdentifier)));
	}

	public void WaitUntiText(String detailIdentifier, int timeout, String text) {

		WebDriverWait wait = (new WebDriverWait(driver, timeout));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.id(detailIdentifier), text));

	}

	public void waitForTextPresent(final String text) {
		(new WebDriverWait(getDriver(), 10))
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						return getPageSource().contains(text);
					}
				});
	}

	public void CreateScreenshot(String location) {

		// generate screenshot as a file object
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			// copy file object to designated location
			FileUtils.copyFile(scrFile, new File(location));
		} catch (IOException e) {
			System.out.println("Error while generating screenshot:\n"
					+ e.toString());
		}
	}

	public void RightClickonElement(String detailIdentifier) throws Exception {

		WebElement Element = driver.findElement(By.id(detailIdentifier));
		Actions builder = new Actions(driver);
		Action rightclick = builder.contextClick(Element).build();
		rightclick.perform();

		Thread.sleep(8000);
	}

	// Paizei wraia!!
	public void UploadFile(String filepath) throws Exception {

		WebElement fileInput = driver.findElement(By
				.xpath("//input[@type='file']"));
		fileInput.sendKeys(filepath);

		Thread.sleep(2000);

	}

	public void selectfromDropDownList(String detailIDIdentifier, String text,
			String value) {

		Select droplist = new Select(driver.findElement(By
				.id(detailIDIdentifier)));

		// Choose to Select by Text or by Value of the drop-down list
		if (text == null && value != null) {
			droplist.selectByValue(value);
		}

		else if (text != null && value == null) {
			droplist.selectByVisibleText(text);

		}

		else {
			throw new AssertionError(
					"Cannot be both parameters null. Give text or value");
		}

	}

	public void selectfromDropDownListbyName(String detailIDIdentifier,
			String text, String value) {

		Select droplist = new Select(driver.findElement(By
				.name(detailIDIdentifier)));

		// Choose to Select by Text or by Value of the drop-down list
		if (text == null && value != null) {
			droplist.selectByValue(value);
		}

		else if (text != null && value == null) {
			droplist.selectByVisibleText(text);

		}

		else {
			throw new AssertionError(
					"Cannot be both parameters null. Give text or value");
		}

	}

	public void SelectRadioButton(String name, String value) {
		List<WebElement> oRadioBox = driver.findElements(By.name(name));
		int iSize = oRadioBox.size();

		// Start the loop from first Radio-box to last check-box
		for (int i = 0; i < iSize; i++) {
			// Store the Radio-box name to the string variable, using 'Value'
			// attribute
			String sValue = oRadioBox.get(i).getAttribute("value");

			// Select theRadio-box it the value of the Radio-box is same what
			// you are looking for

			if (sValue.equalsIgnoreCase(value)) {

				oRadioBox.get(i).click();
				break;
			}
		}
	}

	// There are three possible reasons why your images are not showing up on
	// your pages as expected:
	// (1) The image file is not located in the same location that is specified
	// in your IMG tag
	// (2) The image does not have the same file name as specified in your tag
	// and
	// (3) The image file is either corrupted or damaged.
	public void VerifyallImagesofPage() {
		List<WebElement> allImages = driver.findElements(By.tagName("img"));
		int countBrokenImages = 0;
		List<String> BrokenimageUrl = new ArrayList<String>();

		String script = "return (typeof arguments[0].naturalWidth!=\"undefined\" && arguments[0].naturalWidth>0)";

		for (WebElement image : allImages) {

			Object imgStatus = ((JavascriptExecutor) driver).executeScript(
					script, image);
			if (imgStatus.equals(false)) {
				String currentImageUrl = image.getAttribute("src");
				String imageUrl = currentImageUrl;
				BrokenimageUrl.add(imageUrl);
				countBrokenImages++;
			}
		}

		// Printing the src of the broken images if any
		System.out.println("Number of broken images found in the page : "
				+ countBrokenImages);
		for (String z : BrokenimageUrl) {
			System.out.println(z);

		}

	}

	// Give the expected color into Hex Format
	public void CheckElementColor(String detailIdentifier, String ExpectedColor) {

		WebElement element = driver.findElement(By.id(detailIdentifier));
		String ActualColor = element.getCssValue("color");

		// Convert RGBA to HEX Format
		String[] numbers = ActualColor.replace("rgba(", "").replace(")", "")
				.split(",");
		int r = Integer.parseInt(numbers[0].trim());
		int g = Integer.parseInt(numbers[1].trim());
		int b = Integer.parseInt(numbers[2].trim());
		// System.out.println("r: " + r + " g: " + g + " b: " + b);

		String hexActual = "#" + Integer.toHexString(r)
				+ Integer.toHexString(g) + Integer.toHexString(b);
		// System.out.println(hex);
		//

		Assert.assertEquals(ExpectedColor, hexActual);

	}

	public void ClickSaveButton() {
		WebElement button = driver.findElement(By.name("dispatch"));
		button.click();
	}

	public void CheckMessage(String MessageExpected, String ClassName) {

		WebElement MessageActual = driver.findElement(By.className(ClassName));
		String ActualMessage = MessageActual.getText();
		Assert.assertEquals(MessageExpected, ActualMessage.trim());
	}

	public void CheckMessageContains(String MessageExpected, String ClassName) {

		WebElement MessageActual = driver.findElement(By.className(ClassName));
		String ActualMessage = MessageActual.getText();
		Assert.assertTrue(ActualMessage.contains(MessageExpected));

	}

	public void CheckMessageByIdContains(String MessageExpected, String id) {

		WebElement MessageActual = driver.findElement(By.id(id));
		String ActualMessage = MessageActual.getText();
		Assert.assertTrue(ActualMessage.contains(MessageExpected));

	}

	public void CheckLabelIndex(String labelExpected, String className,
			int Index) {

		List<WebElement> Elements = driver
				.findElements(By.className(className));

		String labelActual = Elements.get(Index).getText();

		Assert.assertEquals(labelExpected.trim(), labelActual.trim());

	}

	public void CheckElementExistance(String detailIdentifier) {
		driver.findElement(By.id(detailIdentifier)).isDisplayed();
	}

	
	public void CheckElementExistance2(String detailIdentifier) {
		driver.findElement(By.xpath(detailIdentifier)).isDisplayed();
	}
	
	
	public void ClickGoogleImage() {
		WebElement GoogleImage = driver.findElement(By
				.xpath("//div/a/img[@alt='Google']"));
		GoogleImage.click();

	}

	public void SwitchToNewWindow(String Name) {
		driver.switchTo().window(Name);
	}

	public void CloseWindow() {
		driver.close();
	}

	public void CloseSession() {
		driver.quit();

	}

	public void handleMultipleWindows(String windowTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				return;
			}
		}
	}

	public void VerifyTextPresentsAtPage(String text) {

		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains(text));

	}

}







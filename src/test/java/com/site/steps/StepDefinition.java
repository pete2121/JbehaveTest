package com.site.steps;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.WebDriver;

import com.site.stories.*;

import ObjectRepository.BOX1Objects;


public class StepDefinition extends RunTest implements BOX1Objects {
	
	
	@Given("user is in $box")
	public void Userlocated(String box) throws Exception
	{
	String identifier = reflectionParser.getValuebyFieldName(box);
	library.CheckElementExistance2(identifier);
	}
	
	@When("user navigates to chesar")
	public void IOpen() throws Exception
	{
	driver.get("http://localhost:8010/index.html#/login");
	}
	
	@When("user clicks on $button")
	public void IClickOnTheButton(String button) throws Exception
	{
	String identifier = reflectionParser.getValuebyFieldName(button);
	library.ClickButtonbyText(identifier);
	}
	
	@When("user enters $detailIdentifier as '$text'")
	public void IEnter(String detailIdentifier, String text) throws Exception
	{
	String identifier = reflectionParser.getValuebyFieldName(detailIdentifier);
	library.Type(identifier, text);
	}
	
	
	@Then("user should see the $detailIdentifier as '$lableExpected'")
	public void IShouldSee(String detailIdentifier, String lableExpected) throws Exception
	{
	String identifier = reflectionParser.getValuebyFieldName(detailIdentifier);
	library.CheckLabel(lableExpected, identifier);
	}
	
}



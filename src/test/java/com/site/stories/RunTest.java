package com.site.stories;

import java.util.Arrays;
import java.util.List;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories; //In order to use clear Junit and NOT Serenity, you should extend JunitStories instead SerenityStories
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.jbehave.core.reporters.Format.CONSOLE;  
import static org.jbehave.core.reporters.Format.TXT; 
import static org.jbehave.core.reporters.Format.HTML; 
import static org.jbehave.core.reporters.Format.STATS; 
import net.serenitybdd.jbehave.SerenityStories; //In order to use Serenity you should extend SerenityStories instead JunitStories


import com.site.steps.StepDefinition;
import com.site.tools.ReflectionParser;
import FunctionLibrary.Library;



public class RunTest extends SerenityStories{
	
	protected Library library = new Library(driver);
	protected ReflectionParser reflectionParser= new ReflectionParser(); 
	protected  static WebDriver driver = new FirefoxDriver();
	
	
	  @Override
	   public Configuration configuration() {  
	     return super.configuration()  
	         .useStoryReporterBuilder(  
	             new StoryReporterBuilder()  
	                 .withDefaultFormats()  
	                 .withFormats(CONSOLE, TXT, HTML, STATS));  
	   }  
	  
	  
	   
	// Here we specify the steps classes  
	   @Override  
	   public InjectableStepsFactory stepsFactory() {  
	     return new InstanceStepsFactory(configuration(), new StepDefinition());  
	   }  
	// Here we specify the stories
	  @Override
	public List<String> storyPaths(){
	 return Arrays.asList("com/site/stories/LogInSuccessfully.story",
	  		"com/site/stories/ImportIUCLIDSubstance.story");
	 }
	
	}





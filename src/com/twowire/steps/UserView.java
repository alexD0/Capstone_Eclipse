package com.twowire.steps;


import static junit.framework.Assert.assertTrue;
import cuke4duke.Given;
import cuke4duke.StepMother;
import cuke4duke.Steps;
import cuke4duke.Then;
import cuke4duke.When;

public class UserView extends Steps {
	
	
	public UserView(StepMother stepMother) {
		super(stepMother);
	}
/*	
    @Transform
    public User transformStringToUserWithAge(String age) {
        return new User(Integer.valueOf(age));
    }
    
    @Transform
    public boolean overrideBooleanPrimitiveTransform(String boolValue) {
        return boolValue.equals("yes") ? true : false;
    }
*/
    @Given("^I am a valid user$")
    public void iAmAValidUser() {
    }

    @When("^I submit my login information$")
    public void iSubmitMyLoginInformation() {
    }
    
    @Then("^I should see my pending tickets$")
    public void iShouldSeeMyPendingTickets() {
    	assertTrue(true);
    } 

    
}

package io.github.bonigarcia;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * (C) Copyright 2020 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


public class BlazeDemoSteps {

    private WebDriver webDriver;


    // Scenario 1

    @Given("that I'm on {string}")
    public void that_i_m_on(String url) {
        webDriver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        webDriver.get(url);
    }

    @When("I select {string} as departure city")
    public void i_select_as_departure_city(String string) {
        WebElement dropdown = webDriver.findElement(By.name("fromPort"));
        dropdown.findElement(By.xpath("//option[. = '" + string + "']")).click();

    }

    @When("I select {string} as destination  city")
    public void i_select_as_destination_city(String string) {
        WebElement dropdown = webDriver.findElement(By.name("toPort"));
        dropdown.findElement(By.xpath("//option[. = '" + string + "']")).click();

    }

    @When("I click the button fo find flights")
    public void i_click_the_button_fo_find_flights() {
        webDriver.findElement(By.xpath("/html/body/div[3]/form/div/input")).click();
    }

    @Then("I should be redirected to {string}")
    public void i_should_be_redirected_to(String string) {
        assertEquals(webDriver.getCurrentUrl(), string);

    }


    // Scenario 2

    @When("I click the button to choose flight")
    public void i_click_the_button_to_choose_flight() {
        webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/input")).click();

    }

    @When("I fill the form with my personal information")
    public void i_fill_the_form_with_my_personal_information() {
        webDriver.findElement(By.id("inputName")).sendKeys("Lucas Sousa");
        webDriver.findElement(By.id("address")).sendKeys("Random St");
        webDriver.findElement(By.id("city")).sendKeys("Random City");
        webDriver.findElement(By.id("state")).sendKeys("Random State");
        webDriver.findElement(By.id("zipCode")).sendKeys("Lucas Sousa");
        webDriver.findElement(By.name("cardType")).findElement(By.xpath("//*[@id=\"cardType\"]/option[1]")).click();
        ;
        webDriver.findElement(By.id("creditCardNumber")).sendKeys("1234432112344321");
        webDriver.findElement(By.id("nameOnCard")).sendKeys("Lucas Sousa");
    }

    @When("click the button to purchase the flight")
    public void click_the_button() {
        webDriver.findElement(By.xpath("/html/body/div[2]/form/div[11]/div/input")).click();

    }

}

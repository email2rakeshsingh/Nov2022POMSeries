package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class CartPage {

	private By cart = By.id("page-cart");
	private By cart1 = By.id("page item");
	private By cart2 = By.xpath("Rakesh.singh//rws");

	public void cartPageRakesh() {
		
		System.out.println("working on cart pages");
		System.out.println("Click on :+ Cart");
	}

}

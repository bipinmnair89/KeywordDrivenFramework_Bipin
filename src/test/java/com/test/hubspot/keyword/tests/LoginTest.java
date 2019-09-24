package com.test.hubspot.keyword.tests;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.test.hubspot.keyword.engine.KeywordEngine;

public class LoginTest {
	
	public KeywordEngine engine;
	
	@Test
	public void load() throws IOException, EncryptedDocumentException, InvalidFormatException
	{
		engine=new KeywordEngine();
		engine.startExecution("Login");
	}

}

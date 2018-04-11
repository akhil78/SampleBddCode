package springpetclinic_selenium.utils;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DriverFactory {

    private static Configure config = new Configure();

    //private HtmlUnitDriver driver = new HtmlUnitDriver();
    private static HtmlUnitDriver driver = new HtmlUnitDriver() {
        protected WebClient modifyWebClient(WebClient client) {
            // This class ships with HtmlUnit itself
            DefaultCredentialsProvider creds = new DefaultCredentialsProvider();

            // Set some example credentials
            creds.addCredentials(config.getPetClinicUsername(), config.getPetClinicPassword());

            // And now add the provider to the webClient instance
            client.setCredentialsProvider(creds);

            return client;
        }
    };

    public  static HtmlUnitDriver getDriver()
    {return driver;
    }

    public  static Configure getConfig()
    {return config;
    }

}
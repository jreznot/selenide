package grid;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.webdriver.WebDriverBinaryManager;
import integration.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.grid.Main;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.openqa.selenium.net.PortProber.findFreePort;

abstract class AbstractGridTest extends IntegrationTest {
  int hubPort;

  @BeforeEach
  final void setUpGrid() throws Exception {
    closeWebDriver();

    hubPort = findFreePort();
    Main.main(new String[]{"standalone", "--port", String.valueOf(hubPort)});

    new WebDriverBinaryManager().setupBinaryPath(new Browser(Configuration.browser, Configuration.headless));
  }

  @AfterEach
  final void tearDownGrid() {
    closeWebDriver();
  }
}

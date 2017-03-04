package br.com.gcs.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class Utils {

	public static WebDriver selecionarNavegador(String browser) {
		try {
			WebDriver driver = null;
			switch (browser) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "drivers/win/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "drivers/win/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				break;
			default:
				break;
			}
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static final String PROPERTIES = "/config.properties";

	public static String propriedades(String proprety) {
		String value = null;
		try {
			Properties properties = new Properties();
			properties.load(Utils.class.getResourceAsStream(PROPERTIES));
			// properties.load(new FileInputStream(new
			// File("C:/Users/Murilo/Desktop/config.properties")));
			value = properties.getProperty(proprety);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String capturarEvidencia(WebDriver driver, String screenShotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = Utils.propriedades("screenPath") + screenShotName + ".png";
			File destino = new File(dest);
			FileUtils.copyFile(source, destino);
			System.out.println("PRINT");
			return dest;
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	public static void AnexarEvidenciaFalha(ITestResult result, WebDriver driver, ExtentTest logger,
			ExtentReports reports, String resultadoEsperado) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShot_path = capturarEvidencia(driver, result.getName());
			String image = logger.addScreenCapture(screenShot_path);
			logger.log(LogStatus.FAIL, "ESPERADO: " + resultadoEsperado, image);
		}
		reports.endTest(logger);
		reports.flush();
	}

	public static String dataHoraAtual() {
		DateFormat dateFormat = new SimpleDateFormat(" dd-MM-yyyy HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private static int randomiza(int n) {
		int ranNum = (int) (Math.random() * n);
		return ranNum;
	}

	private static int mod(int dividendo, int divisor) {
		return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
	}

	private static boolean comPontos = true;

	public static String cpf() {
		int n = 9;
		int n1 = randomiza(n);
		int n2 = randomiza(n);
		int n3 = randomiza(n);
		int n4 = randomiza(n);
		int n5 = randomiza(n);
		int n6 = randomiza(n);
		int n7 = randomiza(n);
		int n8 = randomiza(n);
		int n9 = randomiza(n);
		int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

		d1 = 11 - (mod(d1, 11));

		if (d1 >= 10)
			d1 = 0;

		int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

		d2 = 11 - (mod(d2, 11));

		String retorno = null;

		if (d2 >= 10)
			d2 = 0;
		retorno = "";

		if (comPontos)
			retorno = "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;
		else
			retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

		return retorno;
	}

	public static String gerarCaracteres() {
		int qtdeMaximaCaracteres = 6;
		String[] caracteres = { "a", "1", "b", "2", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B",
				"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z" };
		StringBuilder senha = new StringBuilder();
		for (int i = 0; i < qtdeMaximaCaracteres; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			senha.append(caracteres[posicao]);
		}
		return senha.toString();
	}

	public static String gerarNumero() {
		int qtdCaracteres = 4;
		String[] numeros = { "0", "2", "3", "4", "5", "6", "7", "8", "9" };
		StringBuilder c = new StringBuilder();
		for (int i = 0; i < qtdCaracteres; i++) {
			int posicao = (int) (Math.random() * numeros.length);
			c.append(numeros[posicao]);
		}
		return c.toString();

	}

	public String dados(int nunAba, int linha, int coluna) {
		try {
			File arquivo = new File(propriedades("planilhaDados"));
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setEncoding("Cp1252");
			Workbook planilha;
			planilha = Workbook.getWorkbook(arquivo, workbookSettings);
			Sheet aba = planilha.getSheet(nunAba - 1);
			return aba.getCell(coluna - 1, linha - 1).getContents().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao ler planilha: " + e.getMessage();
		}

	}

	public static void main(String[] args) {
		try {
			Utils u = new Utils();

			System.out.println(u.dados(0, 2, 4));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

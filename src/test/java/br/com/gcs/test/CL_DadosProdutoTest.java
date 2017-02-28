package br.com.gcs.test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import br.com.gcs.steps.CL_DadosProduto;
import br.com.gcs.util.Utils;
import br.com.gcs.util.VideoRecord;

public class CL_DadosProdutoTest {

	private WebDriver driver;
	private CL_DadosProduto dp;
	private ExtentReports reports;
	private ExtentTest logger;
	private String resultadoEsperado;
	private VideoRecord video = new VideoRecord();
	Utils u = new Utils();

	@BeforeClass
	public void preCondicao() throws Exception {
		driver = Utils.selecionarNavegador(Utils.propriedades("browser"));
		reports = new ExtentReports(Utils.propriedades("reportPath"), false);
		reports.config().documentTitle("CAP LEAO").reportName("GCS").reportHeadline("Cap Leao");
		video.inciarGravacao("Dados do Produto");
		dp = new CL_DadosProduto(driver);
	}

	@Test(enabled = true, priority = 1)
	public void validarCamposObrigatorios() throws Exception {
		logger = reports.startTest("CT: " + u.dados(2, 1));
		logger.assignCategory("Dados do Produto");
		dp.avancar();
		assertEquals(u.dados(2, 4), dp.recuperarAlerta());
		Thread.sleep(2000);
		dp.selecionarValorTitulo("R$ " + u.dados(3, 2) + ",00");
		dp.avancar();
		assertEquals(u.dados(3, 4), dp.recuperarAlerta());
		Thread.sleep(2000);
		logger.log(LogStatus.PASS, null);
	}

	@Test(enabled = true, priority = 2)
	public void validarValorMesalidade() throws Exception {
		logger = reports.startTest("CT: " + u.dados(5, 1));
		logger.assignCategory("Dados do Produto");
		dp.selecionarValorTitulo("R$ " + u.dados(5, 2) + ",00");
		dp.selecionarQtdTitulo(Integer.parseInt(u.dados(5, 3)));
		assertEquals("R$ " + u.dados(5, 4) + ",00", dp.recuperarTotal());
		Thread.sleep(1000);
		dp.selecionarQtdTitulo(Integer.parseInt(u.dados(6, 3)));
		assertEquals("R$ " + u.dados(6, 4) + ",00", dp.recuperarTotal());
		Thread.sleep(1000);
		dp.selecionarQtdTitulo(Integer.parseInt(u.dados(7, 3)));
		assertEquals("R$ " + u.dados(7, 4) + ",00", dp.recuperarTotal());
		logger.log(LogStatus.PASS, null);
	}

	@Test(enabled = true, priority = 3)
	public void avancarProximaAba() throws InterruptedException {
		logger = reports.startTest("CT: " + u.dados(9, 1));
		logger.assignCategory("Dados do Produto");
		dp.selecionarValorTitulo("R$ " + u.dados(9, 2) + ",00");
		dp.selecionarQtdTitulo(Integer.parseInt(u.dados(9, 3)));
		Thread.sleep(1500);
		dp.avancar();
		logger.log(LogStatus.PASS, null);
	}

	@AfterMethod
	public void posCondicao(ITestResult result) throws Exception {
		Utils.AnexarEvidenciaFalha(result, driver, logger, reports, resultadoEsperado);
	}

	@AfterClass
	public void fim() throws Exception {
		Thread.sleep(2000);
		driver.quit();
		video.pararGravacao();
	}

}

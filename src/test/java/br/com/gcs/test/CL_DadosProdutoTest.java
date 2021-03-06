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
import static br.com.gcs.util.Utils.*;
import br.com.gcs.util.VideoRecord;

public class CL_DadosProdutoTest {

	private WebDriver driver;
	private CL_DadosProduto dp;
	private ExtentReports reports;
	private ExtentTest logger;
	private String resultadoEsperado;
	private VideoRecord video = new VideoRecord();

	@BeforeClass
	public void preCondicao() throws Exception {
		driver = selecionarNavegador(propriedades("browser"));
		reports = new ExtentReports(propriedades("reportPath"), false);
		reports.config().documentTitle("CAP LEAO").reportName("GCS").reportHeadline("Cap Leao");
	//	video.inciarGravacao("Dados do Produto");
		dp = new CL_DadosProduto(driver);
	}

	@Test(enabled = true, priority = 1)
	public void camposObrigatorios() throws Exception {
		logger = reports.startTest("CT: " + dados(1, 2, 1));
		logger.assignCategory("Dados do Produto");
		dp.avancar();
		resultadoEsperado = dados(1, 2, 4);
		assertEquals(resultadoEsperado, dp.recuperarAlerta());
		dp.selecionarValorTitulo("R$ " + dados(1, 3, 2) + ",00");
		Thread.sleep(2000);
		dp.avancar();
		resultadoEsperado = dados(1, 3, 4);
		assertEquals(resultadoEsperado, dp.recuperarAlerta());
		Thread.sleep(2000);
		logger.log(LogStatus.PASS, null);
	}

	@Test(enabled = false, priority = 2)
	public void validarValorMesalidade() throws Exception {
		logger = reports.startTest("CT: " + dados(1, 5, 1));
		logger.assignCategory("Dados do Produto");
		dp.selecionarValorTitulo("R$ " + dados(1, 5, 2) + ",00");
		dp.selecionarQtdTitulo(Integer.parseInt(dados(1, 5, 3)));
		assertEquals("R$ " + dados(1, 5, 4) + ",00", dp.recuperarTotal());
		Thread.sleep(1000);
		dp.selecionarQtdTitulo(Integer.parseInt(dados(1, 6, 3)));
		assertEquals("R$ " + dados(1, 6, 4) + ",00", dp.recuperarTotal());
		Thread.sleep(1000);
		dp.selecionarQtdTitulo(Integer.parseInt(dados(1, 7, 3)));
		assertEquals("R$ " + dados(1, 7, 4) + ",00", dp.recuperarTotal());
		logger.log(LogStatus.PASS, null);
	}

	@Test(enabled = false, priority = 3)
	public void avancarProximaAba() throws InterruptedException {
		logger = reports.startTest("CT: " + dados(1, 9, 1));
		logger.assignCategory("Dados do Produto");
		dp.selecionarValorTitulo("R$ " + dados(1, 9, 2) + ",00");
		dp.selecionarQtdTitulo(Integer.parseInt(dados(1, 9, 3)));
		Thread.sleep(1500);
		dp.avancar();
		logger.log(LogStatus.PASS, null);
	}

	@AfterMethod
	public void posCondicao(ITestResult result) throws Exception {
		AnexarEvidenciaFalha(result, driver, logger, reports, resultadoEsperado);
	}

	@AfterClass
	public void fim() throws Exception {
		Thread.sleep(2000);
		driver.quit();
		// video.pararGravacao();
	}

}

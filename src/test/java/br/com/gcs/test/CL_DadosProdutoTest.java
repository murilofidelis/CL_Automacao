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

	@BeforeClass
	public void preCondicao() throws Exception {
		driver = Utils.selecionarNavegador(Utils.propriedades("browser"));
		reports = new ExtentReports(Utils.propriedades("reportPath"), false);
		reports.config().documentTitle("CAP LEAO").reportName("GCS").reportHeadline("Cap Leao");
		// video.inciarGravacao("Dados do Produto");
		dp = new CL_DadosProduto(driver);
	}

	@Test(enabled = false, priority = 1)
	public void validarCamposObrigatorios() throws Exception {
		logger = reports.startTest("CT: Validar campos obrigatorios");
		logger.assignCategory("Dados do Produto");
		dp.avancar();
		assertEquals("* Selecione um valor para o título", dp.recuperarAlerta());
		Thread.sleep(2000);
		dp.selecionarValorTitulo("R$ 60,00");
		dp.avancar();
		assertEquals("* Quantos títulos você quer comprar?", dp.recuperarAlerta());
		Thread.sleep(2000);
		logger.log(LogStatus.PASS, null);
	}

	@Test(enabled = false, priority = 2)
	public void validarValorMesalidade() throws Exception {
		logger = reports.startTest("CT: Valida Valor da Mensalidade");
		logger.assignCategory("Dados do Produto");
		dp.selecionarValorTitulo("R$ 30,00");
		dp.selecionarQtdTitulo(2);
		assertEquals("R$ 30,00", dp.recuperarTotal());
		Thread.sleep(1000);
		dp.selecionarQtdTitulo(2);
		assertEquals("R$ 60,00", dp.recuperarTotal());
		Thread.sleep(1000);
		dp.selecionarQtdTitulo(3);
		assertEquals("R$ 90,00", dp.recuperarTotal());
		logger.log(LogStatus.PASS, null);
	}

	@Test(enabled = false, priority = 3)
	public void avancarSucesso() throws InterruptedException {
		logger = reports.startTest("CT: Avançar para próxima aba");
		logger.assignCategory("Dados do Produto");
		dp.selecionarValorTitulo("R$ 30,00");
		dp.selecionarQtdTitulo(1);
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
		// video.pararGravacao();
	}

}

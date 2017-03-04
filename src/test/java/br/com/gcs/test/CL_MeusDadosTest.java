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
import br.com.gcs.steps.CL_MeusDados;
import br.com.gcs.util.Utils;
import br.com.gcs.util.VideoRecord;

public class CL_MeusDadosTest {

	private WebDriver driver;
	private CL_DadosProduto dp;
	private CL_MeusDados md;
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
		dp = new CL_DadosProduto(driver);
		md = new CL_MeusDados(driver);
		dp.avancarSucesso();
		//video.inciarGravacao("Meus Dados");
	}

	@Test(enabled = true, priority = 1)
	public void cpfInvalido() throws InterruptedException {
		logger = reports.startTest("CT: " + u.dados(2, 3, 1));
		logger.assignCategory("Meus Dados");
		md.informarDadosPessoais(u.dados(2, 3, 3), u.dados(2, 3, 4), u.dados(2, 3, 5), u.dados(2, 3, 6),
				u.dados(2, 3, 7), u.dados(2, 3, 8), u.dados(2, 3, 9), u.dados(2, 3, 10));
		md.informarEndereco(u.dados(2, 3, 11));
		md.informarContato(u.dados(2, 3, 17), u.dados(2, 3, 18), u.dados(2, 3, 23), u.dados(2, 3, 24),
				u.dados(2, 3, 25));
		md.avancar();
		resultadoEsperado = u.dados(2, 3, 26);
		assertEquals(resultadoEsperado, md.recuperarAlerta());
		logger.log(LogStatus.PASS, "Resultado Obtido: " + md.recuperarAlerta());
	}

	@Test(enabled = true, priority = 2)
	public void nomeIncompleto() throws InterruptedException {
		logger = reports.startTest("CT: " + u.dados(2, 5, 1));
		logger.assignCategory("Meus Dados");
		md.informarDadosPessoais(u.dados(2, 5, 3), Utils.cpf(), null, null, null, null, null, null);
		md.avancar();
		resultadoEsperado = u.dados(2, 5, 26);
		assertEquals(resultadoEsperado, md.recuperarAlerta());
		logger.log(LogStatus.PASS, "Resultado Obtido: " + md.recuperarAlerta());
	}

	@Test(enabled = true, priority = 3)
	public void dataInvalida() throws InterruptedException {
		logger = reports.startTest("CT: " + u.dados(2, 7, 1));
		logger.assignCategory("Meus Dados");
		md.informarDadosPessoais(u.dados(2, 7, 3), null, u.dados(2, 7, 5), null, null, null, null, null);
		md.avancar();
		resultadoEsperado = u.dados(2, 7, 26);
		assertEquals(resultadoEsperado, md.recuperarAlerta());
		logger.log(LogStatus.PASS, "Resultado Obtido: " + md.recuperarAlerta());
	}

	@Test(enabled = true, priority = 4)
	public void validarRenda() throws Exception {
		logger = reports.startTest("CT: " + u.dados(2, 9, 1));
		logger.assignCategory("Meus Dados");
		md.informarDadosPessoais(null, null, u.dados(2, 9, 5), null, u.dados(2, 9, 7), u.dados(2, 9, 8), null, null);
		md.avancar();
		resultadoEsperado = u.dados(2, 9, 26);
		assertEquals(resultadoEsperado, md.recuperarAlerta());
		logger.log(LogStatus.PASS, "Resultado Obtido: " + md.recuperarAlerta());
	}

	@Test(enabled = false, priority = 5)
	public void avancar() throws InterruptedException {
		logger = reports.startTest("CT: Avançar com sucesso");
		logger.assignCategory("Meus Dados");
		md.avancarSucesso();
		logger.log(LogStatus.PASS, null);
	}

	@AfterMethod
	public void posCondicao(ITestResult result) {
		Utils.AnexarEvidenciaFalha(result, driver, logger, reports, resultadoEsperado);
	}

	@AfterClass
	public void fim() throws Exception {
		Thread.sleep(2000);
		driver.quit();
		//video.pararGravacao();
	}

}

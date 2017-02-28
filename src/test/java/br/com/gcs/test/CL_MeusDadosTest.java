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

	@BeforeClass
	public void preCondicao() throws Exception {
		driver = Utils.selecionarNavegador(Utils.propriedades("browser"));
		reports = new ExtentReports(Utils.propriedades("reportPath"), false);
		reports.config().documentTitle("CAP LEAO").reportName("GCS").reportHeadline("Cap Leao");
		dp = new CL_DadosProduto(driver);
		md = new CL_MeusDados(driver);
		dp.avancarSucesso();
	//	video.inciarGravacao("Meus Dados");
	}

	@Test(enabled = true, priority = 1)
	public void cpfInvalido() throws InterruptedException {
		logger = reports.startTest("CT: CPF inválido");
		logger.assignCategory("Meus Dados");
		md.informarDadosPessoais("Nome Teste", "1234567899", "29091991", "ADVOGADO", "Até R$ 500,00",
				"de R$ 500,01 a R$ 1.500,00", "Solteiro", "Masculino");
		md.informarEndereco("70040907");
		md.informarContato("61", "99553311", "teste@teste.com", "Não", "Não");
		md.avancar();
		resultadoEsperado = "CPF Inválido";
		assertEquals(resultadoEsperado, md.recuperarAlerta());
		logger.log(LogStatus.PASS, "Resultado Obtido: " + md.recuperarAlerta());
	}

	@Test(enabled = true, priority = 2)
	public void nomeIncompleto() throws InterruptedException {
		logger = reports.startTest("CT: Nome Incompleto");
		logger.assignCategory("Meus Dados");
		md.informarDadosPessoais("Nome", Utils.cpf(), null, null, null, null, null, null);
		md.avancar();
		resultadoEsperado = "Por favor, digite o nome completo";
		assertEquals(resultadoEsperado, md.recuperarAlerta());
		logger.log(LogStatus.PASS, "Resultado Obtido: " + md.recuperarAlerta());
	}

	@Test(enabled = true, priority = 3)
	public void dataInvalida() throws InterruptedException {
		logger = reports.startTest("CT: Data inválida");
		logger.assignCategory("Meus Dados");
		md.informarDadosPessoais("Nome Teste", null, "00000000", null, null, null, null, null);
		md.avancar();
		resultadoEsperado = "Data IncorretaX";
		assertEquals(resultadoEsperado, md.recuperarAlerta());
		logger.log(LogStatus.PASS, "Resultado Obtido: " + md.recuperarAlerta());
	}

	@Test(enabled = true, priority = 4)
	public void validarRenda() throws Exception {
		logger = reports.startTest("CT: Renda individual menor que renda familiar");
		logger.assignCategory("Meus Dados");
		md.informarDadosPessoais("Nome Teste", null, "29091991", null, "de R$ 1500,01 a R$ 2.500,00", "Até R$ 500,00",
				null, null);
		md.avancar();
		resultadoEsperado = "Renda individual não deve ser maior que a renda familiar";
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
	//	video.pararGravacao();
	}

}

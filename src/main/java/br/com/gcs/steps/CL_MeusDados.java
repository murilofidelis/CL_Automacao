package br.com.gcs.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.gcs.po.CapLeao_MeusDadosPO;
import br.com.gcs.util.Utils;

public class CL_MeusDados {

	private WebDriverWait wait;
	private CapLeao_MeusDadosPO page;

	public CL_MeusDados(WebDriver driver) {
		wait = new WebDriverWait(driver, Integer.parseInt(Utils.propriedades("timeout")));
		page = PageFactory.initElements(driver, CapLeao_MeusDadosPO.class);
	}

	public void informarDadosPessoais(String nome, String cpf, String dtNascimento, String profissao,
			String rendaIndividual, String rendaFamliar, String estadoCivil, String sexo) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("divSegundoStep")));
		if (nome != null) {
			page.campo_nome.clear();
			page.campo_nome.sendKeys(nome);
		}
		if (cpf != null) {
			Thread.sleep(2000);
			page.campo_cpf.clear();
			page.campo_cpf.sendKeys(cpf);
		}
		if (dtNascimento != null) {
			Thread.sleep(1000);
			page.campo_dtNascimento.clear();
			page.campo_dtNascimento.sendKeys(dtNascimento);
		}
		if (profissao != null) {
			Thread.sleep(1000);
			new Select(page.combo_profissao).selectByVisibleText(profissao);
		}
		if (rendaIndividual != null) {
			Thread.sleep(1000);
			new Select(page.combo_rendaIndividual).selectByVisibleText(rendaIndividual);
		}
		if (rendaFamliar != null) {
			Thread.sleep(1000);
			new Select(page.combo_rendaFamiliar).selectByVisibleText(rendaFamliar);
		}
		if (estadoCivil != null) {
			Thread.sleep(1000);
			new Select(page.combo_estadoCivil).selectByVisibleText(estadoCivil);
		}
		if (sexo != null) {
			Thread.sleep(1000);
			new Select(page.combo_sexo).selectByVisibleText(sexo);
		}
	}

	public void informarEndereco(String cep) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("divSegundoStep")));
		if (cep != null) {
			page.campo_CEP.sendKeys(cep);
			page.campo_CEP.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		}
	}

	public void informarContato(String DDDCelular, String celular, String email, String InfoCelular, String infoEmail) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("divSegundoStep")));
		if (DDDCelular != null) {
			page.campo_DDD_celular.sendKeys(DDDCelular);
		}
		if (celular != null) {
			page.campo_celular.sendKeys(celular);
		}
		if (email != null) {
			page.campo_email.clear();
			page.campo_email.sendKeys(email);
		}
		if (InfoCelular != null) {
			if (InfoCelular.equals("Não")) {
				page.informe_celular_NAO.click();
			}
		}
		if (infoEmail != null) {
			if (infoEmail.equals("Não")) {
				page.informe_email_NAO.click();
			}
		}
	}

	public void avancar() throws InterruptedException {
		Thread.sleep(2000);
		page.btn_avancar.click();
	}

	public String recuperarAlerta() {
		return page.msn_alerta.getText();
	}

	public void avancarSucesso() throws InterruptedException {
		informarDadosPessoais("Nome Teste", Utils.cpf(), "29091991", "ADVOGADO", "Até R$ 500,00",
				"de R$ 500,01 a R$ 1.500,00", "Solteiro", "Masculino");
		informarEndereco("70040907");
		informarContato("61", "99553311", "teste@teste.com", "Não", "Não");
		avancar();
	}

}

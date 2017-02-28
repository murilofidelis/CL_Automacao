package br.com.gcs.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.gcs.po.CapLeao_DadosProdutoPO;
import br.com.gcs.util.Utils;

public class CL_DadosProduto {

	private WebDriverWait wait;
	private CapLeao_DadosProdutoPO page;

	public CL_DadosProduto(WebDriver driver) {
		wait = new WebDriverWait(driver, Integer.parseInt(Utils.propriedades("timeout")));
		driver.get(Utils.propriedades("url"));
		page = PageFactory.initElements(driver, CapLeao_DadosProdutoPO.class);
	}

	public void selecionarValorTitulo(String valor) {
		new Select(page.combo_valor_titulo).selectByVisibleText(valor);
	}

	public void selecionarQtdTitulo(int qtdTitulos) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("divVenda2")));
		switch (qtdTitulos) {
		case 1:
			page.titulo1.click();
			break;
		case 2:
			page.titulo2.click();
			break;
		case 3:
			page.titulo3.click();
			break;
		default:
			break;
		}

	}

	public void avancar() {
		page.bt_avancar.click();
	}

	public String recuperarTotal() {
		return page.valorTotalMensalidade.getText();
	}

	public String recuperarAlerta() {
		return page.mensagemAlerta.getText();
	}

	public void avancarSucesso() throws InterruptedException {
		selecionarValorTitulo("R$ 30,00");
		selecionarQtdTitulo(1);
		Thread.sleep(1500);
		avancar();
	}

}

package br.com.gcs.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CapLeao_DadosProdutoPO {

	@FindBy(id = "selectValorTitulo")
	public WebElement combo_valor_titulo;

	@FindBy(id = "radio1")
	public WebElement titulo1;

	@FindBy(id = "radio2")
	public WebElement titulo2;

	@FindBy(id = "radio3")
	public WebElement titulo3;

	@FindBy(id = "strongValorTotal")
	public WebElement valorTotalMensalidade;

	@FindBy(id = "btnAvancarPrimeiroPassoNovo")
	public WebElement bt_avancar;

	@FindBy(className = "pQuestao")
	public WebElement mensagemAlerta;

}

package br.com.gcs.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CapLeao_MeusDadosPO {

	/* DADOS PESSOAIS */

	@FindBy(id = "inputNomePF")
	public WebElement campo_nome;

	@FindBy(id = "inputCPFPF")
	public WebElement campo_cpf;

	@FindBy(id = "inputDataNascimentoPF")
	public WebElement campo_dtNascimento;

	@FindBy(id = "selectProfissaoPF")
	public WebElement combo_profissao;

	@FindBy(id = "selectRendaIndividualPF")
	public WebElement combo_rendaIndividual;

	@FindBy(id = "selectRendaFamiliar")
	public WebElement combo_rendaFamiliar;

	@FindBy(id = "selectEstadoCivil")
	public WebElement combo_estadoCivil;

	@FindBy(id = "selectSexoPF")
	public WebElement combo_sexo;

	/* ENDEREÇO */

	@FindBy(id = "inputCep")
	public WebElement campo_CEP;

	@FindBy(id = "btnConsultarCEP")
	public WebElement consultar_CEP;

	@FindBy(id = "inputEndereco")
	public WebElement campo_endereco;

	@FindBy(id = "inputComplemento")
	public WebElement campo_complemento;

	@FindBy(id = "ctl00_PlaceHolderMain_CaixaCapTime1_inputBairro")
	public WebElement campo_bairro;

	@FindBy(id = "ctl00_PlaceHolderMain_CaixaCapTime1_inputCidade")
	public WebElement campo_cidade;

	/* CONTATO */

	@FindBy(id = "inputDDDCelularPF")
	public WebElement campo_DDD_celular;

	@FindBy(id = "inputCelularPF")
	public WebElement campo_celular;

	@FindBy(id = "inputEmailPF")
	public WebElement campo_email;

	@FindBy(id = "radioSpamCelularNao")
	public WebElement informe_celular_NAO;

	@FindBy(id = "radioSpamEmailNao")
	public WebElement informe_email_NAO;

	@FindBy(id = "btnAvancarSegundoPassoNovo")
	public WebElement btn_avancar;

	/* MENSAGENS */

	@FindBy(className = "pTipoErro")
	public WebElement msn_alerta;

}

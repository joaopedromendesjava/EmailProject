package br.com.email.project.dto;

import br.com.email.project.model.Email;

public class emailLayout {
	
	private static final String QUEBRA_DE_LINHA_DUPLA = "<br><br>";
	private static final String QUEBRA_DE_LINHA_UNICA = "<br>";
	
	public Email montarEmailAdministrador(String destinatario, String assunto) {
		
		StringBuilder texto = new StringBuilder();
		
		texto
			.append("A/C Administrador")
			.append(QUEBRA_DE_LINHA_DUPLA);
		
		texto
			.append("solicito alteração de senha do sistema")
			.append(QUEBRA_DE_LINHA_DUPLA);
		
		gerarAssinaturaAdmin(texto);
		
		gerarRodapé(texto);
		
		return new Email(destinatario, assunto, texto.toString());
		}
	
	private String gerarAssinaturaAdmin(StringBuilder texto) {
		return texto
					.append("Atenciosamente")
					.append(QUEBRA_DE_LINHA_UNICA)
					.append("Operador de caixa")
					.append(QUEBRA_DE_LINHA_DUPLA)
					.toString();
					
	}
	
	private String gerarAssinaturaSec(StringBuilder texto) {
		return texto
					.append("Atenciosamente")
					.append(QUEBRA_DE_LINHA_UNICA)
					.append("Secretario da Empresa")
					.append(QUEBRA_DE_LINHA_DUPLA)
					.toString();
					
	}

	private String gerarRodapé(StringBuilder texto ) {
		return texto.append("Email automatico: Favor nao responder!").toString();
	}

	public Email montarEmailSecretario(String destinatario, String assunto) {
		StringBuilder texto = new StringBuilder();
	
		texto
			.append("A/C Secretario")
			.append(QUEBRA_DE_LINHA_DUPLA);
	
		texto
			.append("Texto personalizado para secretario")
			.append(QUEBRA_DE_LINHA_DUPLA);
	
		gerarAssinaturaSec(texto);
		gerarRodapé(texto);
	
		return new Email(destinatario, assunto, texto.toString());
	
	}
	

}

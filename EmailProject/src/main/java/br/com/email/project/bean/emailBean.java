package br.com.email.project.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.email.project.dto.emailLayout;
import br.com.email.project.model.Email;
import br.com.email.project.service.emailService;

@Named
@RequestScoped
public class emailBean implements Serializable   {

	private static final long serialVersionUID = -8720525255685431725L;
	
	private static final String DESTINATARIO = "joaopedromendessilva@gmail.com";
	
	private static final String ASSUNTO = "mudança de senha!";
	
	@Inject
	private emailService emailservice;
	
	public String enviarEmail() {
		emailservice.enviar(montarEmail());
		return null;
	
	}
	
	private Email montarEmail() {
		emailLayout layout = new emailLayout();
		return layout.montarEmailAdministrador(DESTINATARIO, ASSUNTO);
		
	}

}

package Rede.exemplos.email;

import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import java.util.Properties;

public class EnviaEmail {
	public static void main(String[] args) {
		try {
			// Define as configurações de servidor
			// e utuário de origem
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "mail.empresa.com.br");

			Session mailSession = Session.getDefaultInstance(props, null);
			Transport transport = mailSession.getTransport();

			// Cria a mensagem do e-mail
			MimeMessage message = new MimeMessage(mailSession);
			message.setContent(JOptionPane.showInputDialog(null, "Informe o texto do e-mail!"), "text/plain");

			// Define o(s) remetente(s)
			InternetAddress[] remetentes = {
					new InternetAddress(JOptionPane.showInputDialog(null, "Informe o Remetente!")) };

			message.addFrom(remetentes);

			// Define o(s) destinatários

			java.util.List<InternetAddress> destinatarios = new java.util.ArrayList<InternetAddress>();

			String dst = JOptionPane.showInputDialog(null, "Informe um destinatário!");

			while (dst != null && !dst.equals("")) {
				destinatarios.add(new InternetAddress(dst));
				dst = JOptionPane.showInputDialog(null, "Informe um destinatário!");
			}

			if (destinatarios.size() != 0) {
				for (InternetAddress dest : destinatarios)
					message.addRecipient(Message.RecipientType.TO, dest);

				// Envia a mensagem (e-mail) ao servidor
				transport.connect();
				transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
				transport.close();

				JOptionPane.showMessageDialog(null, "E-mail enviado!");
			} else
				JOptionPane.showMessageDialog(null, "Não existem destinatarios para este E-mail!");

		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(null, "Operação Cancelada!");
		} catch (NoSuchProviderException ex) {
			JOptionPane.showMessageDialog(null, "CfgServidor Erro\n" + ex.getMessage());
		} catch (MessagingException ex) {
			JOptionPane.showMessageDialog(null, "Mensagem Erro\n" + ex.getMessage());
		}
	}
}

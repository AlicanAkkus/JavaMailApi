package com.alican.akkus.eposta.yolla;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EPostaYolla {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// hangi e-posta adresinden yolluyacaksak onu yaz�yoruz..
		final String from = "alican.akkus94@gmail.com";
		// hesab�n�z�n passwords�
		// test etmek i�in kendi passwordn�z� giriniz :)
		// e�er oldugu gibi test ederseniz me�hur NullPointerException yersiniz
		// :)
		final String password = "***************";

		// gonderilecek olan adres
		// ben kendime yolluyorum :)
		final String to = "alican.akkus94@gmail.com";

		// Properties �zellikleri ayarlamam�z laz�m
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}

		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("alican.akkus94@gmail.com"));

			message.setHeader("Content-Type", "text/plain; charset=UTF-8");// e�er
																			// bu
																			// sat�r�
																			// eklemezsenin
																			// beklenemedik
																			// karekter
																			// hatalar�yla
																			// ka��la�abilirsiniz
																			// :)
			message.setSubject("Java mail Api test");
			message.setText("Bu e-posta , Java kodu ile g�nderildi..\nSpam de�ildir :) ");
			Transport.send(message);

			System.out.println("E-Posta ba�ar�yla g�nderildi..");
		} catch (MessagingException e) {
			throw new RuntimeException();
		}
	}
}

package com.vicenzo.flightreservation.util;

import com.vicenzo.flightreservation.controllers.ReservationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    @Value("${flightreservation.email.body}")
    private String EMAIL_BODY;
    @Value("${flightreservation.email.subject}")
    private String EMAIL_SUBJECT;


    @Autowired
    private JavaMailSender sender;

    public void sendItinerary(String toAddress, String filePath) {

        LOGGER.info("Inside sendItine" +
                "rary()");


        MimeMessage message = sender.createMimeMessage();
        // true indicates the message has multiple parts, and we have attachment

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(EMAIL_SUBJECT);
            messageHelper.setText(EMAIL_BODY);
            messageHelper.addAttachment("Itinerary", new File(filePath));

            sender.send(message);

        } catch (MessagingException e) {
            LOGGER.error("Exception inside sendItinerary" + e);
        }
    }


}

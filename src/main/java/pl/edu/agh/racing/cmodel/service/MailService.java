package pl.edu.agh.racing.cmodel.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.edu.agh.racing.cmodel.dto.NotificationEmailDto;
import pl.edu.agh.racing.cmodel.exception.CModelException;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(NotificationEmailDto notificationEmailDto) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("cmodelapp@email.com");
            messageHelper.setTo(notificationEmailDto.getRecipient());
            messageHelper.setSubject(notificationEmailDto.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmailDto.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!");
        } catch (MailException e) {
            throw new CModelException("Exception occurred while sending mail to " +
                    notificationEmailDto.getRecipient(), e.getCause());
        }
    }
}

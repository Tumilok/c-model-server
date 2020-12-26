package pl.edu.agh.racing.cmodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEmailDto {
    private String subject;
    private String recipient;
    private String body;
}

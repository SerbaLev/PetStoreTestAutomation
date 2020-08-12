package dto.response.pet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@EqualsAndHashCode
@ToString
public class ErrorOrNotificationDTO {

    private String code;
    private String type;
    private String message;

}

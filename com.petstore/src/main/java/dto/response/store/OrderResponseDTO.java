package dto.response.store;

import dto.data.Status;
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
public class OrderResponseDTO {

    private Integer id;
    private Integer petId;
    private Integer quantity;
    private String shipDate;
    private Status status;
    private Boolean complete;

}

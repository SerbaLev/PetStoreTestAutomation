package dto.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold"),
    INCORRECT("qwerty12345");

    private final String value;

}

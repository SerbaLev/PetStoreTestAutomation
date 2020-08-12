package transfer.endpoints;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import transfer.endpoints.pet.Pet;
import transfer.endpoints.store.Store;
import transfer.endpoints.user.User;

@Slf4j
@Getter
@EqualsAndHashCode
@ToString
public class PetStoreRestApi {

    Pet pet = new Pet("");
    Store store = new Store("");
    User user = new User("");

}

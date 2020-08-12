package dto.request.pet;

import dto.data.CategoryDTO;
import dto.data.TagDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@EqualsAndHashCode
@ToString
public class AddOrUpdatePetRequestDTO {

    private String id;
    private CategoryDTO category;
    private String name;
    private List<String> photoUrls;
    private List<TagDTO> tags;
    private String status;

}

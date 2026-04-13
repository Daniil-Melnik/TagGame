package testing.GameBackend.Implementations.Builder;

import testing.GameBackend.DTO.InDTO;
import testing.Interfaces.Builder.InDTOBuilder;

// строитель экземпляров InDTO
public class SimpleInDTOBuilder implements InDTOBuilder {
    private int position = -1; // дублёры полей InDTO
    private String value = "*";

    private String path = "";

    @Override
    public InDTOBuilder setPosition(int position) {
        this.position = position;
        return this;
    }

    @Override
    public InDTOBuilder setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public InDTOBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public InDTO build(){
        return new InDTO(position, value, path);
    }

    @Override
    public InDTOBuilder reset(){
        this.position = -1;
        this.value = "*";
        this.path = "";

        return this;
    }
}

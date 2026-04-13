package testing.Interfaces.Builder;

import testing.GameBackend.DTO.InDTO;
// интерфес строителя объектов InDTO
public interface InDTOBuilder {
    InDTOBuilder setPosition(int position);
    InDTOBuilder setValue(String value);
    InDTOBuilder setPath(String path);
    InDTOBuilder reset();
    InDTO build();
}

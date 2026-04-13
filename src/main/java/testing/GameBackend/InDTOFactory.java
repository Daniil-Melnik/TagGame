package testing.GameBackend;

import testing.GameBackend.DTO.InDTO;
import testing.GameBackend.Implementations.Builder.SimpleInDTOBuilder;
import testing.Interfaces.Builder.InDTOBuilder;

// фабрика создания InDTO-экземпляров на основе строителя
public class InDTOFactory {
    private static InDTOBuilder builder = new SimpleInDTOBuilder();

    // создание экземпляра для выполнения хода
    public static InDTO createInitMoveInDTO(int position, String value){
        return builder
                .reset()
                .setPosition(position)
                .setValue(value)
                .build();
    }

    // создание экземпляра для выполнения операции сохранения/загрузки
    public static InDTO createFileInDTO(String path){
        return builder
                .reset()
                .setPath(path)
                .build();
    }
}

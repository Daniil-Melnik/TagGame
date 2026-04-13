package testing.GameBackend.Implementations.Strategy;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import testing.GameBackend.DTO.SaveDTO;
import testing.Interfaces.FileStrategy;

import java.io.File;
import java.io.IOException;

public class XmlStrategy implements FileStrategy { // стратегия работы с xml-файлами
    private JAXBContext context = null;
    public XmlStrategy(){
        try {
            context = JAXBContext.newInstance(SaveDTO.class); // создание контекста для маршаллера/демаршаллера
        } catch (JAXBException e) {System.err.println(e.getMessage());}
    }

    @Override
    public SaveDTO load(String path) throws IOException { // загрузка
        SaveDTO saveDTO = new SaveDTO();
        try{
            Unmarshaller unmarshaller = context.createUnmarshaller(); // создание демаршаллера (переводчика из Xml-документа
            // в экземпляра класс )
            saveDTO = (SaveDTO) unmarshaller.unmarshal(new File(path)); // демаршаллизация
        } catch (JAXBException e) { System.err.println(e.getMessage());}

        return saveDTO;
    }

    @Override
    public void save(SaveDTO saveDTO, String path) throws IOException { // сохранение
        try {
            Marshaller marshaller = context.createMarshaller(); // создание маршаллера
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(saveDTO, new File(path)); // маршаллизация (запись экземпляра в файл xml)
        } catch (JAXBException e){ System.out.println(e.getMessage());};

    }
}

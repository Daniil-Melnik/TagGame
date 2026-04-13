package testing.GameBackend.Implementations.Strategy;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import testing.GameBackend.DTO.SaveDTO;
import testing.Interfaces.FileStrategy;

import java.io.File;
import java.io.IOException;

/**
 * Стратегия работы с XML-файлами для сохранения и загрузки состояния игры.
 * <p>
 * Использует JAXB (Java Architecture for XML Binding) для сериализации/десериализации
 * объектов {@link SaveDTO} в формат XML и обратно.
 * </p>
 *
 * @author Daniil-Melnik
 * @version 1.0
 * @see FileStrategy
 * @see SaveDTO
 */
public class XmlStrategy implements FileStrategy {
    /** Контекст JAXB для работы с классом SaveDTO */
    private JAXBContext context = null;

    /**
     * Конструктор, инициализирующий JAXB контекст.
     * <p>
     * Создает контекст для маршаллинга и анмаршаллинга объектов SaveDTO.
     * </p>
     */
    public XmlStrategy() {
        try {
            context = JAXBContext.newInstance(SaveDTO.class);
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Загружает состояние игры из XML-файла.
     *
     * @param path путь к XML-файлу
     * @return объект SaveDTO с загруженным состоянием
     * @throws IOException если возникла ошибка при чтении файла или анмаршаллинге
     */
    @Override
    public SaveDTO load(String path) throws IOException {
        SaveDTO saveDTO = new SaveDTO();
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            saveDTO = (SaveDTO) unmarshaller.unmarshal(new File(path));
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
        }
        return saveDTO;
    }

    /**
     * Сохраняет состояние игры в XML-файл.
     * <p>
     * XML-документ форматируется с отступами для удобочитаемости.
     * </p>
     *
     * @param saveDTO объект с состоянием игры для сохранения
     * @param path    путь для сохранения XML-файла
     * @throws IOException если возникла ошибка при записи файла или маршаллинге
     */
    @Override
    public void save(SaveDTO saveDTO, String path) throws IOException {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(saveDTO, new File(path));
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
    }
}
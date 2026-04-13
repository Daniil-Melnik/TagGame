package testing.GameBackend.DTO;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

// класс, описывающий DTO описывающие состояние поля при фаловой загрузке/сохранении
// Jackson - json - маппинг по геттерам
// JAXB - xml - маппинг по аннотациям здесь

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SaveDTO {
    private boolean win = false;
    private ArrayList<String> field = null;
    private int zeroPosition = 0;

    public ArrayList<String> getField(){return new ArrayList<>(field);} // возврат копии в защиту от Jackson

    public boolean getWin(){return win;}

    public int getZeroPosition(){return zeroPosition; }
}

package lab.lab.domain;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Friendship extends Entity{
    int idUser1;
    int idUser2;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dataPrietenie;
    StatusFriendship status;

    public StatusFriendship getStatus() {
        return status;
    }

    public void setStatus(StatusFriendship status) {
        this.status = status;
    }

    public Friendship(int idUser1, int idUser2, LocalDate dataPrietenie, StatusFriendship status) {
        this.idUser1 = idUser1;
        this.idUser2= idUser2;
        if (dataPrietenie == null)
            this.dataPrietenie = LocalDate.now();
        else this.dataPrietenie = dataPrietenie;
        this.status = status;
    }

    public void setData(LocalDate durata) {
        this.dataPrietenie = durata;
    }

    public int getIdUser1() {
        return idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public LocalDate getData() {
        return dataPrietenie;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + getId() +
                ", idUser1=" + idUser1 +
                ", idUser2=" + idUser2 +
                ", dataPrietenie=" + dataPrietenie +
                ", status=" + status +
                '}';
    }
}

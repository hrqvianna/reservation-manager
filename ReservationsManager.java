
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReservationsManager {
    private List<Reserve> reserves;
    private SimpleDateFormat dateFormat;

    public ReservationsManager() {
        this.reserves = new ArrayList<>();
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dateFormat.setLenient(false);
    }
    
    /**
     * Verifica se a data fornecida   valida.
     * @param date A data a ser verificada.
     * @return true se a data for valida, false caso contrario.
     */
    public boolean isValidDate(String date) {
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Adiciona uma reserva para o horário e data fornecidos.
     * 
     * @param reserve A reserva a ser adicionada.
     * @return Uma mensagem de sucesso ou erro, dependendo do resultado da operação.
     */
    public String addReserve(Reserve reserve) {
        if (reserve.getShift().equalsIgnoreCase("tarde") || reserve.getShift().equalsIgnoreCase("noite")) {
            if (reserves.contains(reserve)) {
                return "Já existe uma reserva para este horário e data.";
            } else {
                reserves.add(reserve);
                return "Reserva adicionada com sucesso!";
            }
        }
        return null;
    }

    /**
     * Cancela uma reserva para o horário e data fornecidos.
     * 
     * @param reserva A reserva a ser cancelada.
     * @return Uma mensagem de sucesso ou erro, dependendo do resultado da operação.
     */
    public String cancelReserve(Reserve reserva) {
        if (reserves.remove(reserva)) {
            return "Reserva cancelada com sucesso!";
        } else {
            return "Não foi encontrada uma reserva para este horário e data.";
        }
    }

    public List<Reserve> listReserves() {
        return reserves;
    }
}

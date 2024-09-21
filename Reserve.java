public class Reserve {
    private String name;
    private String date;
    private String shift;

    public Reserve(String name, String date, String shift) {
        this.name = name;
        this.date = date;
        this.shift = shift;
    }

    /**
     * Retorna o nome da pessoa que fez a reserva.
     * @return O nome da pessoa que fez a reserva.
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna a data da reserva.
     * @return A data da reserva.
     */
    public String getDate() {
        return date;
    }

    /**
     * Retorna o turno da reserva (tarde ou noite).
     * @return O turno da reserva.
     */
    public String getShift() {
        return shift;
    }

    /**
     * Retorna uma representacao textual da reserva.
     *
     * @return Uma string com o formato "Reserva:\nNome: <nome>, Data: <data>, Turno: <turno>\n"
     */
    @Override
    public String toString() {
        return "Reserva:\nNome: " + name + ", Data: " + date + ", Turno: " + shift + "\n";
    }

    /**
     * Verifica se a reserva atual   igual a outra reserva.
     * 
     * Dois objetos da classe Reserve s o considerados iguais se tiverem a mesma data e turno.
     * @param obj O objeto a ser comparado.
     * @return true se os objetos s o iguais, false caso contr rio.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reserve reserve = (Reserve) obj;
        return date.equals(reserve.date) && shift.equals(reserve.shift);
    }

    /**
     * Retorna um valor hash para a reserva.
     * 
     * O valor hash   gerado a partir da data e do turno da reserva.
     * 
     * @return Um valor hash para a reserva.
     */
    @Override
    public int hashCode() {
        return 31 * date.hashCode() + shift.hashCode();
    }
}

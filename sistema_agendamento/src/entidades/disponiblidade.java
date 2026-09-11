package entidades;

import java.sql.Time;

public class disponiblidade {
	
	private int codDisponibilidade;
    private String diaSemana;
    private Time horaInicio;
    private Time horaFim;
    private int codPrestador;

    public void Disponibilidade() {
    }

    public int getCodDisponibilidade() {
        return codDisponibilidade;
    }

    public void setCodDisponibilidade(int codDisponibilidade) {
        this.codDisponibilidade = codDisponibilidade;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }

    public int getCodPrestador() {
        return codPrestador;
    }

    public void setCodPrestador(int codPrestador) {
        this.codPrestador = codPrestador;
    }

}

package entidades;

import java.sql.Date;
import java.sql.Time;

public class agendamento {

	
	private int codAgendamento;
    private Date dataAgendamento;
    private Time horaAgendamento;
    private String statusAgendamento;
    private String descricaoAgendamento;
    private int codCliente;
    private int codServico;

    public void Agendamento() {
    }

    public int getCodAgendamento() {
        return codAgendamento;
    }

    public void setCodAgendamento(int codAgendamento) {
        this.codAgendamento = codAgendamento;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Time getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(Time horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public String getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(String statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }

    public String getDescricaoAgendamento() {
        return descricaoAgendamento;
    }

    public void setDescricaoAgendamento(String descricaoAgendamento) {
        this.descricaoAgendamento = descricaoAgendamento;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }
	
}

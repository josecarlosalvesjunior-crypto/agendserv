package entidades;

public class servico {
	
	private int codServico;
    private String nomeServico;
    private String descricaoServico;
    private int duracaoServico;
    private double valorServico;
    private int codPrestador;

    public void Servico() {
    }

    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public int getDuracaoServico() {
        return duracaoServico;
    }

    public void setDuracaoServico(int duracaoServico) {
        this.duracaoServico = duracaoServico;
    }

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }

    public int getCodPrestador() {
        return codPrestador;
    }

    public void setCodPrestador(int codPrestador) {
        this.codPrestador = codPrestador;
    }
	
}

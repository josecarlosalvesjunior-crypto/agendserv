package entidades;

public class prestador {
	
	private int codPrestador;
    private String nomePrestador;
    private String especialidade;
    private String emailPrestador;
    private String telefonePrestador;
    private String statusFornecedor;

    public void Prestador() {
    }

    public int getCodPrestador() {
        return codPrestador;
    }

    public void setCodPrestador(int codPrestador) {
        this.codPrestador = codPrestador;
    }

    public String getNomePrestador() {
        return nomePrestador;
    }

    public void setNomePrestador(String nomePrestador) {
        this.nomePrestador = nomePrestador;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEmailPrestador() {
        return emailPrestador;
    }

    public void setEmailPrestador(String emailPrestador) {
        this.emailPrestador = emailPrestador;
    }

    public String getTelefonePrestador() {
        return telefonePrestador;
    }

    public void setTelefonePrestador(String telefonePrestador) {
        this.telefonePrestador = telefonePrestador;
    }

    public String getStatusFornecedor() {
        return statusFornecedor;
    }

    public void setStatusFornecedor(String statusFornecedor) {
        this.statusFornecedor = statusFornecedor;
    }

}

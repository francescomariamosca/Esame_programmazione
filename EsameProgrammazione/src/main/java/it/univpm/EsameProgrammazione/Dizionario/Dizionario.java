package it.univpm.EsameProgrammazione.Dizionario;

/**
 * Classe contente gli attributi del dizionario e i getter/setter
 */
public  class Dizionario {



    protected String zipCode;
    protected String nomeCitta;

    public Dizionario(){

    }
    public Dizionario(String zipCode, String nomeCitta) {
        this.zipCode = zipCode;
        this.nomeCitta = nomeCitta;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNomeCitta() {
        return nomeCitta;
    }

    public void setNomeCitta(String nomeCitta) {
        this.nomeCitta = nomeCitta;
    }

}

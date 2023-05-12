package domain;

public class SesionId {
	public String fecha;
	public int sala;

    public SesionId() {}

    public SesionId(String fecha, int sala) {
        this.fecha = fecha;
        this.sala = sala;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }
}
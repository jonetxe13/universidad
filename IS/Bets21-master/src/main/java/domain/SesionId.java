package domain;

import java.util.Date;

public class SesionId {
	public Date fecha;
	public int sala;

    public SesionId() {}

    public SesionId(Date fecha, int sala) {
        this.fecha = fecha;
        this.sala = sala;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }
}
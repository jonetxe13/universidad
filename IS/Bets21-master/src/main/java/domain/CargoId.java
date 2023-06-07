package domain;

import java.io.Serializable;

public class CargoId implements Serializable {
    private Usuario user;
    private Sesion sesion;

    public CargoId() {
    }

    public CargoId(Usuario user, Sesion sesion) {
        this.user = user;
        this.sesion = sesion;
    }

    // Implementa equals() y hashCode() correctamente
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CargoId)) return false;
//        CargoId cargoId = (CargoId) o;
//        return Objects.equals(user, cargoId.user) && Objects.equals(sesion, cargoId.sesion);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(user, sesion);
//    }

    // Implementa getters y setters
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }
}

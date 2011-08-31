package general

class Hospital {

    String nombre
    String direccion
    String telefono

    static constraints = {
        nombre blank: false, maxSize: 128
        direccion blank: false
        telefono blank: false
    }
}

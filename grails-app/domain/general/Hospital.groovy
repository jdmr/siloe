package general

class Hospital {

    String nombre
    String direccion
    String telefono
    Set almacenes

    static hashMany = [almacenes: Almacen]
    
    static constraints = {
        nombre blank: false, maxSize: 128
        direccion blank: false
        telefono blank: false
        almacenes nullable: true
    }

    String toString() {
        return nombre
    }
}

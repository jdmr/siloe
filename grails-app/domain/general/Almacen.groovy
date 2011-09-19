package general

class Almacen {
    String nombre
    String direccion
    String telefono
    boolean principal = false
    Hospital hospital
    
    static belogsTo = [hospital: Hospital]

    static constraints = {
        nombre maxSize: 128
        direccion blank: false
        telefono blank: false
        hospital blank: false
    }
}

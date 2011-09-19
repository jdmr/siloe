package general

class Seguro {
	String nombre

    static hasMany=[contratos:Contrato]
    static constraints = {
	
    }
	
	String toString() {
		return nombre
	}
}

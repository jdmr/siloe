package general

class Contrato {
	String folio
 //   static belongsTo=[seguro:Seguro,hospital:Hospital]
    static belongsTo = [seguro:Seguro]
    static constraints = {
    }

	String toString(){
		return folio
	}	

}

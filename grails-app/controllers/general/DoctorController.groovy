package general

class DoctorController {

    def index() { }
    
    def registrarse(){ 
        def doctor  = new Usuario(params)
        
        return [doctor:doctor]
    }
    
    def guardarRegistro(){
        def doctor = new Usuario(params)
        doctor.save()
        redirect(action:"avisoRegistro",id:doctor.id)
    }
    
    def avisoRegistro(){
        def doctor = Usuario.get(params.id)
        return [doctor:doctor]
    }
}

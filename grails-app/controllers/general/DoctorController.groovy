package general

class DoctorController {

    def index() { }
    
    def registrarse(){ 
        def doctor  = new Usuario(params)
        
        return [doctor:doctor]
    }
    
    def guardarRegistro(){
        def rolDoctor = Rol.findByAuthority('ROLE_DOCTOR')
        def doctor = new Usuario(params)
        def doctorhospital = new DoctorHospital(
            doctor: doctor
            , hospital: doctor.hospital
        ).save()
        
        UsuarioRol.create(doctor, rolDoctor)
        
        //doctor.save()
        redirect(action:"avisoRegistro",id:doctor.id)
    }
    
    def avisoRegistro(){
        def doctor = Usuario.get(params.id)
        return [doctor:doctor]
    }
}

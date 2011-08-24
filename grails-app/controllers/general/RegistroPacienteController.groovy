package general

class RegistroPacienteController {

    def index() { 
        def usuario = new Usuario()
        return [usuario: usuario]
    }
    
    def crea() {
        def usuario = new Usuario(params)
        if (usuario.save(flush:true)) {
            flash.message = "El usuario $usuario.username ha sido creado"
            redirect(action:'ver',id:usuario.id)
        } else {
            log.error("No se pudo crear el usuario")
            render(view:'index', model : [usuario: usuario])
        }
    }
    
    def ver() {
        def usuario = Usuario.get(params.id)
        return [usuario: usuario]
    }
}

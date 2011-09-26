class BootStrap {

    def init = { servletContext ->
        log.info("Inicializando aplicacion")
        log.info "Validando roles"
        def rolAdmin = general.Rol.findByAuthority('ROLE_ADMIN')
        if (general.Rol.count() != 4) {
            if (!rolAdmin) {
                rolAdmin = new general.Rol(authority: 'ROLE_ADMIN').save(flush:true)
            }

            def rolDoctor = general.Rol.findByAuthority('ROLE_DOCTOR')
            if (!rolDoctor) {
                rolDoctor = new general.Rol(authority: 'ROLE_DOCTOR').save(flush:true)
            }
        
            def rolPaciente = general.Rol.findByAuthority('ROLE_PACIENTE')
            if (!rolPaciente) {
                rolPaciente = new general.Rol(authority: 'ROLE_PACIENTE').save(flush:true)
            }
            
            def rolHospital = general.Rol.findByAuthority('ROLE_HOSPITAL')
            if (!rolHospital) {
                rolHospital = new general.Rol(authority: 'ROLE_HOSPITAL').save(flush:true)
            }
        }
        
        log.info "Validando hospitales"
        def hospitales = general.Hospital.list([max:1])
        def hospital
        if(hospitales){
            hospital = hospitales.get(0)
        }
        if(!hospital){
            hospital = new general.Hospital(
                nombre : "TEST",
                direccion : "TEST",
                telefono : "TEST"
            ).save()
        }
        
        log.info "Validando usuarios"
        def admin = general.UsuarioRol.findByRol(rolAdmin)
        if (!admin) {
            admin = new general.Usuario(
                username:'admin',
                password:'admin',
                hospital: hospital
            )
            admin.save(flush:true)
            general.UsuarioRol.create(admin, rolAdmin, true)
        }

        log.info("Aplicacion inicializada")
    }
    def destroy = {
    }
}

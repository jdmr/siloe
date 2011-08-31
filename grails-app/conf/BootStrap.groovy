class BootStrap {

    def init = { servletContext ->
        log.info("Inicializando aplicacion")
        log.info "Validando roles"
        def rolAdmin = general.Rol.findByAuthority('ROLE_ADMIN')
        if (general.Rol.count() != 2) {
            if (!rolAdmin) {
                rolAdmin = new general.Rol(authority: 'ROLE_ADMIN').save(flush:true)
            }

            def rolPaciente = general.Rol.findByAuthority('ROLE_PACIENTE')
            if (!rolPaciente) {
                rolPaciente = new general.Rol(authority: 'ROLE_PACIENTE').save(flush:true)
            }
        }

        log.info "Validando usuarios"
        def admin = general.UsuarioRol.findByRol(rolAdmin)
        if (!admin) {
            admin = new general.Usuario(
                username:'admin'
                ,password:'admin'
            )
            admin.save(flush:true)
            general.UsuarioRol.create(admin, rolAdmin, true)
        }

        log.info("Aplicacion inicializada")
    }
    def destroy = {
    }
}

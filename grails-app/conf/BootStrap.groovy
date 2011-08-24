class BootStrap {

    def init = { servletContext ->
        log.info("Inicializando aplicacion")
        log.info "Validando roles"
        def rolAdmin = general.Rol.findByAuthority('ROLE_ADMIN')
        if (!rolAdmin) {
            rolAdmin = new general.Rol(authority: 'ROLE_ADMIN').save(flush:true)
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

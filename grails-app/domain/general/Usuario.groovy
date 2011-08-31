package general

import org.apache.commons.lang.builder.HashCodeBuilder

class Usuario {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

	boolean equals(other) {
		if (!(other instanceof Usuario)) {
			return false
		}

		other.id == this.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append(this.id)
		builder.toHashCode()
	}

    String toString() {
        return username
    }
}

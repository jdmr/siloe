package general

import org.apache.commons.lang.builder.HashCodeBuilder

class Rol {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}

	boolean equals(other) {
		if (!(other instanceof Rol)) {
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
        return authority
    }
}

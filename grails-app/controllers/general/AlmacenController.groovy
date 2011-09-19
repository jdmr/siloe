package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class AlmacenController {
    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[almacens: Almacen.list(params), totalDeAlmacens: Almacen.count()]
	}

    def nuevo = {
        def almacen = new Almacen()
        almacen.properties = params
        return [almacen: almacen]
    }

    def crea = {
        def almacen = new Almacen(params)
        if (almacen.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'almacen.label', default: 'Almacen'), almacen.id])
            redirect(action: "ver", id: almacen.id)
        }
        else {
            render(view: "nuevo", model: [almacen: almacen])
        }
    }

    def ver = {
        def almacen = Almacen.get(params.id)
        if (!almacen) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.id])
            redirect(action: "lista")
        }
        else {
            [almacen: almacen]
        }
    }

    def edita = {
        def almacen = Almacen.get(params.id)
        if (!almacen) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.id])
            redirect(action: "lista")
        }
        else {
            return [almacen: almacen]
        }
    }

    def actualiza = {
        def almacen = Almacen.get(params.id)
        if (almacen) {
            if (params.version) {
                def version = params.version.toLong()
                if (almacen.version > version) {
                    
                    almacen.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'almacen.label', default: 'Almacen')] as Object[], "Another user has updated this Almacen while you were editing")
                    render(view: "edita", model: [almacen: almacen])
                    return
                }
            }
            almacen.properties = params
            if (!almacen.hasErrors() && almacen.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'almacen.label', default: 'Almacen'), almacen.id])
                redirect(action: "ver", id: almacen.id)
            }
            else {
                render(view: "edita", model: [almacen: almacen])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def almacen = Almacen.get(params.id)
        if (almacen) {
            try {
                almacen.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.id])
            redirect(action: "lista")
        }
    }
}

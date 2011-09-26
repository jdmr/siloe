package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class HospitalController {
    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index () {
        redirect(action: "lista", params: params)
    }

	def lista () {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[hospitals: Hospital.list(params), totalDeHospitals: Hospital.count()]
	}

    @Secured(['ROLE_DOCTOR'])
    def nuevo () {
        def hospital = new Hospital()
        hospital.properties = params
        return [hospital: hospital]
    }

    //no cambiar el nombre por que con el nombre "crea" no jala
    @Secured(['ROLE_DOCTOR'])
    def creaste () {
        def hospital = new Hospital(params)
        if (hospital.save(flush: true)) {
            flash.message = message(code: "El hospital $hospital.nombre ha sido creado")
            redirect(action: 'ver', id: hospital.id)
        } else {
            log.error("No se pudo crear el hospital")
            render(view:'index', model:[hopital: hospital])
        }
    }
    
    def ver () {
        def hospital = Hospital.get(params.id)
        if (!hospital) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hospital.label', default: 'Hospital'), params.id])
            redirect(action: "lista")
        }
        else {
            [hospital: hospital]
        }
    }

    def edita () {
        def hospital = Hospital.get(params.id)
        if (!hospital) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hospital.label', default: 'Hospital'), params.id])
            redirect(action: "lista")
        }
        else {
            return [hospital: hospital]
        }
    }

    def actualizamos () {
    println '1=========='
        def hospital = Hospital.get(params.id)
        if (hospital) {
            println '2=========='
            if (params.version) {
                println '3=========='
                def version = params.version.toLong()
                if (hospital.version > version) {
                        println '4=========='
                    hospital.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'hospital.label', default: 'Hospital')] as Object[], "Another user has updated this Hospital while you were editing")
                    render(view: "edita", model: [hospital: hospital])
                    return
                }
            }
                println '5=========='
            hospital.properties = params
            if (!hospital.hasErrors() && hospital.save(flush: true)) {
                println '6=========='
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hospital.label', default: 'Hospital'), hospital.id])
                redirect(action: "ver", id: hospital.id)
            }
            else {
                println '7=========='
                render(view: "edita", model: [hospital: hospital])
            }
        }
        else {
            println '8=========='
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hospital.label', default: 'Hospital'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina () {
        def hospital = Hospital.get(params.id)
        if (hospital) {
            try {
                hospital.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hospital.label', default: 'Hospital'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'hospital.label', default: 'Hospital'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hospital.label', default: 'Hospital'), params.id])
            redirect(action: "lista")
        }
    }
    
    @Secured(['ROLE_HOSPITAL'])
    def pendientes () {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [pendientes: DoctorHospital.findAllByAutorizado(false, params), totalDePendientes: DoctorHospital.countByAutorizado(false)]
    }
    
    @Secured(['ROLE_HOSPITAL'])
    def autorizar () {
        def pendiente = DoctorHospital.get(params.id)
        pendiente.autorizado = true
        pendiente.save()
        redirect (action: "pendientes")
    }
    
    @Secured(['ROLE_HOSPITAL'])
    def rechazar () {
        def pendiente = DoctorHospital.get(params.id)
        pendiente.autorizado = true
        pendiente.delete()
        redirect (action: "pendientes")
    }
    
}

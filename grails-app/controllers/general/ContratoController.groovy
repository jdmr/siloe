package general

import org.springframework.dao.DataIntegrityViolationException

class ContratoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [contratoInstanceList: Contrato.list(params), contratoInstanceTotal: Contrato.count()]
    }

    def create() {
        [contratoInstance: new Contrato(params)]
    }

    def save() {
        def contratoInstance = new Contrato(params)
        if (!contratoInstance.save(flush: true)) {
            render(view: "create", model: [contratoInstance: contratoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'contrato.label', default: 'Contrato'), contratoInstance.id])
        redirect(action: "show", id: contratoInstance.id)
    }

    def show() {
        def contratoInstance = Contrato.get(params.id)
        if (!contratoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'contrato.label', default: 'Contrato'), params.id])
            redirect(action: "list")
            return
        }

        [contratoInstance: contratoInstance]
    }

    def edit() {
        def contratoInstance = Contrato.get(params.id)
        if (!contratoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contrato.label', default: 'Contrato'), params.id])
            redirect(action: "list")
            return
        }

        [contratoInstance: contratoInstance]
    }

    def update() {
        def contratoInstance = Contrato.get(params.id)
        if (!contratoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contrato.label', default: 'Contrato'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (contratoInstance.version > version) {
                contratoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'contrato.label', default: 'Contrato')] as Object[],
                          "Another user has updated this Contrato while you were editing")
                render(view: "edit", model: [contratoInstance: contratoInstance])
                return
            }
        }

        contratoInstance.properties = params

        if (!contratoInstance.save(flush: true)) {
            render(view: "edit", model: [contratoInstance: contratoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'contrato.label', default: 'Contrato'), contratoInstance.id])
        redirect(action: "show", id: contratoInstance.id)
    }

    def delete() {
        def contratoInstance = Contrato.get(params.id)
        if (!contratoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'contrato.label', default: 'Contrato'), params.id])
            redirect(action: "list")
            return
        }

        try {
            contratoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'contrato.label', default: 'Contrato'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'contrato.label', default: 'Contrato'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

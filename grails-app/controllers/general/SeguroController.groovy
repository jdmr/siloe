package general

import org.springframework.dao.DataIntegrityViolationException

//@Secured('ROLE_ADMIN')
class SeguroController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [seguroInstanceList: Seguro.list(params), seguroInstanceTotal: Seguro.count()]
    }

    def create() {
        [seguroInstance: new Seguro(params)]
    }

    def save() {
        def seguroInstance = new Seguro(params)
        if (!seguroInstance.save(flush: true)) {
            render(view: "create", model: [seguroInstance: seguroInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'seguro.label', default: 'Seguro'), seguroInstance.id])
        redirect(action: "show", id: seguroInstance.id)
    }

    def show() {
        def seguroInstance = Seguro.get(params.id)
        if (!seguroInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'seguro.label', default: 'Seguro'), params.id])
            redirect(action: "list")
            return
        }

        [seguroInstance: seguroInstance]
    }

    def edit() {
        def seguroInstance = Seguro.get(params.id)
        if (!seguroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seguro.label', default: 'Seguro'), params.id])
            redirect(action: "list")
            return
        }

        [seguroInstance: seguroInstance]
    }

    def update() {
        def seguroInstance = Seguro.get(params.id)
        if (!seguroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'seguro.label', default: 'Seguro'), params.id])
            redirect(action: "list")
            return
        }
        if (params.version) {
            def version = params.version.toLong()
            if (seguroInstance.version > version) {
                seguroInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'seguro.label', default: 'Seguro')] as Object[],
                          "Another user has updated this Seguro while you were editing")
                render(view: "edit", model: [seguroInstance: seguroInstance])
                return
            }
        }

        seguroInstance.properties = params
        if (!seguroInstance.save(flush: true)) {
            render(view: "edit", model: [seguroInstance: seguroInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'seguro.label', default: 'Seguro'), seguroInstance.id])
        redirect(action: "show", id: seguroInstance.id)
    }

    def delete() {
        def seguroInstance = Seguro.get(params.id)
        if (!seguroInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'seguro.label', default: 'Seguro'), params.id])
            redirect(action: "list")
            return
        }

        try {
            seguroInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'seguro.label', default: 'Seguro'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'seguro.label', default: 'Seguro'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

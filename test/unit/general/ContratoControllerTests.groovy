package general



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(ContratoController)
@Mock([Contrato,Seguro])
class ContratoControllerTests {
	
	def seguroContoller = new SeguroController()

    void testIndex() {
        controller.index()
        assert "/contrato/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.contratoInstanceList.size() == 0
        assert model.contratoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.contratoInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.contratoInstance != null
        assert view == '/contrato/create'

        response.reset()
	def seguro = new Seguro(
	nombre : 'seguro'
	).save()
	
        params.folio = 'contrato'
	params.seguro = seguro

        controller.save()

        assert response.redirectedUrl == '/contrato/show/1'
        assert controller.flash.message != null
        assert Contrato.count() == 1
    }
   
 void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/contrato/list'


        def contrato = new Contrato()
	contrato.folio = 'contrato'
	def seguro = new Seguro(
	nombre:'seguro'
	).save()

	contrato.seguro = seguro

        assert contrato.save() != null

        params.id = contrato.id

        def model = controller.show()

        assert model.contratoInstance == contrato
    }
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/contrato/list'


        def contrato = new Contrato()

	contrato.folio = 'contrato'
	def seguro = new Seguro(
	nombre: 'seguro'
	).save()
	contrato.seguro = seguro

        assert contrato.save() != null

        params.id = contrato.id

        def model = controller.edit()

        assert model.contratoInstance == contrato
    }
    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/contrato/list'

        response.reset()


        def contrato = new Contrato()

	contrato.folio = 'contrato'
	contrato.version = 8
	def seguro = new Seguro(
	nombre:'seguro'
	).save()
	contrato.seguro = seguro

        assert contrato.save() != null
	def contratoVersion = contrato.version
       
        params.id = contrato.id
	params.version = 5

        controller.update()

        assert view == "/contrato/edit"
        assert model.contratoInstance != null

        contrato.clearErrors()

        params.version = 8
        controller.update()

        assert response.redirectedUrl == "/contrato/show/$contrato.id"
        assert flash.message != null
    }
    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/contrato/list'

        response.reset()

        def contrato = new Contrato()
	contrato.folio = 'contratoDelete'
	def seguro = new Seguro(
	nombre:'seguro'
	).save()
	contrato.seguro = seguro
        
        assert contrato.save() != null
        assert Contrato.count() == 1

        params.id = contrato.id

        controller.delete()

        assert Contrato.count() == 0
        assert Contrato.get(contrato.id) == null
        assert response.redirectedUrl == '/contrato/list'
    }
}

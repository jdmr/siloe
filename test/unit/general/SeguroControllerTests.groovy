package general



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(SeguroController)
@Mock(Seguro)
class SeguroControllerTests {

    void testIndex() {
        controller.index()
        assert "/seguro/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.seguroInstanceList.size() == 0
        assert model.seguroInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.seguroInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.seguroInstance != null
        assert view == '/seguro/create'

        response.reset()

        params.nombre = 'seguro'

        controller.save()

        assert response.redirectedUrl == '/seguro/show/1'
        assert controller.flash.message != null
        assert Seguro.count() == 1
	def seguroTest = Seguro.findByNombre('seguro') 
	assert seguroTest.nombre == 'seguro'
	
    }
	
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/seguro/list'


        def seguro = new Seguro()

        seguro.nombre = 'seguro'

        assert seguro.save() != null

        params.id = seguro.id

        def model = controller.show()

        assert model.seguroInstance == seguro
 


 }
	
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/seguro/list'


        def seguro = new Seguro()

        seguro.nombre = 'seguro'

        assert seguro.save() != null

        params.id = seguro.id

        def model = controller.edit()

        assert model.seguroInstance == seguro
    }
	
    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/seguro/list'

        response.reset()


        def seguro = new Seguro()

        seguro.nombre='seguro'
	seguro.version = 8

        assert seguro.save() != null
	def seguroVersion = seguro.version
        params.id = seguro.id
	params.nombre ='seguro' 
	params.version =5
        controller.update()

        assert view == "/seguro/edit"
        assert model.seguroInstance != null

        seguro.clearErrors()
	
	params.version = seguroVersion	
	params.nombre = 'seguro2'

        controller.update()

        assert response.redirectedUrl == "/seguro/show/$seguro.id"
        assert flash.message != null
	def seguroTestUpdate = Seguro.findByNombre('seguro2')
	assert seguroTestUpdate.nombre == 'seguro2'
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/seguro/list'

        response.reset()

        def seguro = new Seguro()
	seguro.nombre = 'seguroDelete'
        
        assert seguro.save() != null
        assert Seguro.count() == 1

        params.id = seguro.id

        controller.delete()

        assert Seguro.count() == 0
        assert Seguro.get(seguro.id) == null
        assert response.redirectedUrl == '/seguro/list'
    }
}

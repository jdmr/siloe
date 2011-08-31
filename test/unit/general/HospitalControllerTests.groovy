package general


import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HospitalController)
class HospitalControllerTests {

    @Test
    void debieraMostrarPaginaDeRegistro() {
        def model = controller.index()
        assert model
        assert model.hospital
    }
    
    @Test
    void debieraRegistrarUnHospital() {
        
        params.nombre = 'test'
        params.direccion = 'test'
        params.telefono = 'test'
        
        controller.crea()
        
        assert response.redirectedUrl == '/hospital/ver/1'
        assert flash.message != null
        assert flash.message == 'El hospital test ha sido creado'
        assert Hospital.count() == 1
        def x = Hospital.get(1)
        assert 'test' == x.nombre
    }
}

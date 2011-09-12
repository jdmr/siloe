package general


import grails.test.mixin.*
import org.junit.*


/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HospitalController)
@Mock([Hospital, Almacen])
class HospitalControllerTests {

    @Test
    void debieraMostrarPaginaDeHospitales() {
        def model = controller.index()
        assert response.redirectedUrl == '/hospital/lista'
    }
    
    
    @Test
    void debieraRegistrarUnHospital() {
        assert Hospital.count() == 0
        
        params.nombre = 'test'
        params.direccion = 'test'
        params.telefono = 'test'

        controller.creaste()

        assert response.redirectedUrl == '/hospital/ver/1'
        assert flash.message != null
        assert flash.message == 'El hospital test ha sido creado'
        assert Hospital.count() == 1
        def x = Hospital.get(1)
        assert 'test' == x.nombre
    }
    
    @Test
    void hospitaldebieraAgregarAlmacen() {
        def hospital = new Hospital(
            nombre: 'test'
            , direccion: 'test'
            , telefono: 'test'
        ).save()
        
        def almacen = new Almacen(
            nombre: 'test'
            , direccion: 'test'
            , telefono: 'test'
            , principal: true
            , hospital: new Hospital() //se debe traer en secion
        ).save()
        
        def model = controller.edita()
        assert model.hospital
        
        assert response.redirectedUrl == '/hospital/edita/1'
        params.almacen = almacen
        controller.actualiza()
        assert response.redirectedUrl == '/hospital/ver/1'

        assert hospital.almacen == almacen //hospital debe estar en secion
    }
}

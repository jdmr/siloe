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
        //se elimina esto cuando el hospital este en sesion
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
            , hospital: hospital //se debe traer en secion
        ).save()
 
        params.almacenes = almacen
        println 'almacenes ' + params.almacenes
        println 'hospital ' + hospital.almacenes
        controller.actualizamos()
        println 'hospital ' + hospital.almacenes

        
        //assert hospital.findByAlmacen(almacen.nombre) == almacen //hospital debe estar en secion
    }
}

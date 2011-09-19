package general


import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AlmacenController)
@Mock([Almacen, Hospital])
class AlmacenControllerTests {

    @Test
    void debieraMostrarPaginaDeRegistro() {
        controller.index()
        assert response.redirectedUrl == '/almacen/lista'
    }
    
    @Test
    void debieraRegistrarUnAlmacen() {
        def hospital = new Hospital(
            nombre: 'TEST'
            , direccion: 'TEST'
            , telefono: 'TELEFONO'
        ).save()

        assert hospital
        assert hospital.id

        params.nombre = 'test'
        params.direccion = 'test'
        params.telefono = 'test'
        params.hospital = hospital
        
        controller.crea()
        
        assert response.redirectedUrl == '/hospital/ver/1'
        assert flash.message != null
        assert flash.message == 'El hospital test ha sido creado'
        assert Almacen.count() == 1
        def x = Almacen.get(1)
        assert 'test' == x.nombre
    }
}

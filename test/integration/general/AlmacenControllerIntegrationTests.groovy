package general

import static org.junit.Assert.*
import org.junit.*

class AlmacenControllerIntegrationTests {
    
    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void debieraCrearUnAlmacen() {
        def controller = new AlmacenController()

        def hospital = new Hospital(
            nombre : 'TEST-1'
            , direccion : 'TEST-1'
            , telefono : 'TEST-1'
        ).save()

        def params = controller.params
        params.nombre = 'TEST-1'
        params.direccion = 'TEST-1'
        params.telefono = 'TEST-1'
        params.hospital = hospital

        controller.crea()

        def almacen = Almacen.findByNombre('TEST-1')
        assert almacen
    }
}

package general

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(UsuarioController)
@Mock([Usuario, UsuarioRol, Rol])
class PacienteRegistraDoctoresTests {
   
   @Test
   void debieraVerificarRol() {
           
           def model = controller.index()
           assert model
           assert model.usuario
        }
 
   @Test
    void registraDoctor() {
        Usuario.metaClass.encodePassword << {password = "AAAAA"}
        assert Usuario.count() == 0
        
        params.username = 'test'
        params.password = 'test'
        
        controller.crea()
        
        assert response.redirectedUrl == '/usuario/ver/1'
        assert flash.message != null
        assert flash.message == 'El usuario test ha sido creado'
        assert Usuario.count() == 1
        def x = Usuario.get(1)
        assert 'AAAAA' == x.password
    }
}

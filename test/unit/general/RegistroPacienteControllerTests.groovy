package general


import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RegistroPacienteController)
@Mock([Usuario, UsuarioRol, Rol])
class RegistroPacienteControllerTests {

    @Test
    void debieraMostrarPaginaDeRegistro() {
        def model = controller.index()
        assert model
        assert model.usuario
    }
    
    @Test
    void debieraRegistrarUnPaciente() {
        assert Usuario.count() == 0
        
        params.username = 'test'
        params.password = 'test'
        
        controller.crea()
        
        assert response.redirectedUrl == '/registroPaciente/ver/1'
        assert flash.message != null
        assert flash.message == 'El usuario test ha sido creado'
        assert Usuario.count() == 1
    }
}

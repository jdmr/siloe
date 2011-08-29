
class RegistroPacienteFunctionalTests extends functionaltestplugin.FunctionalTestCase {
  void testRegistroPaciente() {
    get('/registroPaciente')
    assertStatus 200

    form {
      username = "test-01"
      password = "test-01"
      click "Create"
    }

    assertStatus 200
    assertContentContains "Registrado"		
  }
}


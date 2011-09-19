
class AlmacenFunctionalTests extends functionaltestplugin.FunctionalTestCase {
  void testRegistroAlmacen() {
    get('/almacen')
    assertStatus 200

    form {
      nombre = "test-01"
      direccion = "test-01"
      telefono = "test-01"
      principal = true
      click "Create"
    }

    assertStatus 200
    assertContentContains "Registrado"		
  }
}


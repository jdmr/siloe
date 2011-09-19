
class HospitalFunctionalTests extends functionaltestplugin.FunctionalTestCase {
  void testRegistroHospital() {
    get('/hospital')
    assertStatus 200

    form {
      nombre = "test-01"
      direccion = "test-01"
      telefono = "test-01"
      click "Create"
    }

    assertStatus 200
    assertContentContains "Registrado"		
  }
}


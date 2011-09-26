package general

class DoctorHospital {
    
    Usuario doctor
    Hospital hospital
    Boolean autorizado = false

    static constraints = {
    }
    
    static mapping = {
        doctor cascade:'save-update', lazy:false
        hospital lazy:false
    }
}

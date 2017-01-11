package softuni.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.entities.*;
import softuni.services.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Console implements CommandLineRunner {
    private final DiagnoseService diagnoseService;
    private final MedicamentService medicamentService;
    private final PatientService patientService;
    private final VisitationService visitationService;
    private final DoctorService doctorService;

    @Autowired
    public Console(DiagnoseService diagnoseService, MedicamentService medicamentService, PatientService patientService, VisitationService visitationService, DoctorService doctorService) {
        this.diagnoseService = diagnoseService;
        this.medicamentService = medicamentService;
        this.patientService = patientService;
        this.visitationService = visitationService;
        this.doctorService = doctorService;
    }

    @Override
    public void run(String... strings) throws Exception {
        Patient patient = new Patient();
        patient.setFirstName("Pesho");
        patient.setLastName("Petrov");
        patient.setDateOfBirth(new Date());
        patient.setEmail("ivan@ivan.com");
        patient.setAddres("Sofia");
        patient.setHaveMedicalInsurance(true);

        Doctor doctor = new Doctor();
        doctor.setName("Petrov");
        doctor.setSpeciality("AR");


        Visitation visitation = new Visitation();
        visitation.setDate(new Date());
        visitation.setComment("Good");
        visitation.setPatient(patient);
        visitation.setDoctor(doctor);

        Diagnose diagnose = new Diagnose();
        diagnose.setName("Cough");
        diagnose.setComment("cough");
        Set<Patient> patients = new HashSet<>();
        patients.add(patient);
        diagnose.setPatients(patients);

        Medicament medicament = new Medicament();
        medicament.setName("Neo-angin");

        Set<Medicament> medicaments = new HashSet<>();
        medicaments.add(medicament);

        this.medicamentService.persist(medicament);
        patient.setMedicaments(medicaments);

        this.doctorService.persist(doctor);
        this.patientService.persist(patient);
        this.visitationService.persist(visitation);
        this.diagnoseService.persist(diagnose);

        List<Doctor> doctors = this.doctorService.getAll();

        for (Doctor doctor1 : doctors) {
            for (Visitation visitation1 : doctor1.getVisitations()) {
                System.out.println(visitation1.getComment());
            }
        }

    }
}

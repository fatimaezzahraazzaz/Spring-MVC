package ma.fs.hospital.repository;

import ma.fs.hospital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findPatientByNomContains(String keyword, Pageable pageable);

}

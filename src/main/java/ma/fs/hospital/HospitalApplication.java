package ma.fs.hospital;

import ma.fs.hospital.entities.Patient;
import ma.fs.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class HospitalApplication   {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(new Patient(null,"Ahmed",new Date(),false,230));
            patientRepository.save(new Patient(null,"Malak",new Date(),false,500));
            patientRepository.save(new Patient(null,"Sara",new Date(),true,100));

        };
    }


}


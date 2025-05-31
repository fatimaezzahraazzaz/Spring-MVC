package ma.fs.hospital;

import ma.fs.hospital.entities.Patient;
import ma.fs.hospital.repository.PatientRepository;
import ma.fs.hospital.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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
  //  @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        return args -> {
            UserDetails u1 =jdbcUserDetailsManager.loadUserByUsername("user11");
            if(u1==null)

                jdbcUserDetailsManager.createUser(
                        User.withUsername("user11").password(new BCryptPasswordEncoder().encode("1234")).roles("USER").build());

            UserDetails u2 =jdbcUserDetailsManager.loadUserByUsername("user11");
            if(u2==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user22").password(new BCryptPasswordEncoder().encode("1234")).roles("USER").build());
            UserDetails u3 =jdbcUserDetailsManager.loadUserByUsername("user11");
            if(u3==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin2").password(new BCryptPasswordEncoder().encode("1234")).roles("USER","ADMIN").build());


        };


    }


    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1","1234","user1@gmail.com","1234");
            accountService.addNewUser("user2","1234","user2@gmail.com","1234");
            accountService.addNewUser("admin","1234","admin@gmail.com","1234");

            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");


        };
    }



    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


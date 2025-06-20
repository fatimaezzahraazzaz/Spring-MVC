package ma.fs.hospital.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.fs.hospital.entities.Patient;
import ma.fs.hospital.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

        private PatientRepository patientRepository;

        @GetMapping("/user/index")
        public String index(Model model,
                            @RequestParam(name="page", defaultValue="0") int page,
                            @RequestParam(name="size", defaultValue="4") int size,
                            @RequestParam(name="keyword", defaultValue="") String keyword) {

            Page<Patient> pagePatients = patientRepository.findPatientByNomContains(keyword, PageRequest.of(page, size));
            model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
            model.addAttribute("currentPage", page);
            model.addAttribute("listPatients", pagePatients.getContent());
            model.addAttribute("keyword", keyword);
            return "patients";
        }

        @GetMapping("/admin/delete")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public String delete(@RequestParam(name = "id") Long id,
                             @RequestParam(name="keyword",defaultValue ="") String keyword,
                             @RequestParam(name = "page",defaultValue = "0")  int page) {
            patientRepository.deleteById(id);
            return "redirect:/user/index?page=" + page+"&keyword=" + keyword;
        }
        @GetMapping("/")
        public String home() {
            return "redirect:/user/index";
        }
        @GetMapping("/patients")
        public List<Patient> lisPatients() {
            return patientRepository.findAll();
        }
        @GetMapping("/admin/formPatients")
        @PreAuthorize("hasRole('ROLE_ADMIN')")

        public String formPatients(Model model) {
            model.addAttribute("patient", new Patient());
            return "formPatients";
        }

        @PostMapping(path="/admin/save")
        @PreAuthorize("hasRole('ROLE_ADMIN')")

        public String save(Model model ,@Valid  Patient patient, BindingResult bindingResult,
                           @RequestParam(defaultValue = "0")  int page,
                           @RequestParam(defaultValue = "") String keyword )
        {
            if(bindingResult.hasErrors()) return "formPatients";
            patientRepository.save(patient);

            return "redirect:/user/index?page=" + page + "&keyword=" + keyword;

        }
        @GetMapping("/admin/editPatient")
        @PreAuthorize("hasRole('ROLE_ADMIN')")

        public String editPatients(Model model,Long id,String keyword,int page) {
            Patient patient = patientRepository.findById(id).orElse(null);
            if(patient== null) throw new RuntimeException("Patient introuvable");
            model.addAttribute("patient", patient);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
            return "editePatient";
        }

    }




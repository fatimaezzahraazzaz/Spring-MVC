package ma.fs.hospital.web;

import lombok.AllArgsConstructor;
import ma.fs.hospital.entities.Patient;
import ma.fs.hospital.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

        private PatientRepository patientRepository;

        @GetMapping("/index")
        public String index(Model model,
                            @RequestParam(name="page", defaultValue="0") int page,
                            @RequestParam(name="size", defaultValue="4") int size,
                            @RequestParam(name="Keyword", defaultValue="") String kw) {

            Page<Patient> pagePatients = patientRepository.findPatientByNomContains(kw, PageRequest.of(page, size));
            model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
            model.addAttribute("currentPage", page);
            model.addAttribute("listPatients", pagePatients.getContent());
            model.addAttribute("Keyword", kw);
            return "patients";
        }

        @GetMapping("/delete")
        public String delete(@RequestParam(name = "id") Long id,
                             @RequestParam(name="keyword",defaultValue ="") String keyword,
                             @RequestParam(name = "page",defaultValue = "0")  int page) {
            patientRepository.deleteById(id);
            return "redirect:/index?page=" + page+"&keyword=" + keyword;
        }
        @GetMapping("/")
        public String home() {
            return "redirect:/index";
        }
    }




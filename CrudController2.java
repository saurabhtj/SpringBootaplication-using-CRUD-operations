package springbootweb1.project2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CrudController2 
{
    Student2 students[] = new Student2[10];

    int count = 0;

    
    @PostMapping("/saveStudent2")
    public String saveStudent(
            @RequestParam int rollno,
            @RequestParam String name,
            @RequestParam String city)
    {
        Student2 s = new Student2();

        s.setRollno(rollno);
        s.setName(name);
        s.setCity(city);

        students[count] = s;

        count++;

        return "redirect:/viewStudents2";
    }

    
    @GetMapping("/viewStudents2")
    public String viewStudents(Model model)
    {
        model.addAttribute("data", students);

        return "viewstudents2";
    }

    
    @PostMapping("/searchStudent2")
    public String searchStudent(
            @RequestParam int rollno,
            Model model)
    {
        for(int i=0; i<count; i++)
        {
            if(students[i].getRollno() == rollno)
            {
                model.addAttribute("s", students[i]);

                return "searchresult2";
            }
        }

        model.addAttribute("msg", "Student Not Found");

        return "searchresult2";
    }

    
    @PostMapping("/updateStudent2")
    public String updateStudent(
            @RequestParam int rollno,
            @RequestParam String city,
            Model model)
    {
        for(int i=0; i<count; i++)
        {
            if(students[i].getRollno() == rollno)
            {
                students[i].setCity(city);

                model.addAttribute("msg", "Student Updated");

                return "updateresult2";
            }
        }

        model.addAttribute("msg", "Student Not Found");

        return "updateresult2";
    }

   
    @PostMapping("/deleteStudent2")
    public String deleteStudent(
            @RequestParam int rollno)
    {
        for(int i=0; i<count; i++)
        {
            if(students[i].getRollno() == rollno)
            {
                for(int j=i; j<count-1; j++)
                {
                    students[j] = students[j+1];
                }

                students[count-1] = null;

                count--;

                break;
            }
        }

        return "redirect:/viewStudents2";
    }
}

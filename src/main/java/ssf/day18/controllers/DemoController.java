package ssf.day18.controllers;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import ssf.day18.config.Constants;
import ssf.day18.models.Person;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired @Qualifier(Constants.REDIS_TEMPLATE_01)
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/health")
    public ModelAndView getHealth() {
        ModelAndView mav = new ModelAndView();

        try {
            checkHealth();

            mav.setViewName("healthy");
            mav.setStatus(HttpStatusCode.valueOf(200));
        } catch(Exception ex) {
            mav.setViewName("unhealthy");
            mav.setStatus(HttpStatusCode.valueOf(500));
        }

        return mav;
    }

    private void checkHealth() throws Exception {
        // Get rand no. 0-10
        Random rand = new Random();

        // Throws exception if rand = [0-5]
        if(rand.nextInt(11) <= 5)
            throw new Exception();
    }

    @GetMapping("/time")
    public String displayDateTime(Model model) throws ParseException {

        // YYYY-MM-dd HH:mm:ss      1980-07-25 15:30:40
        // YYYY-MMM-dd HH:mm:ss     1980-JUL-25 15:30:40
        // YYYY-MM-dd hh:mm:ss a    1980-07-25 3:30:40 PM
        String strDate = "1980-07-25 15:30:40";
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date demoDate = sdf.parse(strDate);
        
        // epoch ms -> Date
        long epochMilliseconds = demoDate.getTime();
        Date retrievedDate = new Date(epochMilliseconds); 

        model.addAttribute("date", retrievedDate);

        return "time";        
    }

    @GetMapping("/test")
    @ResponseBody   // to generate content only (instead of template)
    public List<String> testHash() {
        List<String> wordList = new ArrayList<>();
        wordList.add("Marina");
        wordList.add("Park");
        wordList.add("Hotel");
        wordList.add("Bridge");
        wordList.add("Merlion");
        wordList.add("Cruise");

        // Save list to hash in redis
        redisTemplate.opsForHash().put("WORDS", "map", wordList.toString());

        // Retrieve list from redist
        Object wordsObj = redisTemplate.opsForHash().get("WORDS", "map");

        // Process & convert from str obj to list of str
        List<String> retrievedWordList = new ArrayList<>();
        String str = wordsObj.toString();
        str = str.replace("[", "");
        str = str.replace("]", "");
        str = str.replace(" ", "");
        String[] strArr = str.split(",");
        for (String s : strArr) {
            retrievedWordList.add(s);
        }        

        return retrievedWordList;
    } 

    @GetMapping("/testPerson")
    @ResponseBody   
    public List<Person> testPersonHash() {
        List<Person> personsList = new ArrayList<>();
        Person p = new Person(1, "Fred", "fred@gmail.com", "123123", "89998999");
        personsList.add(p);

        p = new Person(2, "Bob", "bob@gmail.com", "321321", "98889888");
        personsList.add(p);

        // Save list to hash in redis
        redisTemplate.opsForHash().put("PERSONS", "map", personsList.toString());

        // Retrieve list from redist
        Object personsObj = redisTemplate.opsForHash().get("PERSONS", "map");

        // Process & convert from str json to list of persons
        String json = personsObj.toString();
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonArray jsonArr =  reader.readArray();

        List<Person> retrievedPersonsList = new ArrayList<>();

        for(int i = 0; i < jsonArr.size(); i++) {
            p = Person.jsonToPerson(jsonArr.get(i).toString());
            retrievedPersonsList.add(p);
        }
            
        return retrievedPersonsList;
    }
}

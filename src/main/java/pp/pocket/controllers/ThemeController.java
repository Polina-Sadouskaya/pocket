package pp.pocket.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.pocket.entities.Theme;
import pp.pocket.services.ThemeService;

import java.util.HashMap;


@RestController
@RequestMapping("/themes")
public class ThemeController extends Controller {
    public static int mockUserId = 1;
    public HashMap<String, Error> error = new HashMap<>();
    @GetMapping
    public ResponseEntity getThemes() {
            try{
                HashMap<String, Theme> result = ThemeService.getThemes(mockUserId);
                return new ResponseEntity(result.values(), HttpStatus.OK);
            } catch (Exception e){
                error.clear();
                error.put("error", new Error(e.getLocalizedMessage()));
                return new ResponseEntity(error.values(), HttpStatus.BAD_REQUEST);
            }
    }

    @PostMapping
    public ResponseEntity theme(@RequestBody Theme request){
            try{
                request.setUserId(mockUserId);
                Theme createdOne = ThemeService.add(request);
                HashMap<String, Theme> result = new HashMap<>();
                result.put("created", createdOne);
                return new ResponseEntity(result.values(), HttpStatus.CREATED);
            } catch (Exception e){
                error.clear();
                error.put("error", new Error(e.getLocalizedMessage()));
                return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
            }
    }
}

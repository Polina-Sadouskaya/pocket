package pp.pocket.controllers;

import org.springframework.web.bind.annotation.*;
import pp.pocket.entities.Theme;
import pp.pocket.response.BaseResponse;
import pp.pocket.services.ThemeService;
import pp.pocket.services.UserService;

import java.util.List;


@RestController
@RequestMapping("/themes")
public class ThemeController extends Controller {
    @GetMapping
    public BaseResponse getThemes(@RequestHeader(value="Authorization") String creds) {
            try{
                int userId = UserService.checkValidCreds(creds);
                List result = ThemeService.getThemes(String.valueOf(userId));
                return new BaseResponse(SUCCESS_STATUS, SUCCESS_CODE, result);
            } catch (Exception e){
                return new BaseResponse(ERROR_STATUS, ERROR_CODE, e.getMessage());
            }
    }

    @PostMapping
    public BaseResponse theme(@RequestHeader(value="Authorization") String creds, @RequestBody Theme request){
            try{
                int userId = UserService.checkValidCreds(creds);
                request.setUserId(userId);
                Theme result = ThemeService.add(request);
                return new BaseResponse(SUCCESS_STATUS, SUCCESS_CODE_CREATED, result);
            } catch (Exception e){
                return new BaseResponse(ERROR_STATUS, ERROR_CODE, e.getMessage());
            }
    }
}

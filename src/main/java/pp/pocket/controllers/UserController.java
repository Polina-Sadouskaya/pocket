package pp.pocket.controllers;

import org.springframework.web.bind.annotation.*;
import pp.pocket.entities.User;
import pp.pocket.response.BaseResponse;
import pp.pocket.services.UserService;

@RestController
@RequestMapping("/knock")
public class UserController extends Controller {
    @PostMapping
    public BaseResponse registration(@RequestBody User request){
        int result;
            try{
                result = UserService.add(request);
                return new BaseResponse(SUCCESS_STATUS, SUCCESS_CODE_CREATED, "Created user id: "+result);
            } catch (Exception e){
                return new BaseResponse(ERROR_STATUS, ERROR_CODE, e.getMessage());
            }
    }
}

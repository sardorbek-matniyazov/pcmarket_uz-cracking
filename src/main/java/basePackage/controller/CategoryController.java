package basePackage.controller;

import basePackage.payload.CategoryDto;
import basePackage.payload.Status;
import basePackage.service.CategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/category")
public record CategoryController (CategoryService service) {

    @PostMapping(value = "/add")
    public HttpEntity<Status> add(@Valid @RequestBody CategoryDto dto) {
        Status add = service.add(dto);
        return add.isActive() ? ResponseEntity.ok(add) : ResponseEntity.badRequest().body(add);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<Status> update(@Valid @RequestBody CategoryDto dto, @PathVariable Long id) {
        Status add = service.update(id, dto);
        return add.isActive() ? ResponseEntity.ok(add) : ResponseEntity.badRequest().body(add);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<Status> delete(@PathVariable Long id) {
        Status add = service.delete(id);
        return add.isActive() ? ResponseEntity.ok(add) : ResponseEntity.badRequest().body(add);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> getException(
            MethodArgumentNotValidException ex) {
        return handleValidationExceptions(ex);
    }

    public static Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new java.util.HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

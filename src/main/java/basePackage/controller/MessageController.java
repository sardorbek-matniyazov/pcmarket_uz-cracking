package basePackage.controller;

import basePackage.payload.MessageDto;
import basePackage.payload.Status;
import basePackage.service.MessageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static basePackage.controller.CategoryController.handleValidationExceptions;

@RestController
@RequestMapping(value = "/message")
public record MessageController(MessageService service) {

    @GetMapping(value = "/all")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping(value = "/add")
    public HttpEntity<Status> add(@Valid @RequestBody MessageDto dto) {
        Status add = service.add(dto);
        return add.isActive() ? ResponseEntity.ok(add) : ResponseEntity.badRequest().body(add);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<Status> update(@Valid @RequestBody MessageDto dto, @PathVariable Long id) {
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
}

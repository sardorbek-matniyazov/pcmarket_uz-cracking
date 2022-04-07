package basePackage.controller;

import basePackage.payload.BrandDto;
import basePackage.payload.Status;
import basePackage.service.BrandService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static basePackage.controller.CategoryController.handleValidationExceptions;

@RestController
@RequestMapping(value = "/brand")
public record BrandController(BrandService service) {

    @PostMapping(value = "/add")
    public HttpEntity<Status> add(@Valid @RequestBody BrandDto dto) {
        Status add = service.add(dto);
        return add.isActive() ? ResponseEntity.ok(add) : ResponseEntity.badRequest().body(add);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<Status> update(@Valid @RequestBody BrandDto dto, @PathVariable Long id) {
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

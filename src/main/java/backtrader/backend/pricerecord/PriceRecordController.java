package backtrader.backend.pricerecord;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus; 

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pricerecords")
public class PriceRecordController {
    
    private PriceRecordRepository priceRecordRepository;

    public PriceRecordController(PriceRecordRepository priceRecordRepository) {
        this.priceRecordRepository = priceRecordRepository;
    }

    // Retrieve all price records
    @GetMapping("")
    public List<PriceRecord> findAll() {
        return priceRecordRepository.findAll();
    }

    // Retrieve a price record by its ID
    @GetMapping("/{id}")
    public PriceRecord findById(@PathVariable Integer id) {
        Optional<PriceRecord> priceRecord = priceRecordRepository.findById(id);
        if (priceRecord.isPresent()) {
            return priceRecord.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price record not found");
        }
    }

    // Create a new price record
    @ResponseStatus(HttpStatus.CREATED) // return 201
    @PostMapping("")
    public void create(@Valid @RequestBody PriceRecord priceRecord) {
        priceRecordRepository.create(priceRecord);
    }

    // Update an existing price record by its ID
    @ResponseStatus(HttpStatus.NO_CONTENT) // return 204
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody PriceRecord priceRecord, @PathVariable Integer id) {
        priceRecordRepository.update(priceRecord, id);
    }

    // Delete a price record by its ID
    @ResponseStatus(HttpStatus.NO_CONTENT) // return 204
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        priceRecordRepository.delete(id);
    }
}

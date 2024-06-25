package backtrader.backend.pricerecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class PriceRecordJsonLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(PriceRecordJsonLoader.class);
    private final PriceRecordRepository priceRecordRepository;
    private final ObjectMapper objectMapper;

    // Constructor injecting dependencies
    public PriceRecordJsonLoader(ObjectMapper objectMapper, @Qualifier("priceRecordRepository") PriceRecordRepository priceRecordRepository) {
        this.objectMapper = objectMapper;
        this.priceRecordRepository = priceRecordRepository;
    }

    // Method executed on application startup
    @Override
    public void run(String... args) throws Exception {
        // Check if price records exist in the database
        if (priceRecordRepository.count() == 0) {
            try (InputStream inputStream = getClass().getResourceAsStream("/data/pricing_data.json")) {
                // Read JSON data into PriceRecords object
                PriceRecords allPriceRecords = objectMapper.readValue(inputStream, PriceRecords.class);
                // Log information about the number of records being loaded
                log.info("Reading {} price records from JSON data and saving to h2-database...", allPriceRecords.priceRecords().size());
                // Save all price records to the database
                priceRecordRepository.saveAll(allPriceRecords.priceRecords());
            } catch (IOException e) {
                // Throw a runtime exception if reading JSON data fails
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            // Log message indicating that JSON data is not loaded because the database already contains data
            log.info("Not loading price records from JSON data because the collection already contains data.");
        }
    }
}

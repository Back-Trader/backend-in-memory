package backtrader.backend.pricerecord;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class PriceRecordRepository {

    private final JdbcClient jdbcClient;

    // Constructor to initialize JdbcClient
    public PriceRecordRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // Retrieve all records from the priceRecord table
    public List<PriceRecord> findAll() {
        return jdbcClient.sql("select * from priceRecord")
                .query(PriceRecord.class)
                .list();
    }

    // Retrieve a record by its ID from the priceRecord table
    public Optional<PriceRecord> findById(Integer id) {
        return jdbcClient.sql("SELECT id,timestamp,ticker,open,high,low,close,volume FROM priceRecord WHERE id = :id")
                .param("id", id)
                .query(PriceRecord.class)
                .optional();
    }

    // Create a new record in the priceRecord table
    public void create(PriceRecord priceRecord) {
        var updated = jdbcClient.sql("INSERT INTO priceRecord(id,timestamp,ticker,open,high,low,close,volume) values(?,?,?,?,?,?,?,?)")
                .params(List.of(
                        priceRecord.id(),
                        priceRecord.timestamp(),
                        priceRecord.ticker().toString(),
                        priceRecord.open(),
                        priceRecord.high(),
                        priceRecord.low(),
                        priceRecord.close(),
                        priceRecord.volume()))
                .update();

        Assert.state(updated == 1, "Failed to create priceRecord " + priceRecord.ticker());
    }

    // Update an existing record in the priceRecord table
    public void update(PriceRecord priceRecord, Integer id) {
        var updated = jdbcClient.sql("update priceRecord set timestamp = ?, ticker = ?, open = ?, high = ?, low = ?, close = ?, volume = ? where id = ?")
                .params(List.of(
                        priceRecord.timestamp(),
                        priceRecord.ticker().toString(),
                        priceRecord.open(),
                        priceRecord.high(),
                        priceRecord.low(),
                        priceRecord.close(),
                        priceRecord.volume(),
                        id))
                .update();

        Assert.state(updated == 1, "Failed to update priceRecord " + priceRecord.ticker());
    }

    // Delete a record by its ID from the priceRecord table
    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from priceRecord where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete priceRecord " + id);
    }

    // Count the number of records in the priceRecord table
    public int count() {
        return jdbcClient.sql("select * from priceRecord").query().listOfRows().size();
    }

    // Save a list of PriceRecord objects to the priceRecord table
    public void saveAll(List<PriceRecord> priceRecords) {
        priceRecords.stream().forEach(this::create);
    }

    // Find records by ticker in the priceRecord table
    public List<PriceRecord> findByTicker(String ticker) {
        return jdbcClient.sql("select * from priceRecord where ticker = :ticker")
                .param("ticker", ticker)
                .query(PriceRecord.class)
                .list();
    }

}

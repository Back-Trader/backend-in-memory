package backtrader.backend.pricerecord;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotEmpty;

// timestamp of form '2022-12-12T15:00:12.497660' aka up to milliseconds ->ISO 8601 Format

public record PriceRecord(
    Integer id,
    @NotNull LocalDateTime timestamp,
    @NotEmpty String ticker,
    @Positive float open,
    @Positive float high,
    @Positive float low,
    @Positive float close,
    @Positive float volume
) {}

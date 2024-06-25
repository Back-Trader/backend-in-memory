CREATE TABLE IF NOT EXISTS PriceRecord (
    id INT NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    ticker VARCHAR(8) NOT NULL,
    open FLOAT NOT NULL,
    high FLOAT NOT NULL,
    low FLOAT NOT NULL,
    close FLOAT NOT NULL,
    volume FLOAT NOT NULL
);


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    @Mock
    IStockMarket market;

    @InjectMocks
    StocksPortfolio portfolio;

    @BeforeEach
    void setUp() {
        portfolio.setName("Lucas");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTotalValue() {
        when(market.getPrice("TSLA")).thenReturn(3121.18);
        when(market.getPrice("AMZN")).thenReturn(691.93);

        portfolio.addStock(new Stock("TSLA", 500));
        portfolio.addStock(new Stock("AMZ", 500));

        double res = 500 * 3121.18 + 500 * 691.93;

        // test function OK
        assertThat(portfolio.getTotalValue(), is(res)); // hamcrest
        assertEquals(1906555, res); // Junit

        verify(market, times(2)).getPrice(anyString());
    }
}
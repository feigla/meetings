package ru.bogdsvn.location.services.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bogdsvn.location.services.S2Service;


@ExtendWith(MockitoExtension.class)
class S2ServiceTest {
    @ParameterizedTest
    @CsvSource({
            "0, 0, 30, 1152921504606846977",
            "0, 180, 30, 8070450532247928831",
            "0, -180, 30, 8070450532247928833",
            "-25.5, 54.7, 30, 2424862035519024215",
    })
    void givenLatitudeAndLongitudeAndLevel_whenGetCellId_thenReturnCorrectCellId (double latitude, double longitude, int level, long expectedCellId) {
        S2Service s2Service = new S2Service();
        long cellId = s2Service.getCellId(latitude, longitude, level);
        Assertions.assertEquals(expectedCellId, cellId);
    }
}

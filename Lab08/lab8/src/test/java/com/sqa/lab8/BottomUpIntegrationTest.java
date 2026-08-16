package com.sqa.lab8;
/*
นายกานดิทัต นามสุดตา
673380392-1
sec 2
*/
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BottomUpIntegrationTest {

    // =========================================================================
    // STEP 1 (Leaf Level): Test Helper methods / getMultiplier (TC 1 - 4)
    // =========================================================================

    @Test
    @DisplayName("TC-BU-01: DistanceConverter.getMultiplier(kilometer -> meter)")
    public void testTC01_Driver_Distance_KmToMeter() {
        DistanceConverter distance = new DistanceConverter();
        double multiplier = distance.getMultiplier("kilometer", "meter");
        assertEquals(1000.0, multiplier, 0.001);
    }

    @Test
    @DisplayName("TC-BU-02: DistanceConverter.getMultiplier(mile -> kilometer)")
    public void testTC02_Driver_Distance_MileToKm() {
        DistanceConverter distance = new DistanceConverter();
        double multiplier = distance.getMultiplier("mile", "kilometer");
        assertEquals(1.609, multiplier, 0.001);
    }

    @Test
    @DisplayName("TC-BU-03: WeightConverter.getMultiplier(kilogram -> gram) [Defect]")
    public void testTC03_Driver_Weight_KgToGram() {
        WeightConverter weight = new WeightConverter();
        double multiplier = weight.getMultiplier("kilogram", "gram");
        // Expected: 1000.0, Actual in code: 0.001 (due to 1.0/1000)
        assertEquals(1000.0, multiplier, 0.001);
    }

    @Test
    @DisplayName("TC-BU-04: WeightConverter.getMultiplier(lbs -> ounce)")
    public void testTC04_Driver_Weight_LbsToOunce() {
        WeightConverter weight = new WeightConverter();
        double multiplier = weight.getMultiplier("lbs", "ounce");
        assertEquals(16.0, multiplier, 0.001);
    }

    // =========================================================================
    // STEP 2 (Module Level): Test Sub-converters convert() (TC 5 - 8)
    // =========================================================================

    @Test
    @DisplayName("TC-BU-05: DistanceConverter.convert(10 meter -> kilometer)")
    public void testTC05_Driver_DistanceConvert_MToKm() {
        DistanceConverter distance = new DistanceConverter();
        double result = distance.convert(10.0, "meter", "kilometer");
        assertEquals(0.01, result, 0.001);
    }

    @Test
    @DisplayName("TC-BU-06: WeightConverter.convert(2 lbs -> ounce)")
    public void testTC06_Driver_WeightConvert_LbsToOunce() {
        WeightConverter weight = new WeightConverter();
        double result = weight.convert(2.0, "lbs", "ounce");
        assertEquals(32.0, result, 0.001);
    }

    @Test
    @DisplayName("TC-BU-07: TemperatureConverter.convert(100 K -> C)")
    public void testTC07_Driver_TempConvert_KtoC() {
        TemperatureConverter temp = new TemperatureConverter();
        double result = temp.convert(100.0, "K", "C");
        assertEquals(-173.15, result, 0.001);
    }

    @Test
    @DisplayName("TC-BU-08: TemperatureConverter.convert(100 C -> F) [Defect]")
    public void testTC08_Driver_TempConvert_CtoF() {
        TemperatureConverter temp = new TemperatureConverter();
        double result = temp.convert(100.0, "C", "F");
        // Expected: 212.0, Actual in code: 132.0 (due to integer division 9/5 = 1)
        assertEquals(212.0, result, 0.001);
    }

    // =========================================================================
    // STEP 3 (Root Level): Test UniversalConverter (Full Integration) (TC 9 - 10)
    // =========================================================================

    @Test
    @DisplayName("TC-BU-09: UniversalConverter.convert(2 Distance km -> mile)")
    public void testTC09_Driver_Universal_KmToMile() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(2.0, "Distance", "kilometer", "mile");
        assertEquals(1.242, result, 0.001);
    }

    @Test
    @DisplayName("TC-BU-10: UniversalConverter.convert(10 Weight lbs -> ounce)")
    public void testTC10_Driver_Universal_LbsToOunce() {
        UniversalConverter universal = new UniversalConverter();
        double result = universal.convert(10.0, "Weight", "lbs", "ounce");
        assertEquals(160.0, result, 0.001);
    }
}
package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietRecommended() {
        //given
        double height = 1.72;
        double weight = 69.5;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldThrowException_whenDietRecommended() {
        //given
        double height = 0.0;
        double weight = 69.5;

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "Weight {0}")
    @ValueSource(doubles = {70.0, 75.0, 80.0})
    void shouldReturnTrueForAllValues_whenDietRecommended(double weight) {
        //given
        double height = 1.65;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "Weight {0} Height {1}")
    @CsvSource({"65.0,1.95", "55.0,1.9", "60.0, 1.85"})
    void shouldReturnFalseForAllCsv_whenDietRecommended(double weight, double height) {
        //given
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "Weight {0} Height {1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalseForAllCsvFileValues_whenDietRecommended(double weight, double height) {
        //given
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldReturnUserWithWorstBMI() {
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;

        //when
        User user = FitCalculator.findUserWithTheWorstBMI(users);

        //then
        assertAll(
                () -> assertEquals(user.getWeight(), 97.3),
                () -> assertEquals(user.getHeight(), 1.79)
        );
    }

    @Test
    void shouldReturnNull_whenListOfUsersIsEmpty() {
        //given
        List<User> users = new ArrayList<>();

        //when
        User user = FitCalculator.findUserWithTheWorstBMI(users);

        //then
        assertNull(user);
    }

    @Test
    void shouldCalculateBMIScore() {
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;

        //when
        double[] calculated = FitCalculator.calculateBMIScore(users);

        //then
        assertArrayEquals(calculated, TestConstants.TEST_USERS_BMI_SCORE);
    }
}
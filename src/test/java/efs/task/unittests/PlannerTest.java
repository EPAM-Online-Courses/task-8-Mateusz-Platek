package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class PlannerTest {

    Planner planner;

    @BeforeEach
    void init() {
        planner = new Planner();
    }

    @ParameterizedTest(name = "Activity level {0}")
    @EnumSource(ActivityLevel.class)
    void shouldCalculateDailyCaloriesDemand(ActivityLevel activityLevel) {
        //given
        User user = TestConstants.TEST_USER;

        //when
        int calculated = planner.calculateDailyCaloriesDemand(user, activityLevel);

        //then
        assertEquals(calculated, TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel));
    }

    @Test
    void shouldCalculateDailyIntake() {
        //given
        User user = TestConstants.TEST_USER;

        //when
        DailyIntake dailyIntake = planner.calculateDailyIntake(user);

        //then
        assertAll(
                () -> assertEquals(dailyIntake.getCalories(), TestConstants.TEST_USER_DAILY_INTAKE.getCalories()),
                () -> assertEquals(dailyIntake.getCarbohydrate(), TestConstants.TEST_USER_DAILY_INTAKE.getCarbohydrate()),
                () -> assertEquals(dailyIntake.getFat(), TestConstants.TEST_USER_DAILY_INTAKE.getFat()),
                () -> assertEquals(dailyIntake.getProtein(), TestConstants.TEST_USER_DAILY_INTAKE.getProtein())
        );
    }
}

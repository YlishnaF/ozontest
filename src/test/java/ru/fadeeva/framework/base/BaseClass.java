package ru.fadeeva.framework.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.fadeeva.framework.managers.DriverManager;
import ru.fadeeva.framework.managers.InitManager;
import ru.fadeeva.framework.managers.PageManager;
import ru.fadeeva.framework.managers.TestPropManager;
import ru.fadeeva.framework.utils.PropsConst;

public class BaseClass {
    private final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager propManager = TestPropManager.getInstance();

    @BeforeAll
    public static void beforeClass(){
        InitManager.initFramework();
    }

    @BeforeEach
    void before() {
        driverManager.getDriver().get(propManager.getProperty(PropsConst.BASE_URL));
    }
    @AfterEach
    void after(){
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }

}
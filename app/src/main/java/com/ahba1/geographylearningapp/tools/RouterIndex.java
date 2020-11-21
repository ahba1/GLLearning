package com.ahba1.geographylearningapp.tools;

public class RouterIndex {

    public final static String IndexScheme = "GL://";

    public final static String Activity = "activity/";

    /**
     * login page
     * @page {@link com.ahba1.geographylearningapp.activity.login.LoginActivity}
     */
    public final static String Login = Activity + "Login";

    /**
     * login page
     * @page {@link com.ahba1.geographylearningapp.activity.register.RegisterActivity}
     */
    public final static String Register = Activity + "Register";

    /**
     * login page
     * @page {@link com.ahba1.geographylearningapp.activity.cube.CubeActivity}
     */
    public final static String Cube  = Activity + "Cube";

    /**
     * login page
     * @page {@link com.ahba1.geographylearningapp.activity.progress.ProgressActivity}
     */
    public final static String Progress = Activity + "Progress/:mode/:id:/path", IdParam = "id";

    public static String getActivityIndex(String page) {
        return IndexScheme + page;
    }
}

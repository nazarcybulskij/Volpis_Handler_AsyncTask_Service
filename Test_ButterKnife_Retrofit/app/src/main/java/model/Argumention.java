package model;

/**
 * Created by nazar on 09.06.15.
 */
public class Argumention {

    String arg1;
    String arg2;

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }
    @Override
    public String toString() {
        return "Args{\n" + "arg1 = " + arg1 + ",\narg2 = " + arg2 + "\n" +
                "}";

    }


    }

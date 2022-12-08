package ru.hogwarts.school;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

public class Constant {
    public static long LONG_NUM_1 = 1L;
    public static long LONG_NUM_2 = 2L;
    public static long LONG_NUM_3 = 3L;
    public static long LONG_NUM_4 = 4L;

    public static int NUM_1 = 1;
    public static int NUM_2 = 2;
    public static int NUM_3 = 3;
    public static int NUM_4 = 4;

    public static String NAME_AAA = "nameAAA";
    public static String NAME_BBB = "nameBBB";
    public static String NAME_CCC = "nameCCC";

    public static String COLOR_AAA = "colorAAA";
    public static String COLOR_BBB = "colorBBB";
    public static String COLOR_CCC = "colorCCC";

    public static Student STUDENT_AAA = new Student(LONG_NUM_1, NAME_AAA, NUM_1);
    public static Student STUDENT_BBB = new Student(LONG_NUM_2, NAME_BBB, NUM_2);
    public static Student STUDENT_CCC = new Student(LONG_NUM_3, NAME_CCC, NUM_1);

    public static Faculty FACULTY_AAA = new Faculty(LONG_NUM_1, NAME_AAA, COLOR_AAA);
    public static Faculty FACULTY_BBB = new Faculty(LONG_NUM_1, NAME_BBB, COLOR_BBB);
    public static Faculty FACULTY_CCC = new Faculty(LONG_NUM_1, NAME_CCC, COLOR_AAA);
}

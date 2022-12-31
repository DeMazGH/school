package ru.hogwarts.school;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static long LONG_NUM_1 = 1L;
    public static long LONG_NUM_2 = 2L;
    public static long LONG_NUM_3 = 3L;
    public static long LONG_NUM_4 = 4L;
    public static double DOUBLE_NUM_1 = 1d;

    public static int NUM_1 = 1;
    public static int NUM_2 = 2;
    public static int NUM_3 = 3;
    public static int NUM_4 = 4;

    public static String STRING_AAA = "stringAAA";

    public static String NAME_AAA = "nameAAA";
    public static String NAME_BBB = "nameBBB";
    public static String NAME_CCC = "nameCCC";
    public static String NAME_DDD = "nameDDD";
    public static String NAME_EEE = "nameEEE";
    public static String NAME_LONGEST = "nameLONGEST";

    public static String COLOR_AAA = "colorAAA";
    public static String COLOR_BBB = "colorBBB";
    public static String COLOR_CCC = "colorCCC";

    public static List<Student> STUDENT_LIST_1 = new ArrayList<>();
    public static Faculty FACULTY_AAA = new Faculty(LONG_NUM_1, NAME_AAA, COLOR_AAA, STUDENT_LIST_1);
    public static Faculty FACULTY_BBB = new Faculty(LONG_NUM_2, NAME_BBB, COLOR_BBB, STUDENT_LIST_1);
    public static Faculty FACULTY_CCC = new Faculty(LONG_NUM_3, NAME_CCC, COLOR_AAA, STUDENT_LIST_1);
    public static Faculty FACULTY_LONGEST_NAME = new Faculty(NAME_LONGEST, COLOR_CCC);

    public static Student STUDENT_AAA = new Student(LONG_NUM_1, NAME_AAA, NUM_1, FACULTY_AAA);
    public static Student STUDENT_BBB = new Student(LONG_NUM_2, NAME_BBB, NUM_2, FACULTY_BBB);
    public static Student STUDENT_CCC = new Student(LONG_NUM_3, NAME_CCC, NUM_1, FACULTY_AAA);
    public static Student STUDENT_DDD = new Student(NAME_DDD, NUM_3);
    public static Student STUDENT_EEE = new Student(NAME_EEE, NUM_4);

}

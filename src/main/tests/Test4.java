package main.tests;

import main.Generator;
import main.paths.Path;
import main.spaces.Space;

/**
 * <h1>Test Script #4</h1>
 * <p>This test script tests whether or not the generation of Paths are working properly or not</p>
 *
 *
 */
public class Test4 {
    public static void main(String[] args) {
        Path careerPath1 = Generator.generateCareerPath(40);
        Path careerPath2 = Generator.generateCareerPath(30);
        Path collegePath = Generator.generateCareerPath(24, careerPath1, careerPath2);
        Path changeCareerPath = Generator.generateChangeCareerPath(20);
        Path startAFamilyPath = Generator.generateStartAFamilyPath(20);
        System.out.println(careerPath1);
        System.out.println(careerPath2);
        System.out.println(collegePath);
        System.out.println(changeCareerPath);
        System.out.println(startAFamilyPath);
    }
}

package model.tests;

import model.Generator;
import model.paths.Path;

/**
 * <h1>Test Script #4</h1>
 * <p>This test script tests whether or not the generation of Paths are working properly or not</p>
 *
 * <h1>Sequence</h1>
 * <ol>
 *     <li>Generate CareerPath 1 and 2</li>
 *     <li>Generate CollegePath and pass CareerPaths 1 and 2</li>
 *     <li>Generate Change Career Path</li>
 *     <li>Generate Start a Family Path</li>
 *     <li>Output all the Path</li>
 * </ol>
 *
 * <p>This proves that the Path generation works.</p>
 *
 * @since 09/04/2020
 */
public class Test4 {
    public static void main(String[] args) {
        Path retirementPath = Generator.generateRetirementPath();
        Path changeCareerPath = Generator.generateChangeCareerPath(retirementPath, null);
        Path startAFamilyPath = Generator.generateStartAFamilyPath(retirementPath, null);
        Path collegePath = Generator.generateCollegePath(changeCareerPath, startAFamilyPath);
        Path careerPath = Generator.generateCareerPath(changeCareerPath, startAFamilyPath);
        System.out.println(retirementPath);
        System.out.println(changeCareerPath);
        System.out.println(startAFamilyPath);
        System.out.println(careerPath);
        System.out.println(collegePath);
    }
}

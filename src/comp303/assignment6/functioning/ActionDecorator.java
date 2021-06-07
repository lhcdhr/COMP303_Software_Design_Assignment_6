package comp303.assignment6.functioning;

import comp303.assignment6.robot.Robot;

/**
 * Decorator Design Pattern
 *
 * This just provides an abstract class
 * in case user want to add more Decorator Actions if possible.
 *
 * Haochen Liu
 *
 */
public abstract class ActionDecorator extends Action{
    Action toDecorate;
}

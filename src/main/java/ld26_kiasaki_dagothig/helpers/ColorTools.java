/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ld26_kiasaki_dagothig.helpers;

import org.newdawn.slick.Color;

public class ColorTools {
    public static void visualSeekAlpha(Color currentColor, float targetAlpha, float animationSpeed) {
        currentColor.a += (targetAlpha - currentColor.a) * animationSpeed;
        if (currentColor.a < 0.01f){
        	currentColor.a = 0f;
        }
    }
    public static void visualSeekColors(Color currentColor, float targetR, float targetG, float targetB, float animationSpeed) {
        currentColor.r += (targetR - currentColor.r) * animationSpeed;
        currentColor.g += (targetG - currentColor.g) * animationSpeed;
        currentColor.b += (targetB - currentColor.b) * animationSpeed;
    }
    public static void visualSeekColors(Color currentColor, float targetR, float targetG, float targetB, float currentStep, float stepCount) {
        currentColor.r += ((targetR - currentColor.r) * currentStep) / stepCount;
        currentColor.g += ((targetG - currentColor.g) * currentStep) / stepCount;
        currentColor.b += ((targetB - currentColor.b) * currentStep) / stepCount;
    }
}

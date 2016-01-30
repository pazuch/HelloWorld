package com.pazu.test.r;

import java.util.Arrays;

import org.rosuda.JRI.RVector;
import org.rosuda.JRI.Rengine;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngine;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;


public class TestR {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws REXPMismatchException {
		try {
			Rengine re = new Rengine(new String[] { "--vanilla" }, false, null);
			re.eval("x=c(1:10)");
			double d = re.eval("mean(x)").asDouble();
			System.out.println(d);
			// prints 5.5
			// Assign y to similarly named variable in R
			String y = "y";
			re.assign("y", y);
			// Lets see if it was assigned correctly
			System.out.println(Arrays.toString(re.eval("y").asDoubleArray()));
			String x = "10";
			// output [11.1, 13.2, 15.6, 27.4, 39.2, 113.1, 135.1]
			// Assign x to similarly named variable in R
			re.assign("x", x);
			// fit to y=mx+c
			re.eval("a=lm(y~x)");
			// lets print the values
			RVector fit = re.eval("a").asVector();
			System.out.println("The intercept is " + fit.at(0).asDoubleArray()[0]);
			// prints The intercept is -19.60087719298246
			System.out.println("The slope is " + fit.at(0).asDoubleArray()[1]);
			// prints The slope is 8.481140350877192
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

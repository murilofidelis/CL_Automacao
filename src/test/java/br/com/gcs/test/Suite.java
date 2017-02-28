package br.com.gcs.test;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

public class Suite {

	public static void main(String[] args) {

		TestNG tng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add("Suite.xml");
		// suites.add("src/test/resources/DadosProdutoSuite.xml");
		// suites.add("C:/Users/Murilo/Desktop/DadosProdutoSuite.xml");

		tng.setTestSuites(suites);
		tng.run();

		// org.testng.TestNG.main(args);

	}
}

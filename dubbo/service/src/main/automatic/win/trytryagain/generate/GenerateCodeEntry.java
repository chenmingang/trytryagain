package com.diy.generate;

public class GenerateCodeEntry {
	
	public static void main(String[] args) throws Exception {
		String configPath = GenerateCodeEntry.class.getClassLoader().getResource("lagou-market-generator.xml").getPath();
		GenerateCode.generate(configPath);
	}
}

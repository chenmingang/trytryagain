package win.trytryagain.generate;

public class GenerateCodeEntry {
	
	public static void main(String[] args) throws Exception {
		String configPath = GenerateCodeEntry.class.getClassLoader().getResource("generator.xml").getPath();
		GenerateCode.generate(configPath);
	}
}

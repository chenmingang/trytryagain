package win.trytryagain.generate;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 自动生成代码
 */
public class GenerateCode {

    public static void main(String[] args) throws Exception {
        new GenerateCode().generateMain();
    }

    public void generateMain() throws Exception {
        String f = GenerateCode.class.getClassLoader().getResource("").getPath();
        generate(f);
    }

    /**
     * @param f
     * @throws Exception
     */
    public static void generate(String f) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(f);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(new ProgressCallback() {

            @Override
            public void startTask(String arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void saveStarted(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void introspectionStarted(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void generationStarted(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void done() {
                // TODO Auto-generated method stub

            }

            @Override
            public void checkCancel() throws InterruptedException {
                // TODO Auto-generated method stub

            }
        });
        for(String s : warnings) {
            System.out.println(s);
        }
    }

}

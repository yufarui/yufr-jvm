/**
 * 此类 辅助查看 类的结构
 */
public class ClassFile {

    public String name = "class";

    private ClassFile classT = new ClassFile();

    public static final int num = 12;

    public static void main(String[] args) throws RuntimeException {

        ClassFile classFile = new ClassFile();
        classFile.name = "777";
        classFile.name += classFile.classT.name;

        System.out.println("Hello World");
    }
}

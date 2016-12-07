import java.io.IOException;
import java.util.Arrays;
import java.applet.Applet;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;
import org.apache.bcel.classfile.*;
import org.apache.bcel.generic.*;
import sun.rmi.rmic.Constants;


public class MetaMain {
    private static int sampleField;
    /**
      *  @param args
      *  @throws IOException
      *  @throws ClassFormatException
      */
    public static void main(String[] args) throws ClassFormatException, IOException {
        Applet applet = new Applet();

        /*An existing class can be parsed with ClassParser */
        ClassParser parser=new ClassParser(MetaMain.class.getResourceAsStream("MetaMain.class"), "MetaMain.class");
        JavaClass javaClassParser=parser.parse();

        System.out.println("*******Constant Pool*********");
        System.out.println(javaClassParser.getConstantPool());

        System.out.println("*******Fields*********");
        System.out.println(Arrays.toString(javaClassParser.getFields()));
        System.out.println();

        System.out.println("*******Methods*********");
        System.out.println(Arrays.toString(javaClassParser.getMethods()));

        for(Method method:javaClassParser.getMethods()){
            System.out.println(method);
            System.out.println(method.getCode());
        }

        System.out.println();
        System.out.println("*******GenerateClass*********");


        ClassGen classGen = new ClassGen("SyntheticClass","java.lang.Object","SyntheticClass.java", Constants.ACC_PUBLIC,null);
        ConstantPoolGen constantPoolGen = classGen.getConstantPool();
        InstructionList instructionList = new InstructionList();
        instructionList.append(new GETSTATIC(constantPoolGen.addFieldref("java.lang.System","out","Ljava/io/PrintStream;")));
        instructionList.append(new LDC(constantPoolGen.addString(" You are a real geek!")));
        instructionList.append(new INVOKEVIRTUAL(constantPoolGen.addMethodref("java.io.PrintStream", "println", "(Ljava/lang/String;)V")));
        instructionList.append(new RETURN());
        MethodGen main = new MethodGen(Constants.ACC_PUBLIC|Constants.ACC_STATIC,Type.VOID,new Type[]{new ArrayType(Type.STRING,1)}, new String[]{"args"}, "main","SyntheticClass",instructionList,constantPoolGen);
        main.setMaxLocals();
        main.setMaxStack();
        classGen.addMethod(main.getMethod());
        // MethodGen log =  new MethodGen(Constants.ACC_PUBLIC| Constants.ACC_STATIC, Type.VOID,new Type[]{new S})


    }
    public static void sampleMethod(){
        System.out.println("Hello World");
    }

}